package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.Invoice;

@Repository
public interface InvoiceOfficeRepository extends JpaRepository<Invoice, Long> {
	@Query(value = "SELECT B.id AS id,B.artikel,B.nama_barang,B.tanggal_transaksi,B.kuantitas,"
			+ "B.harga_satuan_barang AS harga,(B.harga_satuan_barang * B.kuantitas) AS jumlah,"
			+ "A.nama_pelanggan,C.alamat,A.bank_name,A.no_rek,(SELECT SUM(kuantitas) FROM detail_penjualan_office WHERE id_transaksi=:id_transaksi AND rowstatus=1) AS total_kuantitas,"
			+ "(SELECT SUM(harga_satuan_barang * kuantitas) FROM detail_penjualan_office WHERE id_transaksi=:id_transaksi AND rowstatus=1) AS total_jumlah "
			+ "FROM penjualan_office A "
			+ "LEFT JOIN detail_penjualan_office B ON A.id = B.penjualan_office_id AND B.rowstatus=1 "
			+ "LEFT JOIN pelanggan C ON A.no_hp_pelanggan = C.no_hp AND C.rowstatus=1 "
			+ "WHERE A.rowstatus=1 AND A.id_office = :id_office AND A.id_transaksi=:id_transaksi", nativeQuery = true)
	List<Invoice> Invoice(int id_office,String id_transaksi);
}
