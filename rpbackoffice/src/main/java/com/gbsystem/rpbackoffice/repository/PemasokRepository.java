package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.Pemasok;

@Repository
public interface PemasokRepository extends JpaRepository<Pemasok, Long> {
	
	@Query(value = "SELECT * FROM pemasok WHERE rowstatus = 1 AND id=:id ", nativeQuery = true)
	Pemasok findById(int id);
	
	List<Pemasok> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM pemasok WHERE rowstatus = 1 AND (nama_pemasok LIKE %:keyword%) ", nativeQuery = true)
	List<Pemasok> search(String keyword);
	
	@Query(value = "SELECT COUNT(id) AS id FROM pemasok WHERE rowstatus = :rowstatus ", nativeQuery = true)
	Long totalPemasok(int rowstatus);
	
	@Query(value = "SELECT * FROM pemasok WHERE rowstatus = 1 AND kode_pemasok=:kode_pemasok LIMIT 1", nativeQuery = true)
	Pemasok findByKodePemasok(String kode_pemasok); 
	
//	@Query(value = "SELECT * INTO OUTFILE 'D:/test.csv' FROM pemasok where rowstatus = 1", nativeQuery = true)
//	List<Pemasok> download(String oe);
	
	@Procedure(procedureName = "download_pemasok")
	void download();
}
