package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.Pembelian;

@Repository
public interface PembelianRepository extends JpaRepository<Pembelian, Long> {
	List<Pembelian> findByRowstatus(@Param("rowstatus") int rowstatus); 
}
