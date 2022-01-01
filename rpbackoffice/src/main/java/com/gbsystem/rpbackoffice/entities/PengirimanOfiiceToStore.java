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
public class PengirimanOfiiceToStore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pengiriman_code;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date tanggal_pengiriman;
	private int id_office;
	private String office_name;
	private int id_store;
	private String store_name;
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

	public Date getTanggal_pengiriman() {
		return tanggal_pengiriman;
	}

	public void setTanggal_pengiriman(Date tanggal_pengiriman) {
		this.tanggal_pengiriman = tanggal_pengiriman;
	}

	public int getId_office() {
		return id_office;
	}

	public void setId_office(int id_office) {
		this.id_office = id_office;
	}

	public String getOffice_name() {
		return office_name;
	}

	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}

	public int getId_store() {
		return id_store;
	}

	public void setId_store(int id_store) {
		this.id_store = id_store;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
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
		return "PengirimanGudang [id=" + id + ","
				+ " tanggal_pengiriman=" + tanggal_pengiriman + ", "
				+ " office_name=" + office_name + ", "
				+ " store_name=" + store_name + ", "
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
