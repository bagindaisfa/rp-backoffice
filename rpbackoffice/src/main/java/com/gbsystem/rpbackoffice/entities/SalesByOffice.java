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
	
	private Date tanggal_transaksi;
	
	
	private Date date_from;
	
	
	private Date date_to;
	
	
	private String lokasi_office;
	
	
	private String nama_pelanggan;
	
	
	private String artikel;
	
	
	private String nama_barang;
	
	
	private double kuantitas;
	
	
	private double harga;
	
	private double total_qty;
	private double total_harga;

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

	public double getTotal_qty() {
		return total_qty;
	}

	public void setTotal_qty(double total_qty) {
		this.total_qty = total_qty;
	}

	public double getTotal_harga() {
		return total_harga;
	}

	public void setTotal_harga(double total_harga) {
		this.total_harga = total_harga;
	}

	@Override
	public String toString() {
		return "SalesByOffice [id=" + id + ", tanggal_transaksi=" + tanggal_transaksi + ", date_from=" + date_from
				+ ", date_to=" + date_to + ", lokasi_office=" + lokasi_office + ", nama_pelanggan=" + nama_pelanggan
				+ ", artikel=" + artikel + ", nama_barang=" + nama_barang + ", kuantitas=" + kuantitas + ", harga="
				+ harga + "]";
	}
	
}
