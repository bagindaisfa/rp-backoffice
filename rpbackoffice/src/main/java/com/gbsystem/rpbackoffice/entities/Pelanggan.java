package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pelanggan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(columnDefinition="date")
	private Date tanggal_join;
	private String nama_pelanggan;
	private String no_hp;
	private String email;
	private String alamat;
	private double total_kunjungan;
	private double kuantitas;
	private double poin;
	private double total_pembelian;
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
	
	public Double getTotal_kunjungan() {
		return total_kunjungan;	
	}
	public void setTotal_kunjungan(Double total_kunjungan) {
		this.total_kunjungan = total_kunjungan;
	}
	
	public Double getKuantitas() {
		return kuantitas;	
	}
	public void setKuantitas(Double kuantitas) {
		this.kuantitas = kuantitas;
	}
	
	public Double getPoin() {
		return poin;	
	}
	public void setPoin(Double poin) {
		this.poin = poin;
	}
	
	public Double getTotal_pembelian() {
		return total_pembelian;	
	}
	public void setTotal_pembelian(Double total_pembelian) {
		this.total_pembelian = total_pembelian;
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
				+ " nama_pelanggan=" + nama_pelanggan + ", "
				+ " no_hp=" + no_hp + ","
				+ " email=" + email + ","
	            + " alamat=" + alamat + ","
	            + " total_kunjungan=" + total_kunjungan + ","
	            + " kuantitas=" + kuantitas + ","
	            + " poin=" + poin + ","
	            + " total_pembelian=" + total_pembelian + ","
	            + " rowstatus=" + rowstatus + "]";
	}
	
}
