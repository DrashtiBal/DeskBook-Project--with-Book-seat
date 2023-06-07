package com.onerivet.deskbook.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.onerivet.deskbook.models.payload.EmployeeDto;
import com.onerivet.deskbook.models.payload.ProfileUpdatedOrNot;
import com.onerivet.deskbook.models.payload.ProfileViewDto;
import com.onerivet.deskbook.models.payload.SeatInformationViewDto;
import com.onerivet.deskbook.models.payload.UpdateProfileDto;


public interface EmployeeService {

	public List<EmployeeDto> getAllEmployees();

	public ProfileViewDto getEmployeeById(String id) throws Exception;
	
	public ProfileViewDto updateEmpById(String id, UpdateProfileDto newEmployeeDto) throws Exception;
	
	public ProfileUpdatedOrNot isProfileUpdated(String id) throws Exception;
	
	public SeatInformationViewDto seatinformationByID(int id);
	
	public List<EmployeeDto> searchByName(String name,Pageable pageable);
		
}
