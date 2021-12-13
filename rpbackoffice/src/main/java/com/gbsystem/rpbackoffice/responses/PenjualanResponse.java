package com.gbsystem.rpbackoffice.responses;

import java.util.Date;

public class PenjualanResponse {
	private Long id;
	private Date tanggal_transaksi;
	private String id_transaksi;
	private String id_store;
	private String lokasi_store;
	private double kuantitas;
	private double total;
	
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
	public String getId_transaksi() {
		return id_transaksi;
	}
	public void setId_transaksi(String id_transaksi) {
		this.id_transaksi = id_transaksi;
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
	public double getKuantitas() {
		return kuantitas;
	}
	public void setKuantitas(int kuantitas) {
		this.kuantitas = kuantitas;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
