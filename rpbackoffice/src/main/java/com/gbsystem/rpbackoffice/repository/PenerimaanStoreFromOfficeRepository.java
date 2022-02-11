package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenerimaanStoreFromOffice;

@Repository
public interface PenerimaanStoreFromOfficeRepository extends JpaRepository<PenerimaanStoreFromOffice, Long> {
	
List<PenerimaanStoreFromOffice> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM penerimaan_store_from_office WHERE rowstatus = 1 AND  "
			+ "MATCH(nama_barang, lokasi_store, lokasi_office) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<PenerimaanStoreFromOffice> search(String keyword);

}
