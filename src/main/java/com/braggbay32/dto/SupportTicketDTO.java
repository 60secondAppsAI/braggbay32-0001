package com.braggbay32.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class SupportTicketDTO {

	private Integer supportTicketId;

	private String subject;

	private String description;

	private Date ticketDate;






}