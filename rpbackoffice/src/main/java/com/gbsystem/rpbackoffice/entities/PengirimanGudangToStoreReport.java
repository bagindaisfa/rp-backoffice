package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PengirimanGudangToStoreReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable=false, insertable=false)
	private Date tanggal_pengiriman;
	
	@Column(updatable=false, insertable=false)
	private Date date_from;
	
	@Column(updatable=false, insertable=false)
	private Date date_to;
	
	@Column(updatable=false, insertable=false)
	private String kode_pengiriman;
	
	@Column(updatable=false, insertable=false)
	private String id_office;

	@Column(updatable=false, insertable=false)
	private String lokasi_office;

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

	public String getKode_pengiriman() {
		return kode_pengiriman;
	}

	public void setKode_pengiriman(String kode_pengiriman) {
		this.kode_pengiriman = kode_pengiriman;
	}

	public String getId_office() {
		return id_office;
	}

	public void setId_office(String id_office) {
		this.id_office = id_office;
	}

	public String getLokasi_office() {
		return lokasi_office;
	}

	public void setLokasi_office(String lokasi_office) {
		this.lokasi_office = lokasi_office;
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

}
