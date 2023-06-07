package com.onerivet.deskbook.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.payload.SeatInformationViewDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.SeatRequestService;
import com.onerivet.deskbook.services.impl.EmailServiceImpl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/deskbook/user-profile")
public class SeatRequestController {

	@Autowired
	private SeatRequestService seatRequestService;
	

	@Autowired
	private EmailServiceImpl emailServiceImpl;

	@GetMapping("/seat/{id}")
	public GenericResponse<SeatInformationViewDto> seatInformation(@PathVariable("id") int id, Principal principal)
			throws Exception {
		GenericResponse<SeatInformationViewDto> genericResponse = new GenericResponse<>(
				this.seatRequestService.seatInformationById(id, principal.getName()), null);
//		emailServiceImpl.emailSend("drashtimaisuriya@gmail.com", "Email Subject", "Hello World");
		return genericResponse;
	}

	@PostMapping("/accept/{id}")
	public GenericResponse<String> seatRequestAccept(@PathVariable("id")int id,Employee modifiedBy,Principal principal) throws Exception {
				GenericResponse<String>genericResponse=new GenericResponse<>(this.seatRequestService.seatRequestAccept(id,modifiedBy,principal.getName()),null);
				return genericResponse ;
	}
	
	
}
