package com.alz2019.employeesapi.repository;

import com.alz2019.employeesapi.util.DataGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class DbInitializer implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;

    public DbInitializer(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.employeeRepository.deleteAll();

        // Data generated with jfairy - Java data generator
        employeeRepository.saveAll(DataGenerator.generateEmployeeList());
    }
}
