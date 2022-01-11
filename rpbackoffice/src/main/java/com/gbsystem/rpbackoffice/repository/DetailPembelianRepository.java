package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.DetailPembelian;

@Repository
public interface DetailPembelianRepository extends JpaRepository<DetailPembelian, Long> {
	List<DetailPembelian> findByRowstatus(@Param("rowstatus") int rowstatus); 
	
	@Query(value = "SELECT * FROM detail_pembelian WHERE rowstatus = 1"
			+ "MATCH(pembelian_code) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<DetailPembelian> search(String keyword);
	
	@Query(value = "SELECT * FROM detail_pembelian WHERE rowstatus = 1 AND pembelian_code = (?1)", nativeQuery = true)
	List<DetailPembelian> all(String pembelian_code);
	
	@Query(value = "SELECT * FROM detail_pembelian WHERE rowstatus = 1 AND pembelian_id = :pembelian_id", nativeQuery = true)
	DetailPembelian getByPembelian_id(Long pembelian_id);
}
