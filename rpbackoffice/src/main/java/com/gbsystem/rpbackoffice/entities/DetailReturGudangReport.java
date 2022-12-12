package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DetailReturGudangReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable=false, insertable=false)
	private String pengiriman_code;
	
	@Column(updatable=false, insertable=false)
	private String lokasi_store_asal;
	
	@Column(updatable=false, insertable=false)
	private String lokasi_office_tujuan;
	
	@Column(updatable=false, insertable=false)
	private Date tanggal_retur;
	
	@Column(updatable=false, insertable=false)
	private String artikel;
	
	@Column(updatable=false, insertable=false)
	private String type;
	
	@Column(updatable=false, insertable=false)
	private String type_name;
	
	@Column(updatable=false, insertable=false)
	private String kategori;
	
	@Column(updatable=false, insertable=false)
	private String nama_kategori;
	
	@Column(updatable=false, insertable=false)
	private String nama_barang;
	
	@Column(updatable=false, insertable=false)
	private double kuantitas;
	
	@Column(updatable=false, insertable=false)
	private double harga_jual;
	
	@Column(updatable=false, insertable=false)
	private String keterangan;

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
	
	public String getLokasi_store_asal() {
		return lokasi_store_asal;
	}

	public void setLokasi_store_asal(String lokasi_store_asal) {
		this.lokasi_store_asal = lokasi_store_asal;
	}

	public String getLokasi_office_tujuan() {
		return lokasi_office_tujuan;
	}

	public void setLokasi_office_tujuan(String lokasi_office_tujuan) {
		this.lokasi_office_tujuan = lokasi_office_tujuan;
	}

	public Date getTanggal_retur() {
		return tanggal_retur;
	}

	public void setTanggal_retur(Date tanggal_retur) {
		this.tanggal_retur = tanggal_retur;
	}

	public String getArtikel() {
		return artikel;
	}

	public void setArtikel(String artikel) {
		this.artikel = artikel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

	public double getKuantitas() {
		return kuantitas;
	}

	public void setKuantitas(double kuantitas) {
		this.kuantitas = kuantitas;
	}

	public double getHarga_jual() {
		return harga_jual;
	}

	public void setHarga_jual(double harga_jual) {
		this.harga_jual = harga_jual;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	
	
}
