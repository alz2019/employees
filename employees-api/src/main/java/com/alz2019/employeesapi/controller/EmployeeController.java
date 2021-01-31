package com.alz2019.employeesapi.controller;

import com.alz2019.employeesapi.model.Employee;
import com.alz2019.employeesapi.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/employees")
@CrossOrigin
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        if (!Objects.equals(id, employee.getId())) {
            throw new IllegalStateException("ID parameter doesn't match body value");
        }
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void removeEmployee(@PathVariable("id") Long id) {
        employeeService.removeEmployeeById(id);
    }
}
