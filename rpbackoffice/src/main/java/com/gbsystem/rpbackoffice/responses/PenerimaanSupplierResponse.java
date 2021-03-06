package com.gbsystem.rpbackoffice.responses;

import java.util.Date;

public class PenerimaanSupplierResponse {

	private Long id;
	private Date tanggal_penerimaan;
	private String lokasi_penerimaan;
	private String id_supplier;
	private String nama_supplier;
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
	public String getLokasi_penerimaan() {
		return lokasi_penerimaan;
	}
	public void setLokasi_penerimaan(String lokasi_penerimaan) {
		this.lokasi_penerimaan = lokasi_penerimaan;
	}
	public String getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(String id_supplier) {
		this.id_supplier = id_supplier;
	}
	public String getNama_supplier() {
		return nama_supplier;
	}
	public void setNama_supplier(String nama_supplier) {
		this.nama_supplier = nama_supplier;
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
