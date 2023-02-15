package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PIReport;

@Repository
public interface PIReportRepository extends JpaRepository<PIReport, Long> {
	@Query(value = "SELECT B.id AS id,B.artikel,B.nama_barang,B.tanggal_transaksi,B.kuantitas,"
			+ "B.harga_satuan_barang AS harga, "
			+ "if (B.diskon > 100,((B.harga_satuan_barang * B.kuantitas) - B.diskon ),((B.harga_satuan_barang * B.kuantitas) - (B.diskon /100 * (B.harga_satuan_barang * B.kuantitas)))) AS jumlah,"
			+ "A.pi_no,A.pajak_biaya,A.ongkos_kirim,"
			+ "A.diskon, "
			+ "B.diskon as diskon_satuan,"
			+ "A.nama_pelanggan,C.alamat,A.bank_name,A.no_rek,(SELECT SUM(kuantitas) FROM detail_proforma_invoice WHERE pi_no=:pi_no AND rowstatus=1) AS total_kuantitas,"
			+ "A.total_penjualan AS total_jumlah,A.total_dp,(SELECT xf_terbilang(A.total_dp)) AS terbilang,IFNULL(D.owner_name,'') as owner_name "
			+ "FROM proforma_invoice A "
			+ "LEFT JOIN detail_proforma_invoice B ON A.id = B.proforma_invoice_id AND B.rowstatus=1 "
			+ "LEFT JOIN pelanggan C ON A.no_hp_pelanggan = C.no_hp AND C.rowstatus=1 "
			+ "LEFT JOIN master_bank D ON A.no_rek = D.acc_number AND D.rowstatus=1 "
			+ "WHERE A.rowstatus=1 AND A.id_office = :id_office AND A.pi_no=:pi_no", nativeQuery = true)
	List<PIReport> ProformaInvoice(int id_office,String pi_no);
}
