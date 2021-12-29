package com.gbsystem.rpbackoffice.responses;

import java.util.Date;

public class PenjualanResponse {
	private Long id;
	private Date tanggal_transaksi;
	private String id_transaksi;
	private String id_store;
	private String lokasi_store;
	private String nama_pelanggan;
	private String nama_karyawan;
	private double diskon;
	private String metode_bayar;
	private String ekspedisi;
	private double ongkir;
	private double total;
	private double kembalian;
	
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
	public String getId_store() {
		return id_store;
	}
	public void setId_store(String id_store) {
		this.id_store = id_store;
	}
	public String getLokasi_store() {
		return lokasi_store;
	}
	public void setLokasi_store(String lokasi_store) {
		this.lokasi_store = lokasi_store;
	}
	public double getDiskon() {
		return diskon;
	}
	public void setDiskon(double diskon) {
		this.diskon = diskon;
	}
	public String getMetode_bayar() {
		return metode_bayar;
	}
	public void setMetode_bayar(String metode_bayar) {
		this.metode_bayar = metode_bayar;
	}
	public String getEkspedisi() {
		return ekspedisi;
	}
	public void setEkspedisi(String ekspedisi) {
		this.ekspedisi = ekspedisi;
	}
	public double getOngkir() {
		return ongkir;
	}
	public void setOngkir(double ongkir) {
		this.ongkir = ongkir;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getKembalian() {
		return kembalian;
	}
	public void setKembalian(double kembalian) {
		this.kembalian = kembalian;
	}
	public String getNama_pelanggan() {
		return nama_pelanggan;
	}
	public void setNama_pelanggan(String nama_pelanggan) {
		this.nama_pelanggan = nama_pelanggan;
	}
	public String getNama_karyawan() {
		return nama_karyawan;
	}
	public void setNama_karyawan(String nama_karyawan) {
		this.nama_karyawan = nama_karyawan;
	}
}
