package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PengirimanGudang {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date tanggal_pengiriman;
	private String nama_gudang;
	private String lokasi_store;
	private String artikel;
	private String kategori;
	private String tipe;
	private String nama_barang;
	private double kuantitas;
	private String ukuran;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String foto_barang;
	private double hpp;
	private double harga_jual;
	private int rowstatus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getTanggal_pengiriman() {
		return tanggal_pengiriman;
	}
	public void setTanggal_pengiriman(Date tanggal_pengiriman) {
		this.tanggal_pengiriman = tanggal_pengiriman;
	}
	public String getNama_gudang() {
		return nama_gudang;
	}
	public void setNama_gudang(String nama_gudang) {
		this.nama_gudang = nama_gudang;
	}
	public String getLokasi_store() {
		return lokasi_store;
	}
	public void setLokasi_store(String lokasi_store) {
		this.lokasi_store = lokasi_store;
	}
	public String getArtikel() {
		return artikel;
	}
	public void setArtikel(String artikel) {
		this.artikel = artikel;
	}
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
	public String getTipe() {
		return tipe;
	}
	public void setTipe(String tipe) {
		this.tipe = tipe;
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
	public String getFoto_barang() {
		return foto_barang;
	}
	public void setFoto_barang(String foto_barang) {
		this.foto_barang = foto_barang;
	}
	public double getHpp() {
		return hpp;
	}
	public void setHpp(double hpp) {
		this.hpp = hpp;
	}
	public double getHarga_juala() {
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
		return "PengirimanGudang [id=" + id + ","
				+ " tanggal_pengiriman=" + tanggal_pengiriman + ", "
				+ " nama_gudang=" + nama_gudang + ", "
				+ " lokasi_store=" + lokasi_store + ", "
				+ " artikel=" + artikel + ", "
				+ " kategori=" + kategori + ","
				+ " tipe=" + tipe + ","
	            + " nama_barang=" + nama_barang + ","
	            + " kuantitas=" + kuantitas + ","
	            + " ukuran=" + ukuran + ","
	            + " hpp=" + hpp + ","
	            + " foto_barang=" + foto_barang + ","
	            + " harga_jual=" + harga_jual + ","
	            + " rowstatus=" + rowstatus + "]";
	}

}
