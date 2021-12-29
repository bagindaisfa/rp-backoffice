package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenerimaanStore;

@Repository
public interface PenerimaanStoreRepository extends JpaRepository<PenerimaanStore, Long> {
List<PenerimaanStore> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM penerimaan_store WHERE rowstatus = 1 AND  "
			+ "MATCH(nama_barang, nama_pelanggan) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<PenerimaanStore> search(String keyword);

}
