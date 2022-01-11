package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class DetailPengirimanStoreToStore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pengiriman_code;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date tanggal_pengiriman;
	private String artikel;
	private String kategori;
	private String tipe;
	private String nama_barang;
	private double kuantitas;
	private String ukuran;
	private double hpp;
	private double harga_jual;
	private int rowstatus;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pengiriman_store_to_store_id", referencedColumnName = "id")
	@JsonIgnoreProperties("detailPengirimanList")
    private PengirimanStoreToStore pengirimanStoreToStore;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPengiriman_code() {
		return pengiriman_code;
	}
	public void setPengiriman_code(String pengiriman_code) {
		this.pengiriman_code = pengiriman_code;
	}
	public Date getTanggal_pengiriman() {
		return tanggal_pengiriman;
	}
	public void setTanggal_pengiriman(Date tanggal_pengiriman) {
		this.tanggal_pengiriman = tanggal_pengiriman;
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
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	public PengirimanStoreToStore getPengirimanStoreToStore() {
		return pengirimanStoreToStore;
	}
	public void setPengirimanStoreToStore(PengirimanStoreToStore pengirimanStoreToStore) {
		this.pengirimanStoreToStore = pengirimanStoreToStore;
	}
}
