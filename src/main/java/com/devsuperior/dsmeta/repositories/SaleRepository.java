package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SalesSummaryMinProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	//teste:
	@Query(nativeQuery = true, value = "SELECT tb_seller.name, SUM(tb_sales.amount) "
			+ "FROM tb_sales "
			+ "INNER JOIN tb_seller "
			+ "ON tb_sales.seller_id = tb_seller.id "
			+ "WHERE tb_sales.date BETWEEN '2022-01-01' AND '2022-06-30' "
			+ "GROUP BY tb_seller.name "
			+ "ORDER BY tb_seller.name")
	List<SalesSummaryMinProjection> searchSummary();
	
	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SalesReportDTO(obj) "
			+ "FROM Sale obj "
			+ "WHERE obj.date BETWEEN :minDate AND :maxDate")
	Page<SalesReportDTO> searchReport(@Param("minDate") LocalDate minDate, @Param("maxDate") LocalDate maxDate, Pageable pageable);
	
}
