package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.Pemasok;

@Repository
public interface PemasokRepository extends JpaRepository<Pemasok, Long> {
	
List<Pemasok> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM pemasok WHERE rowstatus = 1 AND "
			+ "MATCH(nama_pemasok) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<Pemasok> search(String keyword);
	
	@Query(value = "SELECT * INTO OUTFILE 'D:/test.csv' FROM pemasok where rowstatus = 1", nativeQuery = true)
	List<Pemasok> download(String oe);
}
