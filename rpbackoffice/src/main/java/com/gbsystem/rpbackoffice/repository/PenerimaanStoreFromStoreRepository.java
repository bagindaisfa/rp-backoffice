package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenerimaanStoreFromStore;

@Repository
public interface PenerimaanStoreFromStoreRepository extends JpaRepository<PenerimaanStoreFromStore, Long> {
	
List<PenerimaanStoreFromStore> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM penerimaan_store_from_store WHERE rowstatus = 1 AND  "
			+ "MATCH(nama_barang, lokasi_store_asal, lokasi_store_penerima) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<PenerimaanStoreFromStore> search(String keyword);

}
