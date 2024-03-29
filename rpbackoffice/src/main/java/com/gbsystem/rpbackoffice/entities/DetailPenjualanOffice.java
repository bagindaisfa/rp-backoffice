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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class DetailPenjualanOffice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(columnDefinition="date")
	private Date tanggal_transaksi;
	private String id_transaksi;
	private String sku_code;
	private String artikel;
	private int type;
	private String type_name;
	private String kategori;
	private String nama_kategori;
	private String nama_barang;
	private Double kuantitas;
	private Double harga_satuan_barang;
	private Double diskon;
	private Double total;
	private int rowstatus;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "penjualan_office_id", referencedColumnName = "id")
	@JsonIgnoreProperties("detail_penjualan")
    private PenjualanOffice penjualanOffice;
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
	public Double getKuantitas() {
		return kuantitas;
	}
	public void setKuantitas(Double kuantitas) {
		this.kuantitas = kuantitas;
	}
	
	public Double getHarga_satuan_barang() {
		return harga_satuan_barang;
	}
	public void setHarga_satuan_barang(Double harga_satuan_barang) {
		this.harga_satuan_barang = harga_satuan_barang;
	}
	
	public Double getDiskon() {
		return diskon;
	}
	public void setDiskon(Double diskon) {
		this.diskon = diskon;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	public PenjualanOffice getPenjualanOffice() {
		return penjualanOffice;
	}
	public void setPenjualanOffice(PenjualanOffice penjualanOffice) {
		this.penjualanOffice = penjualanOffice;
	}
}
