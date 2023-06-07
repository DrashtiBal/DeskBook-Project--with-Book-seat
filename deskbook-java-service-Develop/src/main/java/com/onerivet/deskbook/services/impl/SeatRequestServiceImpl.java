package com.onerivet.deskbook.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.entity.SeatRequest;
import com.onerivet.deskbook.models.payload.DesignationDto;
import com.onerivet.deskbook.models.payload.SeatInformationViewDto;
import com.onerivet.deskbook.models.payload.TemporarySeatOwnerDto;
import com.onerivet.deskbook.repository.EmployeeRepo;
import com.onerivet.deskbook.repository.SeatConfigurationRepo;
import com.onerivet.deskbook.repository.SeatNumberRepo;
import com.onerivet.deskbook.repository.SeatRequestRepo;
import com.onerivet.deskbook.services.SeatRequestService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SeatRequestServiceImpl implements SeatRequestService {

	@Autowired
	private SeatConfigurationRepo seatConfigurationRepo;

	@Autowired
	private SeatNumberRepo seatNumberRepo;

	@Autowired
	private SeatRequestRepo seatRequestRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private ModelMapper modelMapper;

	public SeatInformationViewDto seatInformationById(int id, String employeeId) {
		SeatNumber seatNumberInfo = this.seatNumberRepo.findById(id).get();
		// System.out.println(seatNumberInfo);

		SeatConfiguration seatInfo = this.seatConfigurationRepo.findBySeatNumberAndDeletedByNull(seatNumberInfo);
		// System.out.println(seatInfo);
		
		Employee employee = employeeRepo.findById(seatInfo.getEmployee().getId()).get();

		SeatInformationViewDto seatInformationViewDto = new SeatInformationViewDto();
		
		seatInformationViewDto.setName(seatInfo.getEmployee().getFirstName());
		seatInformationViewDto.setDesignation(seatInfo.getEmployee().getDesignation().getDesignationName());
		seatInformationViewDto.setEmail(seatInfo.getEmployee().getEmailId());
		
		LocalDate bookingDate = LocalDate.now();

		int countOfRequest = seatRequestRepo.countFindBySeatIdAndBookingDateAndDeletedDateNull(seatNumberInfo,bookingDate);

		seatInformationViewDto.setCount(countOfRequest);

		System.out.println(countOfRequest);

		
		//SeatInformationViewDto seatInformationViewDtoOld = new SeatInformationViewDto();

		SeatRequest findByEmployeeIdAndSeatIdAndBookingDateAndDeletedDateNull = seatRequestRepo
				.findByEmployeeIdAndSeatIdAndBookingDateAndDeletedDateNull(employeeId, seatNumberInfo, bookingDate);

		// seatRequestRepo.findByRequestStatusAndBookingDateAndSeatIdAndDeletedDateNull(1,
		// bookingDate, seatInfo.getSeatNumber());

		SeatRequest findByRequestStatusAndBookingDateAndSeatIdAndDeletedDateNull = seatRequestRepo
				.findByRequestStatusAndBookingDateAndSeatIdAndDeletedDateNull(1, bookingDate, seatNumberInfo);

		if (findByRequestStatusAndBookingDateAndSeatIdAndDeletedDateNull == null) {
			return seatInformationViewDto;
		} 
			TemporarySeatOwnerDto temp = new TemporarySeatOwnerDto();

			temp.setFirstName(
					findByRequestStatusAndBookingDateAndSeatIdAndDeletedDateNull.getEmployee().getFirstName());
			temp.setLastName(findByRequestStatusAndBookingDateAndSeatIdAndDeletedDateNull.getEmployee().getLastName());
			temp.setEmailId(findByRequestStatusAndBookingDateAndSeatIdAndDeletedDateNull.getEmployee().getEmailId());
			temp.setDesignation(modelMapper.map(
					findByRequestStatusAndBookingDateAndSeatIdAndDeletedDateNull.getEmployee().getDesignation(),
					DesignationDto.class));

			seatInformationViewDto.setTemporarySeatOwnerDto(temp);
		return seatInformationViewDto;

	}

	@Override
	public String seatRequestAccept(int id,Employee modifiedBy,String employeeId) {
		SeatRequest seatReuest1 = seatRequestRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Seat request not found"));
		
		if(seatReuest1.getRequestStatus() == 2) {
            //System.out.println("Seat request has already been accepted.");
            return "Seat request has already been accepted.";
		}
		seatReuest1.setRequestStatus(2);
		seatReuest1.setModifiedBy(seatReuest1.getModifiedBy());
		seatReuest1.setModifiedDate(LocalDateTime.now());
		seatRequestRepo.save(seatReuest1);
		//System.out.println("accepted");
		return "accepted";
		
		
		//List<SeatRequest> findAllByRequestStatus = seatRequestRepo.findAllByRequestStatus(0);
	}
	
	

}
