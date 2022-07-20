package com.gbsystem.rpbackoffice.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class PenjualanOffice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(columnDefinition="date")
	private Date tanggal_transaksi;
	private String id_transaksi;
	private int id_office;
	private String lokasi_office;
	private String no_hp_pelanggan;
	private String nama_pelanggan;
	private double total_penjualan;
	private int rowstatus;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "penjualanOffice", cascade = CascadeType.ALL)
	@Where(clause="rowstatus = 1")
	@JsonIgnoreProperties("penjualanOffice")
    private List<DetailPenjualanOffice> detail_penjualan = new ArrayList<>();
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
	public int getId_office() {
		return id_office;
	}
	public void setId_office(int id_office) {
		this.id_office = id_office;
	}
	public String getLokasi_office() {
		return lokasi_office;
	}
	public void setLokasi_office(String lokasi_office) {
		this.lokasi_office = lokasi_office;
	}
	
	public String getNo_hp_pelanggan() {
		return no_hp_pelanggan;
	}
	public void setNo_hp_pelanggan(String no_hp_pelanggan) {
		this.no_hp_pelanggan = no_hp_pelanggan;
	}
	public String getNama_pelanggan() {
		return nama_pelanggan;
	}
	public void setNama_pelanggan(String nama_pelanggan) {
		this.nama_pelanggan = nama_pelanggan;
	}
	
	public double getTotal_penjualan() {
		return total_penjualan;
	}
	public void setTotal_penjualan(double total_penjualan) {
		this.total_penjualan = total_penjualan;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	public List<DetailPenjualanOffice> getDetail_penjualan() {
		return detail_penjualan;
	}
	public void setDetail_penjualan(List<DetailPenjualanOffice> detail_penjualan) {
		this.detail_penjualan = detail_penjualan;
	}
	
}
