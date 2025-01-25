package com.devsuperior.dsmeta.dto;

//import com.devsuperior.dsmeta.projections.SalesSummaryMinProjection;

public class SalesSummaryDTO {
	
	private String sellerName;
	private Double total;
	
	public SalesSummaryDTO() {
		
	}
	
	public SalesSummaryDTO(String sellerName, Double total) {
		this.sellerName = sellerName;
		this.total = total;
	}
	
	/*SQL
	public SalesSummaryDTO(SalesSummaryMinProjection projection) {
		sellerName = projection.getSellerName();
		total = projection.getTotal();
	}
	*/
	
	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
}
