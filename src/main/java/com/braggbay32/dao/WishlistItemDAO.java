package com.braggbay32.dao;

import java.util.List;

import com.braggbay32.dao.GenericDAO;
import com.braggbay32.domain.WishlistItem;





public interface WishlistItemDAO extends GenericDAO<WishlistItem, Integer> {
  
	List<WishlistItem> findAll();
	






}


