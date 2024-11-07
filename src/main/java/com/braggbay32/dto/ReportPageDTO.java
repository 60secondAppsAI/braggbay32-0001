package com.braggbay32.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReportPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<ReportDTO> reports;
}





