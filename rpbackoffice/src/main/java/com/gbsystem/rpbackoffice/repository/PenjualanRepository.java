package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.Penjualan;

@Repository
public interface PenjualanRepository extends JpaRepository<Penjualan, Long> {
	
	List<Penjualan> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM penjualan WHERE rowstatus = 1 AND id_store = :id_store ORDER BY tanggal_transaksi DESC", nativeQuery = true)
	List<Penjualan> getAllPerStore(int id_store);
	
	@Query(value = "SELECT * FROM penjualan WHERE rowstatus = 1 AND id_store = :id_store AND"
			+ "( id_transaksi LIKE %:keyword% OR "
			+ "lokasi_store LIKE %:keyword% OR "
			+ "nama_pelanggan LIKE %:keyword% ) ORDER BY tanggal_transaksi DESC", nativeQuery = true)
	List<Penjualan> searchPerStore(int id_store, String keyword);
	
	@Query(value = "SELECT * FROM penjualan WHERE id = :id", nativeQuery = true)
	Penjualan getById(Long id);
	
	@Query(value = "SELECT COUNT(id) FROM penjualan WHERE rowstatus = :rowstatus", nativeQuery = true)
	Double counting(int rowstatus);
	
	@Query(value = "SELECT SUM(total) AS total FROM penjualan WHERE rowstatus = :rowstatus", nativeQuery = true)
	Double total(int rowstatus);
	
	@Query(value = "SELECT SUM(B.hpp) AS total FROM detail_pesanan A LEFT JOIN master_product B ON A.artikel=B.artikel_product WHERE A.rowstatus = :rowstatus", nativeQuery = true)
	Double totalHpp(int rowstatus);
	
	@Query(value = "SELECT * FROM penjualan WHERE rowstatus = 1 AND "
			+ "( id_transaksi LIKE %:keyword% OR "
			+ "id_store LIKE %:keyword% OR "
			+ "lokasi_store LIKE %:keyword% OR "
			+ "nama_pelanggan LIKE %:keyword% )", nativeQuery = true)
	List<Penjualan> search(String keyword);
	
	
	//Laporan Moblie
	@Query(value = "SELECT COUNT(id) FROM penjualan WHERE rowstatus = 1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date", nativeQuery = true)
	Double countingMobile(int id_store, String start_date, String end_date);
	
	@Query(value = "SELECT SUM(total) AS total FROM penjualan WHERE rowstatus = 1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date", nativeQuery = true)
	Double totalMobile(int id_store, String start_date, String end_date);
	
	@Query(value = "SELECT * FROM penjualan WHERE rowstatus = 1 AND id_store=:id_store AND no_hp_pelanggan=:no_hp_pelanggan AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date", nativeQuery = true)
	List<Penjualan> subRiwayatPelanggan(int id_store, String start_date, String end_date, String no_hp_pelanggan);
	
	@Query(value = "SELECT * FROM penjualan WHERE rowstatus = 1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date GROUP BY DATE(tanggal_transaksi)", nativeQuery = true)
	List<Penjualan> riwayatPertanggal(int id_store, String start_date, String end_date);
	
	@Query(value = "SELECT * FROM penjualan WHERE rowstatus = 1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date", nativeQuery = true)
	List<Penjualan> subRiwayatTerakhir(int id_store, String start_date, String end_date);
	
	@Query(value = "SELECT * FROM penjualan WHERE rowstatus=1 AND id_store=:id_store AND no_hp_pelanggan=:no_hp_pelanggan AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date", nativeQuery = true)
	List<Penjualan> rekapPelangganPerTanggal(int id_store, String start_date, String end_date, String no_hp_pelanggan);
	
	@Query(value = "SELECT * FROM penjualan WHERE rowstatus = 1 AND id_transaksi=:id_transaksi", nativeQuery = true)
	List<Penjualan> findByIdTransaksi(String id_transaksi);
}
