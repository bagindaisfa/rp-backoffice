package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PenyimpananBarangMasukReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable=false, insertable=false)
	private Date tanggal_masuk;
	
	@Column(updatable=false, insertable=false)
	private Date date_from;
	
	@Column(updatable=false, insertable=false)
	private Date date_to;

	@Column(updatable=false, insertable=false)
	private String artikel;
	
	@Column(updatable=false, insertable=false)
	private String nama_barang;

	@Column(updatable=false, insertable=false)
	private Double kuantitas;
	
	@Column(updatable=false, insertable=false)
	private Double harga_jual;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTanggal_masuk() {
		return tanggal_masuk;
	}

	public void setTanggal_masuk(Date tanggal_masuk) {
		this.tanggal_masuk = tanggal_masuk;
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

	public Double getKuantitas() {
		return kuantitas;
	}

	public void setKuantitas(Double kuantitas) {
		this.kuantitas = kuantitas;
	}

	public Double getHarga_jual() {
		return harga_jual;
	}

	public void setHarga_jual(Double harga_jual) {
		this.harga_jual = harga_jual;
	}

}
