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
	
	@Query(value = "SELECT * FROM karyawan WHERE rowstatus = 1 AND ( nama_karyawan LIKE %:keyword% OR lokasi_store LIKE %:keyword% OR lokasi_office LIKE %:keyword% )", nativeQuery = true)
	List<Karyawan> search(String keyword);
	
	@Query(value = "SELECT * FROM karyawan WHERE rowstatus = 1 AND id_store =:id_store AND ( nama_karyawan LIKE %:keyword% OR lokasi_store LIKE %:keyword% OR lokasi_office LIKE %:keyword% )", nativeQuery = true)
	List<Karyawan> searchForStore(String keyword, int id_store);
	
	@Query(value = "SELECT * FROM karyawan WHERE rowstatus = 1 AND id_store = :id_store", nativeQuery = true)
	List<Karyawan> getAllByIdStore(int id_store);

}
