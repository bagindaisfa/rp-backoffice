package com.gbsystem.rpbackoffice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RekapPenjualanPerProduk {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String artikel;
	private String nama_barang;
	private String nama_kategori;
	private String harga_jual;
	private Double total_terjual;
	private Double penjualan_kotor;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getNama_kategori() {
		return nama_kategori;
	}
	public void setNama_kategori(String nama_kategori) {
		this.nama_kategori = nama_kategori;
	}
	public String getHarga_jual() {
		return harga_jual;
	}
	public void setHarga_jual(String harga_jual) {
		this.harga_jual = harga_jual;
	}
	public Double getTotal_terjual() {
		return total_terjual;
	}
	public void setTotal_terjual(Double total_terjual) {
		this.total_terjual = total_terjual;
	}
	public Double getPenjualan_kotor() {
		return penjualan_kotor;
	}
	public void setPenjualan_kotor(Double penjualan_kotor) {
		this.penjualan_kotor = penjualan_kotor;
	}
}
