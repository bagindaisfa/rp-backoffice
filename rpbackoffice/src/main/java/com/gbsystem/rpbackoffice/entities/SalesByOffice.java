package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SalesByOffice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable=false, insertable=false)
	private Date tanggal_transaksi;
	
	@Column(updatable=false, insertable=false)
	private Date date_from;
	
	@Column(updatable=false, insertable=false)
	private Date date_to;
	
	@Column(updatable=false, insertable=false)
	private String lokasi_office;
	
	@Column(updatable=false, insertable=false)
	private String nama_pelanggan;
	
	@Column(updatable=false, insertable=false)
	private String artikel;
	
	@Column(updatable=false, insertable=false)
	private String nama_barang;
	
	@Column(updatable=false, insertable=false)
	private double kuantitas;
	
	@Column(updatable=false, insertable=false)
	private double harga;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTanggal_transaksi() {
		return tanggal_transaksi;
	}

	public void setTanggal_transaksi(Date tanggal_transaksi) {
		this.tanggal_transaksi = tanggal_transaksi;
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

	public String getLokasi_office() {
		return lokasi_office;
	}

	public void setLokasi_office(String lokasi_office) {
		this.lokasi_office = lokasi_office;
	}

	public String getNama_pelanggan() {
		return nama_pelanggan;
	}

	public void setNama_pelanggan(String nama_pelanggan) {
		this.nama_pelanggan = nama_pelanggan;
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

	public double getKuantitas() {
		return kuantitas;
	}

	public void setKuantitas(double kuantitas) {
		this.kuantitas = kuantitas;
	}

	public double getHarga() {
		return harga;
	}

	public void setHarga(double harga) {
		this.harga = harga;
	}
	
}
