package com.gbsystem.rpbackoffice.responses;

import java.util.Date;

public class PelangganResponse {
	
	private Long id;
	private Date tanggal_join;
	private String nama_pelanggan;
	private String no_hp;
	private String email;
	private String alamat;
	private double total_kunjungan;
	private double kuantitas;
	private double poin;
	private double total_pembelian;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getTanggal_join() {
		return tanggal_join;
	}
	public void setTanggal_join(Date tanggal_join) {
		this.tanggal_join = tanggal_join;
	}
	public String getNama_pelanggan() {
		return nama_pelanggan;
	}
	public void setNama_pelanggan(String nama_pelanggan) {
		this.nama_pelanggan = nama_pelanggan;
	}
	public String getNo_hp() {
		return no_hp;
	}
	public void setNo_hp(String no_hp) {
		this.no_hp = no_hp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public double getTotal_kunjungan() {
		return total_kunjungan;
	}
	public void setTotal_kunjungan(double total_kunjungan) {
		this.total_kunjungan = total_kunjungan;
	}
	public double getKuantitas() {
		return kuantitas;
	}
	public void setKuantitas(double kuantitas) {
		this.kuantitas = kuantitas;
	}
	public double getPoin() {
		return poin;
	}
	public void setPoin(double poin) {
		this.poin = poin;
	}
	public double getTotal_pembelian() {
		return total_pembelian;
	}
	public void setTotal_pembelian(double total_pembelian) {
		this.total_pembelian = total_pembelian;
	}

}
