package com.gbsystem.rpbackoffice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class RekapPenjualanPerKaryawan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int id_karyawan;
	private String nama_karyawan;
	private Double total;
	private Double jml_transaksi;
	
	
	public int getId_karyawan() {
		return id_karyawan;
	}
	public void setId_karyawan(int id_karyawan) {
		this.id_karyawan = id_karyawan;
	}
	public String getNama_karyawan() {
		return nama_karyawan;
	}
	public void setNama_karyawan(String nama_karyawan) {
		this.nama_karyawan = nama_karyawan;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getJml_transaksi() {
		return jml_transaksi;
	}
	public void setJml_transaksi(Double jml_transaksi) {
		this.jml_transaksi = jml_transaksi;
	}
	
}
