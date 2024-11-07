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

import com.braggbay32.domain.WishList;
import com.braggbay32.dto.WishListDTO;
import com.braggbay32.dto.WishListSearchDTO;
import com.braggbay32.dto.WishListPageDTO;
import com.braggbay32.service.WishListService;
import com.braggbay32.dto.common.RequestDTO;
import com.braggbay32.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/wishList")
@RestController
public class WishListController {

	private final static Logger logger = LoggerFactory.getLogger(WishListController.class);

	@Autowired
	WishListService wishListService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<WishList> getAll() {

		List<WishList> wishLists = wishListService.findAll();
		
		return wishLists;	
	}

	@GetMapping(value = "/{wishListId}")
	@ResponseBody
	public WishListDTO getWishList(@PathVariable Integer wishListId) {
		
		return (wishListService.getWishListDTOById(wishListId));
	}

 	@RequestMapping(value = "/addWishList", method = RequestMethod.POST)
	public ResponseEntity<?> addWishList(@RequestBody WishListDTO wishListDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = wishListService.addWishList(wishListDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/wishLists")
	public ResponseEntity<WishListPageDTO> getWishLists(WishListSearchDTO wishListSearchDTO) {
 
		return wishListService.getWishLists(wishListSearchDTO);
	}	

	@RequestMapping(value = "/updateWishList", method = RequestMethod.POST)
	public ResponseEntity<?> updateWishList(@RequestBody WishListDTO wishListDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = wishListService.updateWishList(wishListDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
