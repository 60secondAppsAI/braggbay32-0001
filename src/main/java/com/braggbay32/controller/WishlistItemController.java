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

import com.braggbay32.domain.WishlistItem;
import com.braggbay32.dto.WishlistItemDTO;
import com.braggbay32.dto.WishlistItemSearchDTO;
import com.braggbay32.dto.WishlistItemPageDTO;
import com.braggbay32.service.WishlistItemService;
import com.braggbay32.dto.common.RequestDTO;
import com.braggbay32.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/wishlistItem")
@RestController
public class WishlistItemController {

	private final static Logger logger = LoggerFactory.getLogger(WishlistItemController.class);

	@Autowired
	WishlistItemService wishlistItemService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<WishlistItem> getAll() {

		List<WishlistItem> wishlistItems = wishlistItemService.findAll();
		
		return wishlistItems;	
	}

	@GetMapping(value = "/{wishlistItemId}")
	@ResponseBody
	public WishlistItemDTO getWishlistItem(@PathVariable Integer wishlistItemId) {
		
		return (wishlistItemService.getWishlistItemDTOById(wishlistItemId));
	}

 	@RequestMapping(value = "/addWishlistItem", method = RequestMethod.POST)
	public ResponseEntity<?> addWishlistItem(@RequestBody WishlistItemDTO wishlistItemDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = wishlistItemService.addWishlistItem(wishlistItemDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/wishlistItems")
	public ResponseEntity<WishlistItemPageDTO> getWishlistItems(WishlistItemSearchDTO wishlistItemSearchDTO) {
 
		return wishlistItemService.getWishlistItems(wishlistItemSearchDTO);
	}	

	@RequestMapping(value = "/updateWishlistItem", method = RequestMethod.POST)
	public ResponseEntity<?> updateWishlistItem(@RequestBody WishlistItemDTO wishlistItemDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = wishlistItemService.updateWishlistItem(wishlistItemDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
