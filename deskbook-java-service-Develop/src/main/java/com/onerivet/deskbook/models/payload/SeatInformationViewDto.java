package com.onerivet.deskbook.models.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SeatInformationViewDto {
	
	    private String name;
	    private String designation;
	    private String duration="FULLDAY";
	    private String email;
	    private int count;
	    private TemporarySeatOwnerDto temporarySeatOwnerDto;
}
