package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PurchaseStoreByArticle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable=false, insertable=false)
	private String artikel;
	
	@Column(updatable=false, insertable=false)
	private String nama_kategori;
	
	@Column(updatable=false, insertable=false)
	private String type_name;
	
	@Column(updatable=false, insertable=false)
	private String nama_barang;
	
	@Column(updatable=false, insertable=false)
	private Date tanggal_transaksi;
	
	@Column(updatable=false, insertable=false)
	private String ukuran;
	
	@Column(updatable=false, insertable=false)
	private Date date_from;
	
	@Column(updatable=false, insertable=false)
	private Date date_to;
	
	@Column(updatable=false, insertable=false)
	private String lokasi_store;

	@Column(updatable=false, insertable=false)
	private Double kuantitas;
	
	@Column(updatable=false, insertable=false)
	private Double total;
	
	@Column(updatable=false, insertable=false)
	private Double harga;
	
	@Column(updatable=false, insertable=false)
	private String nama_karyawan;

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

	public String getNama_kategori() {
		return nama_kategori;
	}

	public void setNama_kategori(String nama_kategori) {
		this.nama_kategori = nama_kategori;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
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

	public String getUkuran() {
		return ukuran;
	}

	public void setUkuran(String ukuran) {
		this.ukuran = ukuran;
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

	public String getLokasi_store() {
		return lokasi_store;
	}

	public void setLokasi_store(String lokasi_store) {
		this.lokasi_store = lokasi_store;
	}

	public Double getKuantitas() {
		return kuantitas;
	}

	public void setKuantitas(Double kuantitas) {
		this.kuantitas = kuantitas;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getHarga() {
		return harga;
	}

	public void setHarga(Double harga) {
		this.harga = harga;
	}

	public String getNama_karyawan() {
		return nama_karyawan;
	}

	public void setNama_karyawan(String nama_karyawan) {
		this.nama_karyawan = nama_karyawan;
	}
	
}
