package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.Karyawan;

@Repository
public interface KaryawanRepository extends JpaRepository<Karyawan, Long> {
	
List<Karyawan> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM karyawan WHERE rowstatus = 1 AND "
			+ "MATCH(nama_karyawan) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<Karyawan> search(String keyword);

}
