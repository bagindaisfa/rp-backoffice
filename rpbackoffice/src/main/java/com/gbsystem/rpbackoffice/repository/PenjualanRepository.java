package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.Penjualan;

@Repository
public interface PenjualanRepository extends JpaRepository<Penjualan, Long> {
	List<Penjualan> findByRowstatus(@Param("rowstatus") int rowstatus); 
	
	@Query(value = "SELECT * FROM penjualan WHERE "
			+ "MATCH(id_transaksi, id_store, lokasi_store) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<Penjualan> search(String keyword);
}
