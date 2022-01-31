package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Karyawan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date tanggal_join;
	private String nama_karyawan;
	private int id_office;
	private String lokasi_office;
	private int id_store;
	private String lokasi_store;
	private String jabatan;
	private String no_hp;
	private String email;
	private String alamat;
	private double total_transaksi;
	private int rowstatus;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;
	
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
	
	public int getId_office() {
		return id_office;
	}
	public void setId_office(int id_office) {
		this.id_office = id_office;
	}
	public int getId_store() {
		return id_store;
	}
	public void setId_store(int id_store) {
		this.id_store = id_store;
	}
	public String getLokasi_store() {
		return lokasi_store;
	}
	public void setLokasi_store(String lokasi_store) {
		this.lokasi_store = lokasi_store;
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
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
