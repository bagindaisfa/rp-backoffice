package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.MasterUkuran;

@Repository
public interface MasterUkuranRepository extends JpaRepository<MasterUkuran, Long> {
List<MasterUkuran> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM master_ukuran WHERE rowstatus = 1 AND (ukuran like %:keyword%)", nativeQuery = true)
	List<MasterUkuran> search(String keyword);
}
