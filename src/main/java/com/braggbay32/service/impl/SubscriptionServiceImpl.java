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
import com.braggbay32.dao.SubscriptionDAO;
import com.braggbay32.domain.Subscription;
import com.braggbay32.dto.SubscriptionDTO;
import com.braggbay32.dto.SubscriptionSearchDTO;
import com.braggbay32.dto.SubscriptionPageDTO;
import com.braggbay32.dto.SubscriptionConvertCriteriaDTO;
import com.braggbay32.dto.common.RequestDTO;
import com.braggbay32.dto.common.ResultDTO;
import com.braggbay32.service.SubscriptionService;
import com.braggbay32.util.ControllerUtils;





@Service
public class SubscriptionServiceImpl extends GenericServiceImpl<Subscription, Integer> implements SubscriptionService {

    private final static Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

	@Autowired
	SubscriptionDAO subscriptionDao;

	


	@Override
	public GenericDAO<Subscription, Integer> getDAO() {
		return (GenericDAO<Subscription, Integer>) subscriptionDao;
	}
	
	public List<Subscription> findAll () {
		List<Subscription> subscriptions = subscriptionDao.findAll();
		
		return subscriptions;	
		
	}

	public ResultDTO addSubscription(SubscriptionDTO subscriptionDTO, RequestDTO requestDTO) {

		Subscription subscription = new Subscription();

		subscription.setSubscriptionId(subscriptionDTO.getSubscriptionId());


		subscription.setStartDate(subscriptionDTO.getStartDate());


		subscription.setEndDate(subscriptionDTO.getEndDate());


		subscription.setStatus(subscriptionDTO.getStatus());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		subscription = subscriptionDao.save(subscription);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Subscription> getAllSubscriptions(Pageable pageable) {
		return subscriptionDao.findAll(pageable);
	}

	public Page<Subscription> getAllSubscriptions(Specification<Subscription> spec, Pageable pageable) {
		return subscriptionDao.findAll(spec, pageable);
	}

	public ResponseEntity<SubscriptionPageDTO> getSubscriptions(SubscriptionSearchDTO subscriptionSearchDTO) {
	
			Integer subscriptionId = subscriptionSearchDTO.getSubscriptionId(); 
     			String status = subscriptionSearchDTO.getStatus(); 
 			String sortBy = subscriptionSearchDTO.getSortBy();
			String sortOrder = subscriptionSearchDTO.getSortOrder();
			String searchQuery = subscriptionSearchDTO.getSearchQuery();
			Integer page = subscriptionSearchDTO.getPage();
			Integer size = subscriptionSearchDTO.getSize();

	        Specification<Subscription> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, subscriptionId, "subscriptionId"); 
			
 			
 			
			spec = ControllerUtils.andIfNecessary(spec, status, "status"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("status")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Subscription> subscriptions = this.getAllSubscriptions(spec, pageable);
		
		//System.out.println(String.valueOf(subscriptions.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(subscriptions.getTotalPages()));
		
		List<Subscription> subscriptionsList = subscriptions.getContent();
		
		SubscriptionConvertCriteriaDTO convertCriteria = new SubscriptionConvertCriteriaDTO();
		List<SubscriptionDTO> subscriptionDTOs = this.convertSubscriptionsToSubscriptionDTOs(subscriptionsList,convertCriteria);
		
		SubscriptionPageDTO subscriptionPageDTO = new SubscriptionPageDTO();
		subscriptionPageDTO.setSubscriptions(subscriptionDTOs);
		subscriptionPageDTO.setTotalElements(subscriptions.getTotalElements());
		return ResponseEntity.ok(subscriptionPageDTO);
	}

	public List<SubscriptionDTO> convertSubscriptionsToSubscriptionDTOs(List<Subscription> subscriptions, SubscriptionConvertCriteriaDTO convertCriteria) {
		
		List<SubscriptionDTO> subscriptionDTOs = new ArrayList<SubscriptionDTO>();
		
		for (Subscription subscription : subscriptions) {
			subscriptionDTOs.add(convertSubscriptionToSubscriptionDTO(subscription,convertCriteria));
		}
		
		return subscriptionDTOs;

	}
	
	public SubscriptionDTO convertSubscriptionToSubscriptionDTO(Subscription subscription, SubscriptionConvertCriteriaDTO convertCriteria) {
		
		SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
		
		subscriptionDTO.setSubscriptionId(subscription.getSubscriptionId());

	
		subscriptionDTO.setStartDate(subscription.getStartDate());

	
		subscriptionDTO.setEndDate(subscription.getEndDate());

	
		subscriptionDTO.setStatus(subscription.getStatus());

	

		
		return subscriptionDTO;
	}

	public ResultDTO updateSubscription(SubscriptionDTO subscriptionDTO, RequestDTO requestDTO) {
		
		Subscription subscription = subscriptionDao.getById(subscriptionDTO.getSubscriptionId());

		subscription.setSubscriptionId(ControllerUtils.setValue(subscription.getSubscriptionId(), subscriptionDTO.getSubscriptionId()));

		subscription.setStartDate(ControllerUtils.setValue(subscription.getStartDate(), subscriptionDTO.getStartDate()));

		subscription.setEndDate(ControllerUtils.setValue(subscription.getEndDate(), subscriptionDTO.getEndDate()));

		subscription.setStatus(ControllerUtils.setValue(subscription.getStatus(), subscriptionDTO.getStatus()));



        subscription = subscriptionDao.save(subscription);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SubscriptionDTO getSubscriptionDTOById(Integer subscriptionId) {
	
		Subscription subscription = subscriptionDao.getById(subscriptionId);
			
		
		SubscriptionConvertCriteriaDTO convertCriteria = new SubscriptionConvertCriteriaDTO();
		return(this.convertSubscriptionToSubscriptionDTO(subscription,convertCriteria));
	}







}
