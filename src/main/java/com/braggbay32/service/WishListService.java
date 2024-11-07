package com.braggbay32.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay32.domain.WishList;
import com.braggbay32.dto.WishListDTO;
import com.braggbay32.dto.WishListSearchDTO;
import com.braggbay32.dto.WishListPageDTO;
import com.braggbay32.dto.WishListConvertCriteriaDTO;
import com.braggbay32.service.GenericService;
import com.braggbay32.dto.common.RequestDTO;
import com.braggbay32.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface WishListService extends GenericService<WishList, Integer> {

	List<WishList> findAll();

	ResultDTO addWishList(WishListDTO wishListDTO, RequestDTO requestDTO);

	ResultDTO updateWishList(WishListDTO wishListDTO, RequestDTO requestDTO);

    Page<WishList> getAllWishLists(Pageable pageable);

    Page<WishList> getAllWishLists(Specification<WishList> spec, Pageable pageable);

	ResponseEntity<WishListPageDTO> getWishLists(WishListSearchDTO wishListSearchDTO);
	
	List<WishListDTO> convertWishListsToWishListDTOs(List<WishList> wishLists, WishListConvertCriteriaDTO convertCriteria);

	WishListDTO getWishListDTOById(Integer wishListId);







}





