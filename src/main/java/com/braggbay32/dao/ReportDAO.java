package com.braggbay32.dao;

import java.util.List;

import com.braggbay32.dao.GenericDAO;
import com.braggbay32.domain.Report;





public interface ReportDAO extends GenericDAO<Report, Integer> {
  
	List<Report> findAll();
	






}

