package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gbsystem.rpbackoffice.entities.StockPerStoreList;

public interface StockPerStoreListRepository extends JpaRepository<StockPerStoreList, Long> {
	
	@Query(value = "SELECT id,id_store,lokasi_store,sum(kuantitas) as total_per_store FROM stock_store WHERE rowstatus = 1 group by id_store,lokasi_store", nativeQuery = true)
	List<StockPerStoreList> allStock();

}
