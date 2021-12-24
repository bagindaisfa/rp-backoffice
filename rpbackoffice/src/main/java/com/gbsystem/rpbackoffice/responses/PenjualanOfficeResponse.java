package com.gbsystem.rpbackoffice.responses;

import java.util.Date;

public class PenjualanOfficeResponse {
	private Long id;
	private Date tanggal_transaksi;
	private String id_transaksi;
	private String id_office;
	private String lokasi_office;
	private String artikel;
	private String tipe;
	private String kategori;
	private String nama_barang;
	private Double kuantitas;
	private String ukuran;
	private String metode_pembayaran;
	private Double harga_satuan_barang;
	private Double ongkos_kirim;
	private Double pajak_biaya;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getTanggal_transaksi() {
		return tanggal_transaksi;
	}
	public void setTanggal_transaksi(Date tanggal_transaksi) {
		this.tanggal_transaksi = tanggal_transaksi;
	}
	public String getId_transaksi() {
		return id_transaksi;
	}
	public void setId_transaksi(String id_transaksi) {
		this.id_transaksi = id_transaksi;
	}
	public String getId_office() {
		return id_office;
	}
	public void setId_office(String id_office) {
		this.id_office = id_office;
	}
	public String getLokasi_office() {
		return lokasi_office;
	}
	public void setLokasi_office(String lokasi_office) {
		this.lokasi_office = lokasi_office;
	}
	public String getArtikel() {
		return artikel;
	}
	public void setArtikel(String artikel) {
		this.artikel = artikel;
	}
	public String getTipe() {
		return tipe;
	}
	public void setTipe(String tipe) {
		this.tipe = tipe;
	}
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
	public String getNama_barang() {
		return nama_barang;
	}
	public void setNama_barang(String nama_barang) {
		this.nama_barang = nama_barang;
	}
	public double getKuantitas() {
		return kuantitas;
	}
	public void setKuantitas(double kuantitas) {
		this.kuantitas = kuantitas;
	}
	public String getUkuran() {
		return ukuran;
	}
	public void setUkuran(String ukuran) {
		this.ukuran = ukuran;
	}
	public String getMetode_pembayaran() {
		return metode_pembayaran;
	}
	public void setMetode_pembayaran(String metode_pembayaran) {
		this.metode_pembayaran = metode_pembayaran;
	}
	public double getHarga_satuan_barang() {
		return harga_satuan_barang;
	}
	public void setHarga_satuan_barang(double harga_satuan_barang) {
		this.harga_satuan_barang = harga_satuan_barang;
	}
	public double getOngkos_kirim() {
		return ongkos_kirim;
	}
	public void setOngkos_kirim(double ongkos_kirim) {
		this.ongkos_kirim = ongkos_kirim;
	}
	public double getPajak_biaya() {
		return pajak_biaya;
	}
	public void setPajak_biaya(double pajak_biaya) {
		this.pajak_biaya = pajak_biaya;
	}
}
