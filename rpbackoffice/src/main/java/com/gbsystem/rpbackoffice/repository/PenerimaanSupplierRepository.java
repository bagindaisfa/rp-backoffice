package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenerimaanSupplier;

@Repository
public interface PenerimaanSupplierRepository extends JpaRepository<PenerimaanSupplier, Long> {
	@Query(value = "SELECT "
			+ "A.id, "
			+ "A.id_office, "
			+ "A.id_supplier, "
			+ "A.lokasi_office, "
			+ "A.nama_supplier, "
			+ "A.penerimaan_code, "
			+ "A.pembelian_code, "
			+ "A.rowstatus, "
			+ "A.tanggal_penerimaan, "
			+ "(SELECT SUM(kuantitas) from detail_penerimaan_supplier WHERE penerimaan_supplier_id=A.id and rowstatus=1) AS qty "
			+ "FROM penerimaan_supplier A WHERE A.rowstatus = :rowstatus", nativeQuery = true)
	List<PenerimaanSupplier> findByRowstatus(int rowstatus);
	
	@Query(value = "SELECT "
			+ "A.id, "
			+ "A.id_office, "
			+ "A.id_supplier, "
			+ "A.lokasi_office, "
			+ "A.nama_supplier, "
			+ "A.penerimaan_code, "
			+ "A.pembelian_code, "
			+ "A.rowstatus, "
			+ "A.tanggal_penerimaan, "
			+ "(SELECT SUM(kuantitas) from detail_penerimaan_supplier WHERE penerimaan_supplier_id=A.id and rowstatus=1) AS qty "
			+ "FROM penerimaan_supplier A WHERE A.rowstatus = 1 AND "
			+ "(A.penerimaan_code LIKE %:keyword% OR "
			+ "A.pembelian_code LIKE %:keyword% OR "
			+ "A.nama_supplier LIKE %:keyword%)", nativeQuery = true)
	List<PenerimaanSupplier> search(String keyword);

}
