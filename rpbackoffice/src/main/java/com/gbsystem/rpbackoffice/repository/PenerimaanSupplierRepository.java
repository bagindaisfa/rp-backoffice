package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenerimaanSupplier;

@Repository
public interface PenerimaanSupplierRepository extends JpaRepository<PenerimaanSupplier, Long> {
List<PenerimaanSupplier> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM penerimaan_supplier WHERE rowstatus = 1 AND (nama_barang LIKE %:keyword% OR nama_supplier LIKE %:keyword%)", nativeQuery = true)
	List<PenerimaanSupplier> search(String keyword);

}
