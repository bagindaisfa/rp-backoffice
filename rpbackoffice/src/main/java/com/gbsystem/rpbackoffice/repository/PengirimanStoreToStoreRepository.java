package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gbsystem.rpbackoffice.entities.PengirimanStoreToStore;

public interface PengirimanStoreToStoreRepository extends JpaRepository<PengirimanStoreToStore, Long> {
	@Query(value = "SELECT "
			+ "A.id, "
			+ "A.id_karyawan, "
			+ "A.id_store_asal, "
			+ "A.id_store_tujuan, "
			+ "A.keterangan, "
			+ "A.lokasi_store_asal,"
			+ "A.lokasi_store_tujuan, "
			+ "A.nama_karyawan, "
			+ "A.pengiriman_code, "
			+ "A.rowstatus, "
			+ "A.tanggal_pengiriman, "
			+ "(SELECT SUM(kuantitas) FROM detail_pengiriman_store_to_store WHERE pengiriman_store_to_store_id=A.id and rowstatus=1) AS total_pindah  "
			+ "FROM pengiriman_store_to_store A WHERE A.rowstatus = 1", nativeQuery = true)
	List<PengirimanStoreToStore> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT "
			+ "A.id, "
			+ "A.id_karyawan, "
			+ "A.id_store_asal, "
			+ "A.id_store_tujuan, "
			+ "A.keterangan, "
			+ "A.lokasi_store_asal,"
			+ "A.lokasi_store_tujuan, "
			+ "A.nama_karyawan, "
			+ "A.pengiriman_code, "
			+ "A.rowstatus, "
			+ "A.tanggal_pengiriman, "
			+ "(SELECT SUM(kuantitas) FROM detail_pengiriman_store_to_store WHERE pengiriman_store_to_store_id=A.id and rowstatus=1) AS total_pindah  "
			+ "FROM pengiriman_store_to_store A WHERE A.rowstatus = 1 AND ( A.pengiriman_code LIKE %:keyword% OR "
			+ "A.lokasi_store_asal LIKE %:keyword% OR A.lokasi_store_tujuan LIKE %:keyword% )", nativeQuery = true)
	List<PengirimanStoreToStore> search( String keyword);
	
	@Query(value = "SELECT A.id AS id,A.id_store_asal AS id_store_asal,A.id_store_tujuan AS id_store_tujuan,"
			+ "A.lokasi_store_asal AS lokasi_store_asal,A.lokasi_store_tujuan AS lokasi_store_tujuan,A.pengiriman_code AS pengiriman_code,"
			+ "A.rowstatus AS rowstatus,A.tanggal_pengiriman AS tanggal_pengiriman, A.id_karyawan AS id_karyawan, A.nama_karyawan AS nama_karyawan, A.keterangan AS keterangan,"
			+ "(SELECT SUM(kuantitas) FROM detail_pengiriman_store_to_store WHERE pengiriman_store_to_store_id=A.id) AS total_pindah "
			+ "FROM pengiriman_store_to_store A "
			+ "WHERE A.rowstatus = 1 AND A.id_store_asal = :id_store AND ( A.pengiriman_code LIKE %:keyword% OR "
			+ "A.lokasi_store_asal LIKE %:keyword% OR A.lokasi_store_tujuan LIKE %:keyword% )", nativeQuery = true)
	List<PengirimanStoreToStore> searchMobile(int id_store, String keyword);
	
	@Query(value = "SELECT A.id AS id,A.id_store_asal AS id_store_asal,A.id_store_tujuan AS id_store_tujuan,"
			+ "A.lokasi_store_asal AS lokasi_store_asal,A.lokasi_store_tujuan AS lokasi_store_tujuan,A.pengiriman_code AS pengiriman_code,"
			+ "A.rowstatus AS rowstatus,A.tanggal_pengiriman AS tanggal_pengiriman, A.id_karyawan AS id_karyawan, A.nama_karyawan AS nama_karyawan, A.keterangan AS keterangan,"
			+ "(SELECT SUM(kuantitas) FROM detail_pengiriman_store_to_store WHERE pengiriman_store_to_store_id=A.id) AS total_pindah "
			+ "FROM pengiriman_store_to_store A "
			+ "WHERE A.rowstatus = 1 AND A.id_store_asal= :id_store ", nativeQuery = true)
	List<PengirimanStoreToStore> getAllPerStorePindah(int id_store);

	@Query(value = "SELECT * FROM pengiriman_store_to_store WHERE rowstatus = 1 AND pengiriman_code =:pengiriman_code", nativeQuery = true)
	PengirimanStoreToStore getByPengirimanCode(String pengiriman_code);
}
