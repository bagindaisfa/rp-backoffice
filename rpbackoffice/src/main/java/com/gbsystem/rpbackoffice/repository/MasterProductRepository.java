package com.gbsystem.rpbackoffice.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.MasterProduct;

@Repository
public interface MasterProductRepository extends JpaRepository<MasterProduct, Long> {
	List<MasterProduct> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value= "select o from master_product o where artikel_product in :artikel_product", nativeQuery = true )
	List<MasterProduct> findByMasterProductArtikel_product(@Param("artikel_product") List<String> masterProductArtikel_productList);
	
	@Query(value = "SELECT * FROM master_product WHERE rowstatus = 1 AND "
			+ "MATCH(artikel_product, nama_product, type_name, nama_kategori) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<MasterProduct> search(String keyword);
}
