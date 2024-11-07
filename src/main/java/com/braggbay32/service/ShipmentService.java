package com.braggbay32.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay32.domain.Shipment;
import com.braggbay32.dto.ShipmentDTO;
import com.braggbay32.dto.ShipmentSearchDTO;
import com.braggbay32.dto.ShipmentPageDTO;
import com.braggbay32.dto.ShipmentConvertCriteriaDTO;
import com.braggbay32.service.GenericService;
import com.braggbay32.dto.common.RequestDTO;
import com.braggbay32.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ShipmentService extends GenericService<Shipment, Integer> {

	List<Shipment> findAll();

	ResultDTO addShipment(ShipmentDTO shipmentDTO, RequestDTO requestDTO);

	ResultDTO updateShipment(ShipmentDTO shipmentDTO, RequestDTO requestDTO);

    Page<Shipment> getAllShipments(Pageable pageable);

    Page<Shipment> getAllShipments(Specification<Shipment> spec, Pageable pageable);

	ResponseEntity<ShipmentPageDTO> getShipments(ShipmentSearchDTO shipmentSearchDTO);
	
	List<ShipmentDTO> convertShipmentsToShipmentDTOs(List<Shipment> shipments, ShipmentConvertCriteriaDTO convertCriteria);

	ShipmentDTO getShipmentDTOById(Integer shipmentId);







}





