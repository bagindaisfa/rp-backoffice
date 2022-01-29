package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PengirimanStoreToStoreReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable=false, insertable=false)
	private Date tanggal_pengiriman;
	
	@Column(updatable=false, insertable=false)
	private Date tanggal_transaksi;
	
	@Column(updatable=false, insertable=false)
	private String kode_pengiriman;
	
	@Column(updatable=false, insertable=false)
	private String id_store_asal;

	@Column(updatable=false, insertable=false)
	private String lokasi_store_asal;

	@Column(updatable=false, insertable=false)
	private String id_store_tujuan;

	@Column(updatable=false, insertable=false)
	private String lokasi_store_tujuan;

	@Column(updatable=false, insertable=false)
	private String artikel;

	@Column(updatable=false, insertable=false)
	private String nama_barang;

	@Column(updatable=false, insertable=false)
	private Double kuantitas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTanggal_pengiriman() {
		return tanggal_pengiriman;
	}

	public void setTanggal_pengiriman(Date tanggal_pengiriman) {
		this.tanggal_pengiriman = tanggal_pengiriman;
	}

	public Date getTanggal_transaksi() {
		return tanggal_transaksi;
	}

	public void setTanggal_transaksi(Date tanggal_transaksi) {
		this.tanggal_transaksi = tanggal_transaksi;
	}

	public String getKode_pengiriman() {
		return kode_pengiriman;
	}

	public void setKode_pengiriman(String kode_pengiriman) {
		this.kode_pengiriman = kode_pengiriman;
	}

	public String getId_store_asal() {
		return id_store_asal;
	}

	public void setId_store_asal(String id_store_asal) {
		this.id_store_asal = id_store_asal;
	}

	public String getLokasi_store_asal() {
		return lokasi_store_asal;
	}

	public void setLokasi_store_asal(String lokasi_store_asal) {
		this.lokasi_store_asal = lokasi_store_asal;
	}

	public String getId_store_tujuan() {
		return id_store_tujuan;
	}

	public void setId_store_tujuan(String id_store_tujuan) {
		this.id_store_tujuan = id_store_tujuan;
	}

	public String getLokasi_store_tujuan() {
		return lokasi_store_tujuan;
	}

	public void setLokasi_store_tujuan(String lokasi_store_tujuan) {
		this.lokasi_store_tujuan = lokasi_store_tujuan;
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

}
