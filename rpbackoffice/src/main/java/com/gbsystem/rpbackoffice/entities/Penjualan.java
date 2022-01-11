package com.gbsystem.rpbackoffice.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Penjualan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date tanggal_transaksi;
	private String id_transaksi;
	private int id_store;
	private String lokasi_store;
	private String nama_pelanggan;
	private String nama_karyawan;
	private double diskon;
	private String metode_bayar;
	private String ekspedisi;
	private double ongkir;
	private double total;
	private double kembalian;
	private int rowstatus;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "penjualan", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("penjualan")
    private List<DetailPesanan> detailPesananList = new ArrayList<>();
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
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	
	public List<DetailPesanan> getDetailPesananList() {
		return detailPesananList;
	}
	public void setDetailPesananList(List<DetailPesanan> detailPesananList) {
		this.detailPesananList = detailPesananList;
	}
	
	@Override
	public String toString() {
		return "Penjualan [id=" + id + ","
				+ " tanggal_transaksi=" + tanggal_transaksi + ", "
				+ " id_transaksi=" + id_transaksi + ", "
				+ "id_store=" + id_store + ","
				+ " lokasi_store=" + lokasi_store + ","
	            + " diskon=" + diskon + ","
	            + " metode_bayar=" + metode_bayar + ","
	            + " ekspedisi=" + ekspedisi + ","
	            + " ongkir=" + ongkir + ","
	            + " total=" + total + ","
	            + " kembalian=" + kembalian + ","
	            + " nama_pelanggan=" + nama_pelanggan + ","
	            + " nama_karyawan=" + nama_karyawan + ","
	            + " rowstatus=" + rowstatus + "]";
	}
}
