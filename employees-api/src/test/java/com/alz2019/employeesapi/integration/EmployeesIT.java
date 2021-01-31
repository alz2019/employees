package com.alz2019.employeesapi.integration;

import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EmployeesIT {
    @LocalServerPort
    private int port;

    @Test
    void getEmployeesTest() throws Exception {
        given()
                .port(port)
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("", Matchers.hasSize(0));
    }

    @Test
    void addAndRemoveTest() throws JSONException {
        JSONObject jsonObject = new JSONObject()
                .put("firstName", "John")
                .put("lastName", "Doe");
        given()
                .port(port)
                .contentType("application/json")
                .body(jsonObject.toString())
                .when()
                .post("/employees")
                .then()
                .statusCode(200);

        given()
                .port(port)
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("", Matchers.hasSize(1))
                .body("", Matchers.anything("[{birthday=null, firstName=John, lastName=Doe, id=1, position=null, email=null}]"));

        given()
                .port(port)
                .when()
                .delete("/employees/1")
                .then()
                .statusCode(200);

        given()
                .port(port)
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("", Matchers.hasSize(0));
    }
}
