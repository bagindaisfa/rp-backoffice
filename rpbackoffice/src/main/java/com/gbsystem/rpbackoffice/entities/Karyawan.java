package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Karyawan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date tanggal_join;
	private String nama_karyawan;
	private String lokasi_office;
	private String jabatan;
	private String no_hp;
	private String email;
	private String alamat;
	private double total_transaksi;
	private int rowstatus;
	
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
	
	public Double getTotal_transaksi() {
		return total_transaksi;	
	}
	public void setTotal_transaksi(Double total_transaksi) {
		this.total_transaksi = total_transaksi;
	}
	
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	
	@Override
	public String toString() {
		return "Pelanggan [id=" + id + ","
				+ " tanggal_join=" + tanggal_join + ", "
				+ " nama_karyawan=" + nama_karyawan + ", "
				+ " lokasi_office=" + lokasi_office + ", "
				+ " jabatan=" + jabatan + ", "
				+ "no_hp=" + no_hp + ","
				+ " email=" + email + ","
	            + " alamat=" + alamat + ","
	            + " rowstatus=" + rowstatus + "]";
	}
	
}
