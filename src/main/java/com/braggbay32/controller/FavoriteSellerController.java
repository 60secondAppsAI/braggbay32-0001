package com.braggbay32.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.braggbay32.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.braggbay32.domain.FavoriteSeller;
import com.braggbay32.dto.FavoriteSellerDTO;
import com.braggbay32.dto.FavoriteSellerSearchDTO;
import com.braggbay32.dto.FavoriteSellerPageDTO;
import com.braggbay32.service.FavoriteSellerService;
import com.braggbay32.dto.common.RequestDTO;
import com.braggbay32.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/favoriteSeller")
@RestController
public class FavoriteSellerController {

	private final static Logger logger = LoggerFactory.getLogger(FavoriteSellerController.class);

	@Autowired
	FavoriteSellerService favoriteSellerService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<FavoriteSeller> getAll() {

		List<FavoriteSeller> favoriteSellers = favoriteSellerService.findAll();
		
		return favoriteSellers;	
	}

	@GetMapping(value = "/{favoriteSellerId}")
	@ResponseBody
	public FavoriteSellerDTO getFavoriteSeller(@PathVariable Integer favoriteSellerId) {
		
		return (favoriteSellerService.getFavoriteSellerDTOById(favoriteSellerId));
	}

 	@RequestMapping(value = "/addFavoriteSeller", method = RequestMethod.POST)
	public ResponseEntity<?> addFavoriteSeller(@RequestBody FavoriteSellerDTO favoriteSellerDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = favoriteSellerService.addFavoriteSeller(favoriteSellerDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/favoriteSellers")
	public ResponseEntity<FavoriteSellerPageDTO> getFavoriteSellers(FavoriteSellerSearchDTO favoriteSellerSearchDTO) {
 
		return favoriteSellerService.getFavoriteSellers(favoriteSellerSearchDTO);
	}	

	@RequestMapping(value = "/updateFavoriteSeller", method = RequestMethod.POST)
	public ResponseEntity<?> updateFavoriteSeller(@RequestBody FavoriteSellerDTO favoriteSellerDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = favoriteSellerService.updateFavoriteSeller(favoriteSellerDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
