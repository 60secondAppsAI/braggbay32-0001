package com.braggbay32.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay32.domain.FavoriteSeller;
import com.braggbay32.dto.FavoriteSellerDTO;
import com.braggbay32.dto.FavoriteSellerSearchDTO;
import com.braggbay32.dto.FavoriteSellerPageDTO;
import com.braggbay32.dto.FavoriteSellerConvertCriteriaDTO;
import com.braggbay32.service.GenericService;
import com.braggbay32.dto.common.RequestDTO;
import com.braggbay32.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FavoriteSellerService extends GenericService<FavoriteSeller, Integer> {

	List<FavoriteSeller> findAll();

	ResultDTO addFavoriteSeller(FavoriteSellerDTO favoriteSellerDTO, RequestDTO requestDTO);

	ResultDTO updateFavoriteSeller(FavoriteSellerDTO favoriteSellerDTO, RequestDTO requestDTO);

    Page<FavoriteSeller> getAllFavoriteSellers(Pageable pageable);

    Page<FavoriteSeller> getAllFavoriteSellers(Specification<FavoriteSeller> spec, Pageable pageable);

	ResponseEntity<FavoriteSellerPageDTO> getFavoriteSellers(FavoriteSellerSearchDTO favoriteSellerSearchDTO);
	
	List<FavoriteSellerDTO> convertFavoriteSellersToFavoriteSellerDTOs(List<FavoriteSeller> favoriteSellers, FavoriteSellerConvertCriteriaDTO convertCriteria);

	FavoriteSellerDTO getFavoriteSellerDTOById(Integer favoriteSellerId);







}





