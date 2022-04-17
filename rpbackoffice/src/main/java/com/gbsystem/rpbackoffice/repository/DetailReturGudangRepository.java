package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.DetailReturGudang;

@Repository
public interface DetailReturGudangRepository extends JpaRepository<DetailReturGudang, Long> {

	List<DetailReturGudang> findByRowstatus(@Param("rowstatus") int rowstatus); 
	
	@Query(value = "SELECT * FROM detail_retur_gudang WHERE rowstatus = 1 AND ( pengiriman_code LIKE %:keyword% OR sku_code LIKE %:keyword% )", nativeQuery = true)
	List<DetailReturGudang> search(String keyword);
	
	@Query(value = "SELECT * FROM detail_retur_gudang WHERE rowstatus = 1 AND pengiriman_code = (?1)", nativeQuery = true)
	List<DetailReturGudang> all(String pengiriman_code);
	
	@Query(value = "SELECT * FROM detail_retur_gudang WHERE rowstatus = 1 AND retur_gudang_id = :retur_gudang_id", nativeQuery = true)
	DetailReturGudang getByRetur_gudang_id(Long retur_gudang_id);
}
