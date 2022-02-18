package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.RekapPenjualanStore;

@Repository
public interface RekapPenjualanStoreRepository extends JpaRepository<RekapPenjualanStore, Long> {
	@Query(value = "SELECT * FROM rekap_penjualan_store WHERE rowstatus = 1 AND id_store = :id_store", nativeQuery = true)
	List<RekapPenjualanStore> all(int id_store);
}
