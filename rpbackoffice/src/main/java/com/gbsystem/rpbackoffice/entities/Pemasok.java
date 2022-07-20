package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pemasok {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(columnDefinition="date")
	private Date tanggal_join;
	private String kode_pemasok;
	private String nama_pemasok;
	private String no_hp;
	private String email;
	private String alamat;
	private int rowstatus;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getTanggal_join() {
		return tanggal_join;	
	}
	public void setTanggal_join(Date tanggal_join) {
		this.tanggal_join = tanggal_join;
	}
	
	public String getKode_pemasok() {
		return kode_pemasok;	
	}
	public void setKode_pemasok(String kode_pemasok) {
		this.kode_pemasok = kode_pemasok;
	}
	
	public String getNama_pemasok() {
		return nama_pemasok;	
	}
	public void setNama_pemasok(String nama_pemasok) {
		this.nama_pemasok = nama_pemasok;
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
				+ " kode_pemasok=" + kode_pemasok + ", "
				+ " nama_pemasok=" + nama_pemasok + ", "
				+ " no_hp=" + no_hp + ","
				+ " email=" + email + ","
	            + " alamat=" + alamat + ","
	            + " rowstatus=" + rowstatus + "]";
	}
	
}
