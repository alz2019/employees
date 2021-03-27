package com.alz2019.employeesapi.repository;

import com.alz2019.employeesapi.model.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;

@Component
public class DbInitializer implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;

    public DbInitializer(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.employeeRepository.deleteAll();

        // Data generated with jfairy - Java data generator
        Employee employee1 = new Employee(1L, "Alexa", "Wright", LocalDateTime.of(1982, Month.FEBRUARY, 5, 0, 0), "Team Lead", "alexa.wright@gmail.com");
        Employee employee2 = new Employee(2L, "Ashley", "Flores", LocalDateTime.of(1987, Month.MAY, 11, 0, 0), "Scrum Master", "ashley.flores@mail.com");
        Employee employee3 = new Employee(3L, "Andrew", "Humphrey", LocalDateTime.of(1959, Month.JUNE, 29, 0, 0), "Tech Lead", "humphrey@mail.com");
        Employee employee4 = new Employee(4L, "Jackson", "Acosta", LocalDateTime.of(1991, Month.NOVEMBER, 21, 0, 0), "Project Manager", "acosta@gmail.com");
        Employee employee5 = new Employee(5L, "Adam", "Mcdowell", LocalDateTime.of(1955, Month.AUGUST, 17, 0, 0), "Senior", "adammcdowell@mail.com");
        Employee employee6 = new Employee(6L, "Bailey", "Fuentes", LocalDateTime.of(1951, Month.MAY, 18, 0, 0), "Senior", "baileyfuentes@yahoo.com");
        Employee employee7 = new Employee(7L, "Molly", "Mueller", LocalDateTime.of(1966, Month.NOVEMBER, 7, 0, 0), "Middle", "mueller@yahoo.com");
        Employee employee8 = new Employee(8L, "Mia", "Cervantes", LocalDateTime.of(1971, Month.JULY, 26, 0, 0), "Middle", "miacervantes@gmail.com");
        Employee employee9 = new Employee(9L, "Aaliyah", "Atkinson", LocalDateTime.of(1982, Month.APRIL, 23, 0, 0), "Junior", "aaliyahatkinson@yahoo.com");
        Employee employee10 = new Employee(10L, "Landon", "Lopez", LocalDateTime.of(1973, Month.AUGUST, 20, 0, 0), "Trainee", "lopez@yahoo.com");

        this.employeeRepository.save(employee1);
        this.employeeRepository.save(employee2);
        this.employeeRepository.save(employee3);
        this.employeeRepository.save(employee4);
        this.employeeRepository.save(employee5);
        this.employeeRepository.save(employee6);
        this.employeeRepository.save(employee7);
        this.employeeRepository.save(employee8);
        this.employeeRepository.save(employee9);
        this.employeeRepository.save(employee10);
    }
}
