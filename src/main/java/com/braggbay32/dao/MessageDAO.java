package com.braggbay32.dao;

import java.util.List;

import com.braggbay32.dao.GenericDAO;
import com.braggbay32.domain.Message;





public interface MessageDAO extends GenericDAO<Message, Integer> {
  
	List<Message> findAll();
	






}


