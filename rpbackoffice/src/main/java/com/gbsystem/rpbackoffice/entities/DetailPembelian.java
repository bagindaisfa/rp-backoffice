package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class DetailPembelian {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pembelian_code;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(columnDefinition="date")
	private Date tanggal_transaksi;

	@Column(updatable=false, insertable=false)
	private Date date_from;

	@Column(updatable=false, insertable=false)
	private Date date_to;
	private String sku_code;
	private String artikel;
	private int type;
	private String type_name;
	private String kategori;
	private String nama_kategori;
	private String nama_barang;
	private double kuantitas;
	private String ukuran;
	private double hpp;
	private double harga_jual;
	private double total_hpp;
	private int rowstatus;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pembelian_id", referencedColumnName = "id")
	@Where(clause="rowstatus = 1")
    @JsonIgnoreProperties("detailPembelianList")
    private Pembelian pembelian;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPembelian_code() {
		return pembelian_code;
	}
	public void setPembelian_code(String pembelian_code) {
		this.pembelian_code = pembelian_code;
	}
	public Date getTanggal_transaksi() {
		return tanggal_transaksi;
	}
	public void setTanggal_transaksi(Date tanggal_transaksi) {
		this.tanggal_transaksi = tanggal_transaksi;
	}
	public Date getDate_from() {
		return date_from;
	}

	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}

	public Date getDate_to() {
		return date_to;
	}

	public void setDate_to(Date date_to) {
		this.date_to = date_to;
	}
	
	public String getSku_code() {
		return sku_code;
	}
	public void setSku_code(String sku_code) {
		this.sku_code = sku_code;
	}
	public String getArtikel() {
		return artikel;
	}
	public void setArtikel(String artikel) {
		this.artikel = artikel;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
	public String getNama_kategori() {
		return nama_kategori;
	}
	public void setNama_kategori(String nama_kategori) {
		this.nama_kategori = nama_kategori;
	}
	public String getNama_barang() {
		return nama_barang;
	}
	public void setNama_barang(String nama_barang) {
		this.nama_barang = nama_barang;
	}
	public double getKuantitas() {
		return kuantitas;
	}
	public void setKuantitas(double kuantitas) {
		this.kuantitas = kuantitas;
	}
	public String getUkuran() {
		return ukuran;
	}
	public void setUkuran(String ukuran) {
		this.ukuran = ukuran;
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
	public double getTotal_hpp() {
		return total_hpp;
	}
	public void setTotal_hpp(double total_hpp) {
		this.total_hpp = total_hpp;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	public Pembelian getPembelian() {
		return pembelian;
	}
	public void setPembelian(Pembelian pembelian) {
		this.pembelian = pembelian;
	}
}
