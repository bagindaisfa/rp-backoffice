package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.TransferRequest;

@Repository
public interface TransferRequestRepository extends JpaRepository<TransferRequest, Long> {
	@Query(value = "select d.id, "
			+ "p.tanggal_pengiriman, "
			+ "p.lokasi_office, "
			+ "p.lokasi_store, "
			+ "d.artikel, "
			+ "d.nama_barang, "
			+ "d.kuantitas, "
			+ "d.harga_jual "
			+ "from pengiriman_office_to_store p "
			+ "left join detail_pengiriman_office_to_store d on p.id = d.pengiriman_office_to_store_id and d.rowstatus=1 "
			+ "where p.rowstatus=1 and p.pengiriman_code = :pengiriman_code", nativeQuery = true)
	List<TransferRequest> TransferRequest(String pengiriman_code);
}
