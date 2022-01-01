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
public class ReturGudang {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pengiriman_code;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date tanggal_retur;
	private int id_store_asal;
	private String lokasi_store_asal;
	private int id_office_tujuan;
	private String lokasi_office_tujuan;
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
	public String getPengiriman_code() {
		return pengiriman_code;
	}
	public void setPengiriman_code(String pengiriman_code) {
		this.pengiriman_code = pengiriman_code;
	}
	public Date getTanggal_retur() {
		return tanggal_retur;
	}
	public void setTanggal_retur(Date tanggal_retur) {
		this.tanggal_retur = tanggal_retur;
	}
	public int getId_store_asal() {
		return id_store_asal;
	}
	public void setId_store_asal(int id_store_asal) {
		this.id_store_asal = id_store_asal;
	}
	public String getLokasi_store_asal() {
		return lokasi_store_asal;
	}
	public void setLokasi_store_asal(String lokasi_store_asal) {
		this.lokasi_store_asal = lokasi_store_asal;
	}
	public int getId_office_tujuan() {
		return id_office_tujuan;
	}
	public void setId_office_tujuan(int id_office_tujuan) {
		this.id_office_tujuan = id_office_tujuan;
	}
	public String getLokasi_office_tujuan() {
		return lokasi_office_tujuan;
	}
	public void setLokasi_office_tujuan(String lokasi_office_tujuan) {
		this.lokasi_office_tujuan = lokasi_office_tujuan;
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
	public double getHarga_jual() {
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
		return "ReturGudang [id=" + id + ","
				+ " pengiriman_code=" + pengiriman_code + ", "
				+ " tanggal_retur=" + tanggal_retur + ", "
				+ " id_store_asal=" + id_store_asal + ", "
				+ " id_office_tujuan=" + id_office_tujuan + ", "
				+ " lokasi_office_tujuan=" + lokasi_office_tujuan + ", "
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
