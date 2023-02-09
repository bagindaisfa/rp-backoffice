package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.ProformaInvoice;

@Repository
public interface ProformaInvoiceRepository extends JpaRepository<ProformaInvoice, Long> {
	List<ProformaInvoice> findByRowstatus(@Param("rowstatus") int rowstatus); 
	
	@Query(value = "SELECT COUNT(id) FROM proforma_invoice WHERE rowstatus = :rowstatus", nativeQuery = true)
	Double counting(int rowstatus);
	
	@Query(value = "SELECT * FROM proforma_invoice WHERE rowstatus=1 AND (pi_no LIKE %:keyword% OR id_transaksi LIKE %:keyword% OR id_office LIKE %:keyword% OR lokasi_office LIKE %:keyword%)", nativeQuery = true)
	List<ProformaInvoice> search(String keyword);
	
	@Query(value = "SELECT * FROM proforma_invoice WHERE rowstatus=1 AND pi_no =:pi_no and id_transaksi is null", nativeQuery = true)
	ProformaInvoice getPi(String pi_no);
	
	@Query(value = "SELECT * FROM proforma_invoice WHERE rowstatus=1 AND pi_no =:pi_no", nativeQuery = true)
	ProformaInvoice getPiOld(String pi_no);
}
