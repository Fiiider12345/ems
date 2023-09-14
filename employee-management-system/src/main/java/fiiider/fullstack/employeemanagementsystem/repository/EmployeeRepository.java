package fiiider.fullstack.employeemanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiiider.fullstack.employeemanagementsystem.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findEmployeeById(Long id);

}
