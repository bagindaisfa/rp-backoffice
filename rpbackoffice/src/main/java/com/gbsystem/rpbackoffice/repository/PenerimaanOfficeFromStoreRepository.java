package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenerimaanOfficeFromStore;

@Repository
public interface PenerimaanOfficeFromStoreRepository extends JpaRepository<PenerimaanOfficeFromStore, Long> {
	@Query(value = "SELECT "
			+ "A.id, "
			+ "A.id_office, "
			+ "A.id_store, "
			+ "A.lokasi_office, "
			+ "A.lokasi_store, "
			+ "A.penerimaan_code, "
			+ "A.retur_code, "
			+ "A.rowstatus, "
			+ "A.tanggal_penerimaan, "
			+ "(SELECT SUM(kuantitas) from detail_penerimaan_office_from_store WHERE penerimaan_office_from_store_id=A.id and rowstatus=1) AS qty "
			+ "FROM penerimaan_office_from_store A WHERE A.rowstatus = :rowstatus", nativeQuery = true)
	List<PenerimaanOfficeFromStore> findByRowstatus(int rowstatus);
	
	@Query(value = "SELECT "
			+ "A.id, "
			+ "A.id_office, "
			+ "A.id_store, "
			+ "A.lokasi_office, "
			+ "A.lokasi_store, "
			+ "A.penerimaan_code, "
			+ "A.retur_code, "
			+ "A.rowstatus, "
			+ "A.tanggal_penerimaan, "
			+ "(SELECT SUM(kuantitas) from detail_penerimaan_office_from_store WHERE penerimaan_office_from_store_id=A.id and rowstatus=1) AS qty "
			+ "FROM penerimaan_office_from_store A WHERE A.rowstatus = 1 AND "
			+ "(A.lokasi_office LIKE %:keyword% OR "
			+ "A.retur_code LIKE %:keyword% OR "
			+ "A.penerimaan_code LIKE %:keyword% OR "
			+ "A.lokasi_store LIKE %:keyword% )", nativeQuery = true)
	List<PenerimaanOfficeFromStore> search(String keyword);
	
	@Query(value = "SELECT * FROM penerimaan_office_from_store WHERE rowstatus = 1 AND retur_code = :retur_code", nativeQuery = true)
	PenerimaanOfficeFromStore getByRetur_code(String retur_code);
	
	@Query(value = "delete from penerimaan_office_from_store where penerimaan_code=:penerimaan_code", nativeQuery = true)
	PenerimaanOfficeFromStore deletePenerimaan(String penerimaan_code);
	
	@Query(value = "SELECT * FROM penerimaan_office_from_store WHERE rowstatus = 1 AND penerimaan_code = :penerimaan_code", nativeQuery = true)
	PenerimaanOfficeFromStore getByPenerimaan_code(String penerimaan_code);
}
