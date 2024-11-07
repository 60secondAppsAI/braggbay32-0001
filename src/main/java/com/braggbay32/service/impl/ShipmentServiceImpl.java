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
import com.braggbay32.dao.ShipmentDAO;
import com.braggbay32.domain.Shipment;
import com.braggbay32.dto.ShipmentDTO;
import com.braggbay32.dto.ShipmentSearchDTO;
import com.braggbay32.dto.ShipmentPageDTO;
import com.braggbay32.dto.ShipmentConvertCriteriaDTO;
import com.braggbay32.dto.common.RequestDTO;
import com.braggbay32.dto.common.ResultDTO;
import com.braggbay32.service.ShipmentService;
import com.braggbay32.util.ControllerUtils;





@Service
public class ShipmentServiceImpl extends GenericServiceImpl<Shipment, Integer> implements ShipmentService {

    private final static Logger logger = LoggerFactory.getLogger(ShipmentServiceImpl.class);

	@Autowired
	ShipmentDAO shipmentDao;

	


	@Override
	public GenericDAO<Shipment, Integer> getDAO() {
		return (GenericDAO<Shipment, Integer>) shipmentDao;
	}
	
	public List<Shipment> findAll () {
		List<Shipment> shipments = shipmentDao.findAll();
		
		return shipments;	
		
	}

	public ResultDTO addShipment(ShipmentDTO shipmentDTO, RequestDTO requestDTO) {

		Shipment shipment = new Shipment();

		shipment.setShipmentId(shipmentDTO.getShipmentId());


		shipment.setTrackingNumber(shipmentDTO.getTrackingNumber());


		shipment.setShipmentDate(shipmentDTO.getShipmentDate());


		shipment.setDeliveryDate(shipmentDTO.getDeliveryDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		shipment = shipmentDao.save(shipment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Shipment> getAllShipments(Pageable pageable) {
		return shipmentDao.findAll(pageable);
	}

	public Page<Shipment> getAllShipments(Specification<Shipment> spec, Pageable pageable) {
		return shipmentDao.findAll(spec, pageable);
	}

	public ResponseEntity<ShipmentPageDTO> getShipments(ShipmentSearchDTO shipmentSearchDTO) {
	
			Integer shipmentId = shipmentSearchDTO.getShipmentId(); 
 			String trackingNumber = shipmentSearchDTO.getTrackingNumber(); 
     			String sortBy = shipmentSearchDTO.getSortBy();
			String sortOrder = shipmentSearchDTO.getSortOrder();
			String searchQuery = shipmentSearchDTO.getSearchQuery();
			Integer page = shipmentSearchDTO.getPage();
			Integer size = shipmentSearchDTO.getSize();

	        Specification<Shipment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, shipmentId, "shipmentId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, trackingNumber, "trackingNumber"); 
			
 			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("trackingNumber")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Shipment> shipments = this.getAllShipments(spec, pageable);
		
		//System.out.println(String.valueOf(shipments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(shipments.getTotalPages()));
		
		List<Shipment> shipmentsList = shipments.getContent();
		
		ShipmentConvertCriteriaDTO convertCriteria = new ShipmentConvertCriteriaDTO();
		List<ShipmentDTO> shipmentDTOs = this.convertShipmentsToShipmentDTOs(shipmentsList,convertCriteria);
		
		ShipmentPageDTO shipmentPageDTO = new ShipmentPageDTO();
		shipmentPageDTO.setShipments(shipmentDTOs);
		shipmentPageDTO.setTotalElements(shipments.getTotalElements());
		return ResponseEntity.ok(shipmentPageDTO);
	}

	public List<ShipmentDTO> convertShipmentsToShipmentDTOs(List<Shipment> shipments, ShipmentConvertCriteriaDTO convertCriteria) {
		
		List<ShipmentDTO> shipmentDTOs = new ArrayList<ShipmentDTO>();
		
		for (Shipment shipment : shipments) {
			shipmentDTOs.add(convertShipmentToShipmentDTO(shipment,convertCriteria));
		}
		
		return shipmentDTOs;

	}
	
	public ShipmentDTO convertShipmentToShipmentDTO(Shipment shipment, ShipmentConvertCriteriaDTO convertCriteria) {
		
		ShipmentDTO shipmentDTO = new ShipmentDTO();
		
		shipmentDTO.setShipmentId(shipment.getShipmentId());

	
		shipmentDTO.setTrackingNumber(shipment.getTrackingNumber());

	
		shipmentDTO.setShipmentDate(shipment.getShipmentDate());

	
		shipmentDTO.setDeliveryDate(shipment.getDeliveryDate());

	

		
		return shipmentDTO;
	}

	public ResultDTO updateShipment(ShipmentDTO shipmentDTO, RequestDTO requestDTO) {
		
		Shipment shipment = shipmentDao.getById(shipmentDTO.getShipmentId());

		shipment.setShipmentId(ControllerUtils.setValue(shipment.getShipmentId(), shipmentDTO.getShipmentId()));

		shipment.setTrackingNumber(ControllerUtils.setValue(shipment.getTrackingNumber(), shipmentDTO.getTrackingNumber()));

		shipment.setShipmentDate(ControllerUtils.setValue(shipment.getShipmentDate(), shipmentDTO.getShipmentDate()));

		shipment.setDeliveryDate(ControllerUtils.setValue(shipment.getDeliveryDate(), shipmentDTO.getDeliveryDate()));



        shipment = shipmentDao.save(shipment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ShipmentDTO getShipmentDTOById(Integer shipmentId) {
	
		Shipment shipment = shipmentDao.getById(shipmentId);
			
		
		ShipmentConvertCriteriaDTO convertCriteria = new ShipmentConvertCriteriaDTO();
		return(this.convertShipmentToShipmentDTO(shipment,convertCriteria));
	}







}
