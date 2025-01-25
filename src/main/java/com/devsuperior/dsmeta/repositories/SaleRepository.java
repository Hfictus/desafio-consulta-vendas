package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

//import com.devsuperior.dsmeta.projections.SalesReportMinProjection;
//import com.devsuperior.dsmeta.projections.SalesSummaryMinProjection;


public interface SaleRepository extends JpaRepository<Sale, Long> {

	/*Report SQL
	@Query(nativeQuery = true, value = "SELECT tb_sales.id AS id, tb_sales.date AS date, tb_sales.amount AS amount, tb_seller.name AS sellerName "
			+ "FROM tb_sales "
			+ "INNER JOIN tb_seller "
			+ "ON tb_sales.seller_id = tb_seller.id "
			+ "WHERE tb_sales.date BETWEEN :min AND :max "
			+ "AND UPPER(tb_seller.name) LIKE UPPER(CONCAT('%', :name, '%')) "
			+ "ORDER BY tb_sales.id ASC")
	Page<SalesReportMinProjection> searchReport(LocalDate min, LocalDate max, String name, Pageable pageable);
	*/
	
	//Report JPQL
	@Query("SELECT new com.devsuperior.dsmeta.dto.SalesReportDTO(obj.id, obj.date, obj.amount, obj.seller.name) "
			+ "FROM Sale obj "
			+ "WHERE obj.date BETWEEN :minDate AND :maxDate "
			+ "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
	Page<SalesReportDTO> searchReport(
			@Param("minDate") LocalDate minDate,
			@Param("maxDate") LocalDate maxDate,
			@Param("name") String name,
			Pageable pageable);
	
	
	//Summary JPQL
	
	/*Summary SQL
	@Query(nativeQuery = true, value = "SELECT tb_seller.name AS sellerName, SUM(tb_sales.amount) AS total "
			+ "FROM tb_sales "
			+ "INNER JOIN tb_seller "
			+ "ON tb_sales.seller_id = tb_seller.id "
			+ "WHERE tb_sales.date BETWEEN :min AND :max "
			+ "GROUP BY tb_seller.name "
			+ "ORDER BY tb_seller.name")
	List<SalesSummaryMinProjection> searchSummary(LocalDate min, LocalDate max);
	*/
	
	//Summary JPQL
	@Query("SELECT new com.devsuperior.dsmeta.dto.SalesSummaryDTO(obj.seller.name, SUM(obj.amount)) "
			+ "FROM Sale obj "
			+ "WHERE obj.date BETWEEN :minDate AND :maxDate "
			+ "GROUP BY obj.seller.name ")
	List<SalesSummaryDTO> searchSummary(
			@Param("minDate") LocalDate minDate,
			@Param("maxDate") LocalDate maxDate);
	

}
