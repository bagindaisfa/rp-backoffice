package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class RekapPenjualanPerPelanggan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nama_pelanggan;
	private String no_hp_pelanggan;
	@JsonFormat(pattern="EEEE, dd MMM yyyy")
	private Date tanggal_transaksi;
	private Integer total_kunjungan;
	private Double total;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNama_pelanggan() {
		return nama_pelanggan;
	}
	public void setNama_pelanggan(String nama_pelanggan) {
		this.nama_pelanggan = nama_pelanggan;
	}
	public String getNo_hp_pelanggan() {
		return no_hp_pelanggan;
	}
	public void setNo_hp_pelanggan(String no_hp_pelanggan) {
		this.no_hp_pelanggan = no_hp_pelanggan;
	}
	public Date getTanggal_transaksi() {
		return tanggal_transaksi;
	}
	public void setTanggal_transaksi(Date tanggal_transaksi) {
		this.tanggal_transaksi = tanggal_transaksi;
	}
	public Integer getTotal_kunjungan() {
		return total_kunjungan;
	}
	public void setTotal_kunjungan(Integer total_kunjungan) {
		this.total_kunjungan = total_kunjungan;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
}
