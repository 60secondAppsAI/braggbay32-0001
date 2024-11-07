package com.braggbay32.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay32.domain.Subscription;
import com.braggbay32.dto.SubscriptionDTO;
import com.braggbay32.dto.SubscriptionSearchDTO;
import com.braggbay32.dto.SubscriptionPageDTO;
import com.braggbay32.dto.SubscriptionConvertCriteriaDTO;
import com.braggbay32.service.GenericService;
import com.braggbay32.dto.common.RequestDTO;
import com.braggbay32.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SubscriptionService extends GenericService<Subscription, Integer> {

	List<Subscription> findAll();

	ResultDTO addSubscription(SubscriptionDTO subscriptionDTO, RequestDTO requestDTO);

	ResultDTO updateSubscription(SubscriptionDTO subscriptionDTO, RequestDTO requestDTO);

    Page<Subscription> getAllSubscriptions(Pageable pageable);

    Page<Subscription> getAllSubscriptions(Specification<Subscription> spec, Pageable pageable);

	ResponseEntity<SubscriptionPageDTO> getSubscriptions(SubscriptionSearchDTO subscriptionSearchDTO);
	
	List<SubscriptionDTO> convertSubscriptionsToSubscriptionDTOs(List<Subscription> subscriptions, SubscriptionConvertCriteriaDTO convertCriteria);

	SubscriptionDTO getSubscriptionDTOById(Integer subscriptionId);







}





