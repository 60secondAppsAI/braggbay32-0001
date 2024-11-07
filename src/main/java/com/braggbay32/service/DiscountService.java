package com.braggbay32.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay32.domain.Discount;
import com.braggbay32.dto.DiscountDTO;
import com.braggbay32.dto.DiscountSearchDTO;
import com.braggbay32.dto.DiscountPageDTO;
import com.braggbay32.dto.DiscountConvertCriteriaDTO;
import com.braggbay32.service.GenericService;
import com.braggbay32.dto.common.RequestDTO;
import com.braggbay32.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DiscountService extends GenericService<Discount, Integer> {

	List<Discount> findAll();

	ResultDTO addDiscount(DiscountDTO discountDTO, RequestDTO requestDTO);

	ResultDTO updateDiscount(DiscountDTO discountDTO, RequestDTO requestDTO);

    Page<Discount> getAllDiscounts(Pageable pageable);

    Page<Discount> getAllDiscounts(Specification<Discount> spec, Pageable pageable);

	ResponseEntity<DiscountPageDTO> getDiscounts(DiscountSearchDTO discountSearchDTO);
	
	List<DiscountDTO> convertDiscountsToDiscountDTOs(List<Discount> discounts, DiscountConvertCriteriaDTO convertCriteria);

	DiscountDTO getDiscountDTOById(Integer discountId);







}





