package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.KasKeluar;

@Repository
public interface KasKeluarRepository extends JpaRepository<KasKeluar, Long> {
	@Query(value = "SELECT * FROM kas_keluar WHERE rowstatus = 1 AND id_store = :id_store ORDER BY waktu_keluar DESC", nativeQuery = true)
	List<KasKeluar> all(int id_store);

}
