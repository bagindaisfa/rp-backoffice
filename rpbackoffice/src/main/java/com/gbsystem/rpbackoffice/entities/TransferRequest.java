package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransferRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable=false,nullable=false, insertable=false)
	private Date tanggal_pengiriman;
	
	@Column(updatable=false,nullable=false, insertable=false)
	private String kode_pengiriman;
	
	@Column(updatable=false,nullable=false, insertable=false)
	private String lokasi_office;

	@Column(updatable=false,nullable=false, insertable=false)
	private String lokasi_store;

	@Column(updatable=false,nullable=false, insertable=false)
	private String artikel;

	@Column(updatable=false,nullable=false, insertable=false)
	private String nama_barang;

	@Column(updatable=false,nullable=false, insertable=false)
	private Double kuantitas;
	
	@Column(updatable=false,nullable=false, insertable=false)
	private Double harga_jual;
	
	@Column(updatable=false,nullable=false, insertable=false)
	private Double sum_qty;
	
	@Column(updatable=false, insertable=false)
	private String keterangan;

	public Double getSum_qty() {
		return sum_qty;
	}

	public void setSum_qty(Double sum_qty) {
		this.sum_qty = sum_qty;
	}

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

	public String getLokasi_office() {
		return lokasi_office;
	}

	public void setKode_pengiriman(String kode_pengiriman) {
		this.kode_pengiriman = kode_pengiriman;
	}

	public String getKode_pengiriman() {
		return kode_pengiriman;
	}
	
	public void setLokasi_office(String lokasi_office) {
		this.lokasi_office = lokasi_office;
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

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	
}
