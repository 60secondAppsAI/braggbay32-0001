package com.braggbay32.dao;

import java.util.List;

import com.braggbay32.dao.GenericDAO;
import com.braggbay32.domain.SupportTicket;





public interface SupportTicketDAO extends GenericDAO<SupportTicket, Integer> {
  
	List<SupportTicket> findAll();
	






}


