package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.MasterStore;

@Repository
public interface MasterStoreRepository extends JpaRepository<MasterStore, Long> {
	List<MasterStore> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM master_store WHERE rowstatus = 1 AND (store_name LIKE %:keyword% OR alamat LIKE %:keyword% ) ", nativeQuery = true)
	List<MasterStore> search(String keyword);
}
