package com.onerivet.deskbook.services;

import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.payload.SeatInformationViewDto;


public interface SeatRequestService {

	public SeatInformationViewDto seatInformationById(int id, String employeeId);
	
	public String seatRequestAccept(int id,Employee modifiedBy,String employeeId);
	

	
}
