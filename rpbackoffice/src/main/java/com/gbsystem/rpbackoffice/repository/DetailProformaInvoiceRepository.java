package com.gbsystem.rpbackoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.DetailProformaInvoice;

@Repository
public interface DetailProformaInvoiceRepository extends JpaRepository<DetailProformaInvoice, Long> {

	@Query(value = "SELECT SUM(total) AS total FROM detail_proforma_invoice WHERE rowstatus = :rowstatus", nativeQuery = true)
	Double total(int rowstatus);
	
	@Query(value = "SELECT SUM(B.hpp) AS total FROM detail_proforma_invoice A LEFT JOIN master_product B ON A.artikel=B.artikel_product WHERE A.rowstatus = :rowstatus", nativeQuery = true)
	Double totalHpp(int rowstatus);
}
