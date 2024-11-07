package com.braggbay32.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay32.domain.WishlistItem;
import com.braggbay32.dto.WishlistItemDTO;
import com.braggbay32.dto.WishlistItemSearchDTO;
import com.braggbay32.dto.WishlistItemPageDTO;
import com.braggbay32.dto.WishlistItemConvertCriteriaDTO;
import com.braggbay32.service.GenericService;
import com.braggbay32.dto.common.RequestDTO;
import com.braggbay32.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface WishlistItemService extends GenericService<WishlistItem, Integer> {

	List<WishlistItem> findAll();

	ResultDTO addWishlistItem(WishlistItemDTO wishlistItemDTO, RequestDTO requestDTO);

	ResultDTO updateWishlistItem(WishlistItemDTO wishlistItemDTO, RequestDTO requestDTO);

    Page<WishlistItem> getAllWishlistItems(Pageable pageable);

    Page<WishlistItem> getAllWishlistItems(Specification<WishlistItem> spec, Pageable pageable);

	ResponseEntity<WishlistItemPageDTO> getWishlistItems(WishlistItemSearchDTO wishlistItemSearchDTO);
	
	List<WishlistItemDTO> convertWishlistItemsToWishlistItemDTOs(List<WishlistItem> wishlistItems, WishlistItemConvertCriteriaDTO convertCriteria);

	WishlistItemDTO getWishlistItemDTOById(Integer wishlistItemId);







}





