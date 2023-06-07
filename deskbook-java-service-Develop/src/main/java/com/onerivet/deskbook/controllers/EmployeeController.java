package com.onerivet.deskbook.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.entity.PaginationAndSorting;
import com.onerivet.deskbook.models.payload.EmployeeDto;
import com.onerivet.deskbook.models.payload.ProfileUpdatedOrNot;
import com.onerivet.deskbook.models.payload.ProfileViewDto;
import com.onerivet.deskbook.models.payload.SeatInformationViewDto;
import com.onerivet.deskbook.models.payload.UpdateProfileDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.EmployeeService;
import com.onerivet.deskbook.services.impl.EmailServiceImpl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/deskbook/user-profile")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmailServiceImpl emailServiceImpl;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	/**
	 * @purpose: Get all employees
	 * @return: List of employeeDto
	 */
	@GetMapping("/employees")
	public GenericResponse<List<EmployeeDto>> getAll() {
		GenericResponse<List<EmployeeDto>> genericResponse = new GenericResponse<>(
				this.employeeService.getAllEmployees(), null);
		return genericResponse;
	}

	/**
	 * @purpose: Get employee by id
	 * @param: id
	 * @return: ProfileViewDto
	 */
	@GetMapping("/")
	public GenericResponse<ProfileViewDto> getById(Principal principal) throws Exception {
		GenericResponse<ProfileViewDto> genericResponse = new GenericResponse<>(
				this.employeeService.getEmployeeById(principal.getName()), null);
		return genericResponse;
	}

	/**
	 * @purpose: Update employee by id
	 * @param: updateProfileDto
	 * @return: profileViewDto
	 */
	@PutMapping("/")
	public ResponseEntity<GenericResponse<ProfileViewDto>> updateById(Principal principal,
			@RequestBody @Valid UpdateProfileDto newEmployee) throws Exception {
		GenericResponse<ProfileViewDto> genericResponse = new GenericResponse<>(
				this.employeeService.updateEmpById(principal.getName(), newEmployee), null);
		return new ResponseEntity<GenericResponse<ProfileViewDto>>(genericResponse, HttpStatus.OK);
	}

	/**
	 * @purpose: Check profile updated or not
	 * @param: id
	 * @return: ProfileUpdatedOrNot
	 */
	@GetMapping("/isUpdated")
	public GenericResponse<ProfileUpdatedOrNot> isUpdated(Principal principal) throws Exception {
		GenericResponse<ProfileUpdatedOrNot> genericResponse = new GenericResponse<>(
				this.employeeService.isProfileUpdated(principal.getName()), null);
		return genericResponse;
	}

//	@GetMapping("/seat/{id}")
//	public GenericResponse<SeatInformationViewDto> seatInformatin(@PathVariable("id") int id) throws Exception {
//		GenericResponse<SeatInformationViewDto> genericResponse = new GenericResponse<>(
//				this.employeeService.seatinformationByID(id), null);
//		emailServiceImpl.emailSend("drashtimaisuriya@gmail.com", "Email Subject", "Hello World");
//		return genericResponse;
//	}

//	@GetMapping("/search/{name}")
//	public GenericResponse<EmployeeDto> searchByName(@PathVariable String name, PaginationAndSorting pagination)
//			throws Exception {
//
//		GenericResponse<EmployeeDto> genericResponse = new GenericResponse<>(
//				this.employeeService.searchByName(name, pagination.createPageRequest()), null);
//		// List<EmployeeDto> searchByName =
//		// this.employeeService.searchByName(name,pagination.createPageRequest());
//		// return new GenericResponse<>(searchByName,null);
//		// return new GenericResponse<>(searchByName.getContent(), null);
//		return genericResponse;
//	}

	@GetMapping("/search/{name}")
	public GenericResponse<List<EmployeeDto>> searchByName(@PathVariable String name,
			PaginationAndSorting paginationAndSorting) throws Exception {
		GenericResponse<List<EmployeeDto>> genericResponse = new GenericResponse<>(
				this.employeeService.searchByName(name, paginationAndSorting.createPageRequest()), null);
		return genericResponse;
	}

}
