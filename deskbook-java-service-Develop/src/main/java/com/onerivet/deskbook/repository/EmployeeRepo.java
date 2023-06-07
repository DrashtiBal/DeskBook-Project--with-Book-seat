package com.onerivet.deskbook.repository;


//import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.payload.EmployeeDto;

//import jakarta.persistence.Query;

//import jakarta.validation.constraints.AssertFalse.List;

public interface EmployeeRepo extends JpaRepository<Employee, String> {

	//@Query(value="SELECT e FROM ")
	@Query(value="SELECT e FROM Employee e  WHERE e.firstName LIKE :name% OR e.lastName LIKE :name%")
	public List<Employee> searchByName(String name,Pageable pageable);
	
}
