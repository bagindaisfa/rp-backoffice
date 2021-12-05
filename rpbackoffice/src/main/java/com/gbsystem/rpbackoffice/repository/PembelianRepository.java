package com.gbsystem.rpbackoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.Pembelian;

@Repository
public interface PembelianRepository extends JpaRepository<Pembelian, Long> {

}
