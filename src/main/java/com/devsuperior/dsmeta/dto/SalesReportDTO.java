
	package com.devsuperior.dsmeta.dto;
	
	import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SalesReportMinProjection;
	
	public class SalesReportDTO {
		
		private Long id;
		private LocalDate date;
		private Double amount;
		private String sellerName;
		
		
		public SalesReportDTO() {
			
		}
				
		public SalesReportDTO(Long id, LocalDate date, Double amount, String sellerName) {
			this.id = id;
			this.date = date;
			this.amount = amount;
			this.sellerName = sellerName;
		}



		public SalesReportDTO(Sale sale) {
			id = sale.getId();
			date = sale.getDate();
			amount = sale.getAmount();
			sellerName = sale.getSeller().getName();
		}
		
		public SalesReportDTO(SalesReportMinProjection projection) {
			id = projection.getId();
			date = projection.getDate();
			amount = projection.getAmount();
			sellerName = projection.getName();
		}
		
		public Long getId() {
			return id;
		}
		
		
		public void setId(Long id) {
			this.id = id;
		}
		
		
		public String getDate() {
			return date.format(DateTimeFormatter.ISO_DATE);
		}
		
		
		public void setDate(LocalDate date) {
			this.date = date;
		}
		
		
		public Double getAmount() {
			return amount;
		}
		
		
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		
		
		public String getSellerName() {
			return sellerName;
		}
		
		
		public void setSellerName(String sellerName) {
			this.sellerName = sellerName;
		}
		
	}