package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PenyimpananStockOpnameStoreReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable=false, insertable=false)
	private String lokasi_store;
	
	@Column(updatable=false, insertable=false)
	private Date tanggal_so;
	
	@Column(updatable=false, insertable=false)
	private Date date_from;
	
	@Column(updatable=false, insertable=false)
	private Date date_to;
	
	@Column(updatable=false, insertable=false)
	private String artikel;

	@Column(updatable=false, insertable=false)
	private String nama_barang;

	@Column(updatable=false, insertable=false)
	private Double kuantitas_masuk;

	@Column(updatable=false, insertable=false)
	private Double kuantitas_keluar;	

	@Column(updatable=false, insertable=false)
	private Double stock;
	
	@Column(updatable=false, insertable=false)
	private Double stock_opname;
	
	@Column(updatable=false, insertable=false)
	private String keterangan;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLokasi_store() {
		return lokasi_store;
	}

	public void setLokasi_store(String lokasi_store) {
		this.lokasi_store = lokasi_store;
	}

	public Date getTanggal_so() {
		return tanggal_so;
	}

	public void setTanggal_so(Date tanggal_so) {
		this.tanggal_so = tanggal_so;
	}

	public Date getDate_from() {
		return date_from;
	}

	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}

	public Date getDate_to() {
		return date_to;
	}

	public void setDate_to(Date date_to) {
		this.date_to = date_to;
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

	public Double getKuantitas_masuk() {
		return kuantitas_masuk;
	}

	public void setKuantitas_masuk(Double kuantitas_masuk) {
		this.kuantitas_masuk = kuantitas_masuk;
	}

	public Double getKuantitas_keluar() {
		return kuantitas_keluar;
	}

	public void setKuantitas_keluar(Double kuantitas_keluar) {
		this.kuantitas_keluar = kuantitas_keluar;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public Double getStock_opname() {
		return stock_opname;
	}

	public void setStock_opname(Double stock_opname) {
		this.stock_opname = stock_opname;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
}
