package com.gbsystem.rpbackoffice.responses;

import java.util.Date;

public class KaryawanResponse {
	
	private Long id;
	private Date tanggal_join;
	private String nama_karyawan;
	private String lokasi_office;
	private String jabatan;
	private String no_hp;
	private String email;
	private String alamat;
	private double total_transaksi;
	
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
	public String getNama_karyawan() {
		return nama_karyawan;
	}
	public void setNama_karyawan(String nama_karyawan) {
		this.nama_karyawan = nama_karyawan;
	}
	public String getLokasi_office() {
		return lokasi_office;
	}
	public void setLokasi_office(String lokasi_office) {
		this.lokasi_office = lokasi_office;
	}
	public String getJabatan() {
		return jabatan;
	}
	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
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
	public double getTotal_transaksi() {
		return total_transaksi;
	}
	public void setTotal_transaksi(double total_transaksi) {
		this.total_transaksi = total_transaksi;
	}

}
