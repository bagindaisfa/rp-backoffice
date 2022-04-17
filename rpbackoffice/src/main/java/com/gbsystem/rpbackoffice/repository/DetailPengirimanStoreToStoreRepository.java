package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.DetailPengirimanStoreToStore;

@Repository
public interface DetailPengirimanStoreToStoreRepository extends JpaRepository<DetailPengirimanStoreToStore, Long>{

	List<DetailPengirimanStoreToStore> findByRowstatus(@Param("rowstatus") int rowstatus); 
	
	@Query(value = "SELECT * FROM detail_pengiriman_store_to_store WHERE rowstatus = 1 AND ( pengiriman_code LIKE %:keyword% OR sku_code LIKE %:keyword% )", nativeQuery = true)
	List<DetailPengirimanStoreToStore> search(String keyword);
	
	@Query(value = "SELECT id AS id,artikel AS artikel,harga_jual AS harga_jual,"
			+ "hpp AS hpp,kategori AS kategori,kuantitas AS kuantitas,"
			+ "nama_barang AS nama_barang,pengiriman_code AS pengiriman_code,rowstatus AS rowstatus,"
			+ "tanggal_pengiriman AS tanggal_pengiriman,ukuran AS ukuran,nama_kategori AS nama_kategori,"
			+ "type AS type,type_name AS type_name,pengiriman_store_to_store_id AS pengiriman_store_to_store_id,"
			+ " 0.0 as total_pindah, sku_code as sku_code "
			+ "FROM detail_pengiriman_store_to_store "
			+ "WHERE rowstatus = 1 AND pengiriman_code = :pengiriman_code", nativeQuery = true)
	List<DetailPengirimanStoreToStore> all(String pengiriman_code);
	
	@Query(value = "SELECT * FROM detail_pengiriman_store_to_store WHERE rowstatus = 1 AND pengiriman_store_to_store_id = :pengiriman_store_to_store_id", nativeQuery = true)
	DetailPengirimanStoreToStore getByPengiriman_store_to_store_id(Long pengiriman_store_to_store_id);
	

	@Query(value = "SELECT SUM(B.kuantitas) AS kuantitas FROM pengiriman_store_to_store A "
			+ "LEFT JOIN detail_pengiriman_store_to_store B "
			+ "ON A.id = B.pengiriman_store_to_store_id AND B.rowstatus =1 "
			+ "WHERE A.rowstatus = :rowstatus AND A.id_store_asal = :id_store", nativeQuery = true)
	Double totalQtyPindah(int rowstatus, int id_store);
}
