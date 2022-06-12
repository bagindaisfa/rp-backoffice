package com.gbsystem.rpbackoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.DetailPenerimaanSupplier;

@Repository
public interface DetailPenerimaanSupplierRepository extends JpaRepository<DetailPenerimaanSupplier, Long> {

}
