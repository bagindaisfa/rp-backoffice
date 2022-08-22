package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.MasterKategori;

@Repository
public interface MasterKategoriRepository extends JpaRepository<MasterKategori, Long> {
	List<MasterKategori> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM master_kategori WHERE rowstatus = 1 AND ( kategori_name LIKE %:keyword% )", nativeQuery = true)
	List<MasterKategori> search(String keyword);
	
	@Query(value = "SELECT * FROM master_kategori WHERE rowstatus = 1 AND kategori_name=:kategori_name LIMIT 1", nativeQuery = true)
	MasterKategori findByName(String kategori_name);
}
