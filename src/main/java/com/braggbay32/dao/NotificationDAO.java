package com.braggbay32.dao;

import java.util.List;

import com.braggbay32.dao.GenericDAO;
import com.braggbay32.domain.Notification;





public interface NotificationDAO extends GenericDAO<Notification, Integer> {
  
	List<Notification> findAll();
	






}


