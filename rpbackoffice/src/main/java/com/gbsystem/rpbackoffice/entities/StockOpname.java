package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class StockOpname {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "date") @JsonFormat(pattern="yyyy-MM-dd")
	private Date tanggal_so;
	private String sku_code;
	private String artikel;
	private int type;
	private String type_name;
	private String kategori;
	private String nama_kategori;
	private String nama_barang;
	private Double kuantitas_masuk;
	private Double kuantitas_keluar;
	private Double stock;
	private Double stock_opname;
	private String keterangan;
	private int rowstatus;
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
	public String getSku_code() {
		return sku_code;
	}
	public void setSku_code(String sku_code) {
		this.sku_code = sku_code;
	}
	public String getArtikel() {
		return artikel;
	}
	public void setArtikel(String artikel) {
		this.artikel = artikel;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
	public String getNama_kategori() {
		return nama_kategori;
	}
	public void setNama_kategori(String nama_kategori) {
		this.nama_kategori = nama_kategori;
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
	public Double getStock() {
		return stock;
	}
	public void setStock(Double stock) {
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
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	@Override
	public String toString() {
		return "StockOpname [id=" + id + ","
				+ " tanggal_so=" + tanggal_so + ", "
				+ " artikel=" + artikel + ", "
				+ " kategori=" + kategori + ","
				+ " type=" + type + ","
	            + " nama_barang=" + nama_barang + ","
	            + " kuantitas_masuk=" + kuantitas_masuk + ","
	            + " kuantitas_keluar=" + kuantitas_keluar + ","
	            + " stock=" + stock + ","
	            + " stock_opname=" + stock_opname + ","
	            + " keterangan=" + keterangan + ","
	            + " rowstatus=" + rowstatus + "]";
	}
}
