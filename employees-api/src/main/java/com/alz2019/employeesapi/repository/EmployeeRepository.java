package com.alz2019.employeesapi.repository;

import com.alz2019.employeesapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
