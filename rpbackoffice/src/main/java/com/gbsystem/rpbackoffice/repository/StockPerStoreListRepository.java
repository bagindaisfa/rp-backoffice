package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gbsystem.rpbackoffice.entities.StockPerStoreList;

public interface StockPerStoreListRepository extends JpaRepository<StockPerStoreList, Long> {
	
	@Query(value = "SELECT id,id_store,lokasi_store,sum(kuantitas) as total_per_store FROM stock_store WHERE rowstatus = 1 and id_store != 8 group by id_store,lokasi_store", nativeQuery = true)
	List<StockPerStoreList> allStock();
	
	@Query(value = "SELECT id,id_store,lokasi_store,sum(kuantitas) as total_per_store FROM stock_store WHERE rowstatus = 1 and id_store != 8 AND kuantitas > 0 AND "
			+ "(sku_code LIKE %:keyword% OR "
			+ "artikel LIKE %:keyword% OR "
			+ "nama_barang LIKE %:keyword% OR "
			+ "type_name LIKE %:keyword% OR "
			+ "nama_kategori LIKE %:keyword% OR "
			+ "lokasi_store LIKE %:keyword%) group by id_store,lokasi_store", nativeQuery = true)
	List<StockPerStoreList> search(String keyword);

}
