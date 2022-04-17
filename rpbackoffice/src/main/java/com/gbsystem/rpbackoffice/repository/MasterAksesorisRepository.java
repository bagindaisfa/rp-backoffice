package com.gbsystem.rpbackoffice.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.MasterAksesoris;

@Repository
public interface MasterAksesorisRepository extends JpaRepository<MasterAksesoris, Long> {
	List<MasterAksesoris> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM master_aksesoris WHERE rowstatus = 1 AND (artikel_aksesoris LIKE %:keyword% OR nama_aksesoris LIKE %:keyword% OR type_name LIKE %:keyword% OR nama_kategori LIKE %:keyword% ) ", nativeQuery = true)
	List<MasterAksesoris> search(String keyword);
}
