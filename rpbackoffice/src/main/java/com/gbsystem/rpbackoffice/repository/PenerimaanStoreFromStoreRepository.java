package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenerimaanStoreFromStore;

@Repository
public interface PenerimaanStoreFromStoreRepository extends JpaRepository<PenerimaanStoreFromStore, Long> {
	@Query(value = "SELECT "
			+ "A.id, "
			+ "A.id_store_asal, "
			+ "A.id_store_tujuan, "
			+ "A.lokasi_store_asal, "
			+ "A.lokasi_store_tujuan, "
			+ "A.penerimaan_code, "
			+ "A.pengiriman_code, "
			+ "A.rowstatus, "
			+ "A.tanggal_penerimaan, "
			+ "(SELECT SUM(kuantitas) from detail_penerimaan_store_from_store WHERE penerimaan_store_from_store_id=A.id and rowstatus=1) AS qty "
			+ "FROM penerimaan_store_from_store A WHERE A.rowstatus = :rowstatus", nativeQuery = true)
	List<PenerimaanStoreFromStore> findByRowstatus(int rowstatus);
	
	@Query(value = "SELECT "
			+ "A.id, "
			+ "A.id_store_asal, "
			+ "A.id_store_tujuan, "
			+ "A.lokasi_store_asal, "
			+ "A.lokasi_store_tujuan, "
			+ "A.penerimaan_code, "
			+ "A.pengiriman_code, "
			+ "A.rowstatus, "
			+ "A.tanggal_penerimaan, "
			+ "(SELECT SUM(kuantitas) from detail_penerimaan_store_from_store WHERE penerimaan_store_from_store_id=A.id and rowstatus=1) AS qty "
			+ "FROM penerimaan_store_from_store A WHERE A.rowstatus = 1 AND "
			+ "(A.lokasi_store_asal LIKE %:keyword% OR "
			+ "A.penerimaan_code LIKE %:keyword% OR "
			+ "A.pengiriman_code LIKE %:keyword% OR "
			+ "A.lokasi_store_tujuan LIKE %:keyword%)", nativeQuery = true)
	List<PenerimaanStoreFromStore> search(String keyword);
	
	@Query(value = "SELECT * FROM penerimaan_store_from_store WHERE rowstatus = 1 AND pengiriman_code = :pengiriman_code", nativeQuery = true)
	PenerimaanStoreFromStore findByPengirimanCode(String pengiriman_code);

}
