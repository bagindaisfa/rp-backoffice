package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.MasterMenu;

@Repository
public interface MasterMenuRepository extends JpaRepository<MasterMenu, Long>  {
List<MasterMenu> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM master_menu WHERE rowstatus = 1 AND (kode_menu LIKE %:keyword% OR nama_menu LIKE %:keyword%) ", nativeQuery = true)
	List<MasterMenu> search(String keyword);
}
