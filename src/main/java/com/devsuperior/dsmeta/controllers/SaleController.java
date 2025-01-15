package com.devsuperior.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
/*
	@GetMapping(value = "/report")
	public ResponseEntity<Page<SalesReportDTO>> getReport() {
		// TODO
		return null;
	}
*/
	@GetMapping(value = "/report")
	public ResponseEntity<Page<SalesReportDTO>> getReport(
			@RequestParam(value = "minDate", defaultValue = "2022-01-01") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "2022-06-30") String maxDate,
			Pageable pageable) {
		Page<SalesReportDTO> dto = service.getReport(minDate, maxDate, pageable);
		return ResponseEntity.ok(dto);
	}
		
	
	@GetMapping(value = "/summary")
	public ResponseEntity<List<SalesSummaryDTO>> getSummary() {
		List<SalesSummaryDTO> dto = service.getSummary();
		return ResponseEntity.ok(dto);
	}
}
