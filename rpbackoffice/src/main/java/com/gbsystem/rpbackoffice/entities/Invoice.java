package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String id_transaksi;
	private String artikel;
	private String nama_barang;
	private Date tanggal_transaksi;
	private Double ongkos_kirim;
	private Double pajak_biaya;
	private Double diskon;
	private Double kuantitas;
	private Double total_kuantitas;
	private Double harga;
	private Double jumlah;
	private Double total_jumlah;
	private String nama_pelanggan;
	private String alamat;
	private String bank_name;
	private String no_rek;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getId_transaksi() {
		return id_transaksi;
	}
	public void setId_transaksi(String id_transaksi) {
		this.id_transaksi = id_transaksi;
	}
	public String getArtikel() {
		return artikel;
	}
	public void setArtikel(String artikel) {
		this.artikel = artikel;
	}
	public String getNama_barang() {
		return nama_barang;
	}
	public void setNama_barang(String nama_barang) {
		this.nama_barang = nama_barang;
	}
	public Date getTanggal_transaksi() {
		return tanggal_transaksi;
	}
	public void setTanggal_transaksi(Date tanggal_transaksi) {
		this.tanggal_transaksi = tanggal_transaksi;
	}
	
	public Double getPajak_biaya() {
		return pajak_biaya;
	}
	public void setPajak_biaya(Double pajak_biaya) {
		this.pajak_biaya = pajak_biaya;
	}
	public Double getOngkos_kirim() {
		return ongkos_kirim;
	}
	public void setOngkos_kirim(Double ongkos_kirim) {
		this.ongkos_kirim = ongkos_kirim;
	}
	public Double getDiskon() {
		return diskon;
	}
	public void setDiskon(Double diskon) {
		this.diskon = diskon;
	}
	public Double getKuantitas() {
		return kuantitas;
	}
	public void setKuantitas(Double kuantitas) {
		this.kuantitas = kuantitas;
	}
	
	public Double getTotal_kuantitas() {
		return total_kuantitas;
	}
	public void setTotal_kuantitas(Double total_kuantitas) {
		this.total_kuantitas = total_kuantitas;
	}
	public Double getHarga() {
		return harga;
	}
	public void setHarga(Double harga) {
		this.harga = harga;
	}
	public Double getJumlah() {
		return jumlah;
	}
	public void setJumlah(Double jumlah) {
		this.jumlah = jumlah;
	}
	public Double getTotal_jumlah() {
		return total_jumlah;
	}
	public void setTotal_jumlah(Double total_jumlah) {
		this.total_jumlah = total_jumlah;
	}
	public String getNama_pelanggan() {
		return nama_pelanggan;
	}
	public void setNama_pelanggan(String nama_pelanggan) {
		this.nama_pelanggan = nama_pelanggan;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getNo_rek() {
		return no_rek;
	}
	public void setNo_rek(String no_rek) {
		this.no_rek = no_rek;
	}
	
}
