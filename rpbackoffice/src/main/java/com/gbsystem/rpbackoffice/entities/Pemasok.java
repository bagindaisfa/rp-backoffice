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
	private Long id;
	@Column(columnDefinition = "date") @JsonFormat(pattern="yyyy-MM-dd")
	private Date tanggal_join;
	private String kode_pemasok;
	private String nama_pemasok;
	private String no_hp;
	private String email;
	private String alamat;
	private double hpp;
	private double harga_jual;
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
	
	public double getHpp() {
		return hpp;
	}
	public void setHpp(double hpp) {
		this.hpp = hpp;
	}
	
	public double getHarga_jual() {
		return harga_jual;
	}
	public void setHarga_jual(double harga_jual) {
		this.harga_jual = harga_jual;
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
	            + " hpp=" + hpp + ","
	    	    + " harga_jual=" + harga_jual + ","
	            + " rowstatus=" + rowstatus + "]";
	}
	
}
