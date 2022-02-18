package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.KasMasuk;

@Repository
public interface KasMasukRepository extends JpaRepository <KasMasuk, Long>{
	@Query(value = "SELECT * FROM kas_masuk WHERE rowstatus = 1 AND id_store = :id_store ORDER BY waktu_masuk DESC", nativeQuery = true)
	List<KasMasuk> all(int id_store);
}
