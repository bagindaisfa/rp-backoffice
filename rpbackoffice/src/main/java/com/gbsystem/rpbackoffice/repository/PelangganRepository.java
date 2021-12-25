package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.Pelanggan;

@Repository
public interface PelangganRepository extends JpaRepository<Pelanggan, Long> {
	
List<Pelanggan> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM pelanggan WHERE rowstatus = 1 AND "
			+ "MATCH(nama_pelanggan) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<Pelanggan> search(String keyword);
	
	@Query(value = "SELECT * INTO OUTFILE 'D:/test.csv' FROM pelanggan where rowstatus = 1", nativeQuery = true)
	List<Pelanggan> download(String oe);

}
