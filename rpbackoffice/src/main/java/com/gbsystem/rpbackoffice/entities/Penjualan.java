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

import org.hibernate.annotations.Where;

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
	private String no_hp_pelanggan;
	private String nama_pelanggan;
	private int id_karyawan;
	private String nama_karyawan;
	private Double diskon;
	private String diskon_remark;
	private String metode_bayar;
	private String bank_name;
	private String no_rek;
	private String ekspedisi;
	private Double ongkir;
	private Double total;
	private Double kembalian;
	private Double sum_qty;
	private int rowstatus;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "penjualan", cascade = CascadeType.ALL)
	@Where(clause = "rowstatus=1")
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
	public String getNo_hp_pelanggan() {
		return no_hp_pelanggan;
	}
	public void setNo_hp_pelanggan(String no_hp_pelanggan) {
		this.no_hp_pelanggan = no_hp_pelanggan;
	}
	public Double getDiskon() {
		return diskon;
	}
	public void setDiskon(Double diskon) {
		this.diskon = diskon;
	}
	public String getDiskon_remark() {
		return diskon_remark;
	}
	public void setDiskon_remark(String diskon_remark) {
		this.diskon_remark = diskon_remark;
	}
	public String getMetode_bayar() {
		return metode_bayar;
	}
	public void setMetode_bayar(String metode_bayar) {
		this.metode_bayar = metode_bayar;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getNo_rek() {
		return no_rek;
	}
	public void setNo_rek(String no_rek) {
		this.no_rek = no_rek;
	}
	public String getEkspedisi() {
		return ekspedisi;
	}
	public void setEkspedisi(String ekspedisi) {
		this.ekspedisi = ekspedisi;
	}
	public Double getOngkir() {
		return ongkir;
	}
	public void setOngkir(Double ongkir) {
		this.ongkir = ongkir;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getKembalian() {
		return kembalian;
	}
	public void setKembalian(Double kembalian) {
		this.kembalian = kembalian;
	}
	
	public Double getSum_qty() {
		return sum_qty;
	}
	public void setSum_qty(Double sum_qty) {
		this.sum_qty = sum_qty;
	}
	public String getNama_pelanggan() {
		return nama_pelanggan;
	}
	public void setNama_pelanggan(String nama_pelanggan) {
		this.nama_pelanggan = nama_pelanggan;
	}
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
	            + " id_karyawan=" + id_karyawan + ","
	            + " rowstatus=" + rowstatus + "]";
	}
}
