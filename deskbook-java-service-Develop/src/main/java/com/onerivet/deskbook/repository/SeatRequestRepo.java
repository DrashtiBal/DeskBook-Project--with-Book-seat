package com.onerivet.deskbook.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.entity.SeatRequest;

public interface SeatRequestRepo extends JpaRepository<SeatRequest, Integer> {

	public int countFindBySeatIdAndBookingDateAndDeletedDateNull(SeatNumber seatId, LocalDate bookingDate);

	public SeatRequest findByEmployeeIdAndSeatIdAndBookingDateAndDeletedDateNull(String employeeId, SeatNumber seatId,
			LocalDate LocalbookingDate);

	public SeatRequest findByRequestStatusAndBookingDateAndSeatIdAndDeletedDateNull(int id, LocalDate bookingDate,
			SeatNumber seatNumber);
	
	public List<SeatRequest> findAllByRequestStatus(int requestStatus);
}
