package com.gbsystem.rpbackoffice.responses;

import java.util.Date;

public class StockOpnameResponse {
	private Long id;
	private Date tanggal_so;
	private String artikel;
	private String kategori;
	private String tipe;
	private String nama_barang;
	private double kuantitas_masuk;
	private double kuantitas_keluar;
	private Float stock;
	private double stock_opname;
	private String keterangan;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getTanggal_so() {
		return tanggal_so;
	}
	public void setTanggal_so(Date tanggal_so) {
		this.tanggal_so = tanggal_so;
	}
	public String getArtikel() {
		return artikel;
	}
	public void setArtikel(String artikel) {
		this.artikel = artikel;
	}
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
	public String getTipe() {
		return tipe;
	}
	public void setTipe(String tipe) {
		this.tipe = tipe;
	}
	public String getNama_barang() {
		return nama_barang;
	}
	public void setNama_barang(String nama_barang) {
		this.nama_barang = nama_barang;
	}
	public double getKuantitas_masuk() {
		return kuantitas_masuk;
	}
	public void setKuantitas_masuk(double kuantitas_masuk) {
		this.kuantitas_masuk = kuantitas_masuk;
	}
	public double getKuantitas_keluar() {
		return kuantitas_keluar;
	}
	public void setKuantitas_keluar(double kuantitas_keluar) {
		this.kuantitas_keluar = kuantitas_keluar;
	}
	public Float getStock() {
		return stock;
	}
	public void setStock(Float stock) {
		this.stock = stock;
	}
	public double getStock_opname() {
		return stock_opname;
	}
	public void setStock_opname(double stock_opname) {
		this.stock_opname = stock_opname;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
}
