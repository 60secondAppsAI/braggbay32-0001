package com.braggbay32.dao;

import java.util.List;

import com.braggbay32.dao.GenericDAO;
import com.braggbay32.domain.FavoriteSeller;





public interface FavoriteSellerDAO extends GenericDAO<FavoriteSeller, Integer> {
  
	List<FavoriteSeller> findAll();
	






}


