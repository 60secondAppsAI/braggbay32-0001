package com.braggbay32.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbay32.dao.GenericDAO;
import com.braggbay32.service.GenericService;
import com.braggbay32.service.impl.GenericServiceImpl;
import com.braggbay32.dao.FavoriteSellerDAO;
import com.braggbay32.domain.FavoriteSeller;
import com.braggbay32.dto.FavoriteSellerDTO;
import com.braggbay32.dto.FavoriteSellerSearchDTO;
import com.braggbay32.dto.FavoriteSellerPageDTO;
import com.braggbay32.dto.FavoriteSellerConvertCriteriaDTO;
import com.braggbay32.dto.common.RequestDTO;
import com.braggbay32.dto.common.ResultDTO;
import com.braggbay32.service.FavoriteSellerService;
import com.braggbay32.util.ControllerUtils;





@Service
public class FavoriteSellerServiceImpl extends GenericServiceImpl<FavoriteSeller, Integer> implements FavoriteSellerService {

    private final static Logger logger = LoggerFactory.getLogger(FavoriteSellerServiceImpl.class);

	@Autowired
	FavoriteSellerDAO favoriteSellerDao;

	


	@Override
	public GenericDAO<FavoriteSeller, Integer> getDAO() {
		return (GenericDAO<FavoriteSeller, Integer>) favoriteSellerDao;
	}
	
	public List<FavoriteSeller> findAll () {
		List<FavoriteSeller> favoriteSellers = favoriteSellerDao.findAll();
		
		return favoriteSellers;	
		
	}

	public ResultDTO addFavoriteSeller(FavoriteSellerDTO favoriteSellerDTO, RequestDTO requestDTO) {

		FavoriteSeller favoriteSeller = new FavoriteSeller();

		favoriteSeller.setFavoriteSellerId(favoriteSellerDTO.getFavoriteSellerId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		favoriteSeller = favoriteSellerDao.save(favoriteSeller);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<FavoriteSeller> getAllFavoriteSellers(Pageable pageable) {
		return favoriteSellerDao.findAll(pageable);
	}

	public Page<FavoriteSeller> getAllFavoriteSellers(Specification<FavoriteSeller> spec, Pageable pageable) {
		return favoriteSellerDao.findAll(spec, pageable);
	}

	public ResponseEntity<FavoriteSellerPageDTO> getFavoriteSellers(FavoriteSellerSearchDTO favoriteSellerSearchDTO) {
	
			Integer favoriteSellerId = favoriteSellerSearchDTO.getFavoriteSellerId(); 
 			String sortBy = favoriteSellerSearchDTO.getSortBy();
			String sortOrder = favoriteSellerSearchDTO.getSortOrder();
			String searchQuery = favoriteSellerSearchDTO.getSearchQuery();
			Integer page = favoriteSellerSearchDTO.getPage();
			Integer size = favoriteSellerSearchDTO.getSize();

	        Specification<FavoriteSeller> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, favoriteSellerId, "favoriteSellerId"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<FavoriteSeller> favoriteSellers = this.getAllFavoriteSellers(spec, pageable);
		
		//System.out.println(String.valueOf(favoriteSellers.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(favoriteSellers.getTotalPages()));
		
		List<FavoriteSeller> favoriteSellersList = favoriteSellers.getContent();
		
		FavoriteSellerConvertCriteriaDTO convertCriteria = new FavoriteSellerConvertCriteriaDTO();
		List<FavoriteSellerDTO> favoriteSellerDTOs = this.convertFavoriteSellersToFavoriteSellerDTOs(favoriteSellersList,convertCriteria);
		
		FavoriteSellerPageDTO favoriteSellerPageDTO = new FavoriteSellerPageDTO();
		favoriteSellerPageDTO.setFavoriteSellers(favoriteSellerDTOs);
		favoriteSellerPageDTO.setTotalElements(favoriteSellers.getTotalElements());
		return ResponseEntity.ok(favoriteSellerPageDTO);
	}

	public List<FavoriteSellerDTO> convertFavoriteSellersToFavoriteSellerDTOs(List<FavoriteSeller> favoriteSellers, FavoriteSellerConvertCriteriaDTO convertCriteria) {
		
		List<FavoriteSellerDTO> favoriteSellerDTOs = new ArrayList<FavoriteSellerDTO>();
		
		for (FavoriteSeller favoriteSeller : favoriteSellers) {
			favoriteSellerDTOs.add(convertFavoriteSellerToFavoriteSellerDTO(favoriteSeller,convertCriteria));
		}
		
		return favoriteSellerDTOs;

	}
	
	public FavoriteSellerDTO convertFavoriteSellerToFavoriteSellerDTO(FavoriteSeller favoriteSeller, FavoriteSellerConvertCriteriaDTO convertCriteria) {
		
		FavoriteSellerDTO favoriteSellerDTO = new FavoriteSellerDTO();
		
		favoriteSellerDTO.setFavoriteSellerId(favoriteSeller.getFavoriteSellerId());

	

		
		return favoriteSellerDTO;
	}

	public ResultDTO updateFavoriteSeller(FavoriteSellerDTO favoriteSellerDTO, RequestDTO requestDTO) {
		
		FavoriteSeller favoriteSeller = favoriteSellerDao.getById(favoriteSellerDTO.getFavoriteSellerId());

		favoriteSeller.setFavoriteSellerId(ControllerUtils.setValue(favoriteSeller.getFavoriteSellerId(), favoriteSellerDTO.getFavoriteSellerId()));



        favoriteSeller = favoriteSellerDao.save(favoriteSeller);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public FavoriteSellerDTO getFavoriteSellerDTOById(Integer favoriteSellerId) {
	
		FavoriteSeller favoriteSeller = favoriteSellerDao.getById(favoriteSellerId);
			
		
		FavoriteSellerConvertCriteriaDTO convertCriteria = new FavoriteSellerConvertCriteriaDTO();
		return(this.convertFavoriteSellerToFavoriteSellerDTO(favoriteSeller,convertCriteria));
	}







}
