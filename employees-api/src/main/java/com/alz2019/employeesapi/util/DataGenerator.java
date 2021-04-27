package com.alz2019.employeesapi.util;

import com.alz2019.employeesapi.model.Employee;
import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataGenerator {
    public static List<Employee> generateEmployeeList() {
        return Stream.generate(DataGenerator::generateEmployee)
                .filter(employee -> employee.getBirthday().isAfter(LocalDateTime.of(1956, Month.APRIL, 27, 0, 0)) &&
                        employee.getBirthday().isBefore(LocalDateTime.of(2003, Month.APRIL, 28, 0, 0)))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static Employee generateEmployee() {
        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        return convertPersonToEmployee(person);
    }

    private static Employee convertPersonToEmployee(Person person) {
        Employee employee = new Employee();
        employee.setFirstName(person.getFirstName());
        employee.setLastName(person.getLastName());
        employee.setBirthday(person.getDateOfBirth().atStartOfDay());
        employee.setPhone(person.getTelephoneNumber());
        employee.setEmail(person.getEmail());
        return employee;
    }
}
