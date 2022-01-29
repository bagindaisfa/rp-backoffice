package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PenyimpananBarangKeluarReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable=false, insertable=false)
	private Date tanggal_keluar;
	
	@Column(updatable=false, insertable=false)
	private Date tanggal_transaksi;

	@Column(updatable=false, insertable=false)
	private String id_store;

	@Column(updatable=false, insertable=false)
	private String lokasi_store;

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

	public Date getTanggal_keluar() {
		return tanggal_keluar;
	}

	public void setTanggal_keluar(Date tanggal_keluar) {
		this.tanggal_keluar = tanggal_keluar;
	}

	public Date getTanggal_transaksi() {
		return tanggal_transaksi;
	}

	public void setTanggal_transaksi(Date tanggal_transaksi) {
		this.tanggal_transaksi = tanggal_transaksi;
	}

	public String getId_store() {
		return id_store;
	}

	public void setId_store(String id_store) {
		this.id_store = id_store;
	}

	public String getLokasi_store() {
		return lokasi_store;
	}

	public void setLokasi_store(String lokasi_store) {
		this.lokasi_store = lokasi_store;
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
