package com.gbsystem.rpbackoffice.responses;

import java.util.Date;

public class PenjualanOfficeResponse {
	private Long id;
	private Date tanggal_transaksi;
	private String id_transaksi;
	private String id_office;
	private String lokasi_office;
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
