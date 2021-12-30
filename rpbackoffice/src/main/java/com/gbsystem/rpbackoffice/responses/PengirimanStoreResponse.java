package com.gbsystem.rpbackoffice.responses;

import java.util.Date;

public class PengirimanStoreResponse {
	
	private Long id;
	private Date tanggal_penerimaan;
	private String lokasi_store_asal;
	private String id_store_tujuan;
	private String lokasi_store_tujuan;
	private String artikel;
	private String kategori;
	private String tipe;
	private String nama_barang;
	private double kuantitas;
	private String ukuran;
	private double hpp;
	private double harga_jual;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getTanggal_penerimaan() {
		return tanggal_penerimaan;
	}
	public void setTanggal_penerimaan(Date tanggal_penerimaan) {
		this.tanggal_penerimaan = tanggal_penerimaan;
	}
	public String getLokasi_store_asal() {
		return lokasi_store_asal;
	}
	public void setLokasi_store_asal(String lokasi_store_asal) {
		this.lokasi_store_asal = lokasi_store_asal;
	}
	public String getId_store_tujuan() {
		return id_store_tujuan;
	}
	public void setId_store_tujuan(String id_store_tujuan) {
		this.id_store_tujuan = id_store_tujuan;
	}
	public String getLokasi_store_tujuan() {
		return lokasi_store_tujuan;
	}
	public void setLokasi_store_tujuan(String lokasi_store_tujuan) {
		this.lokasi_store_tujuan = lokasi_store_tujuan;
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
	public double getHpp() {
		return hpp;
	}
	public void setHpp(double hpp) {
		this.hpp = hpp;
	}
	public double getHarga_jual() {
		return harga_jual;
	}
	public void setHarga_jual(double harga_jual) {
		this.harga_jual = harga_jual;
	}

}
