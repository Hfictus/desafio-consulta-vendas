package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SalesSummaryMinProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public Page<SalesReportDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		Page<SalesReportDTO> dto;
		if(!minDate.equals("") || !maxDate.equals("")) {
			if(name.equals("")) {
				name = " ";
				dto = repository.searchReport1(processMinDate(minDate, maxDate), processMaxDate(maxDate), pageable);
				for(SalesReportDTO obj : dto) {
					obj.setSellerName(name);
				}
			}else {
				dto = repository.searchReport2(processMinDate(minDate, maxDate), processMaxDate(maxDate), name, pageable);
			}
		}else {
			dto = repository.searchReport2(todayMinusYear(today()), today(), name, pageable);
		}
				
		return dto;
	}
	
	
	//getSummary(String minDate, String maxDate) -> JPQL
	public List<SalesSummaryDTO> getSummary() {

		//LocalDate startDate = LocalDate.parse(minDate);
		//LocalDate endDate = LocalDate.parse(maxDate);
		List<SalesSummaryMinProjection> list = repository.searchSummary();
		return list.stream().map(x -> new SalesSummaryDTO(x)).collect(Collectors.toList());
	}

	
	private LocalDate processMinDate(String minDate, String maxDate) {
		return (minDate.equals("")) ? todayMinusYear(LocalDate.parse(maxDate)) : LocalDate.parse(minDate);
	}
	private LocalDate processMaxDate(String maxDate) {
		return (maxDate.equals("")) ? today() : LocalDate.parse(maxDate);
	}
	private LocalDate today() {
		return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	}
	private LocalDate todayMinusYear(LocalDate maxDate) {
		return maxDate.minusYears(1L);
	}
	
	
}
