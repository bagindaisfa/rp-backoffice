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
public class PenerimaanSupplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String penerimaan_code;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date tanggal_penerimaan;
	private int id_office;
	private String lokasi_office;
	private String id_supplier;
	private String nama_supplier;
	
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
	public String getPenerimaan_code() {
		return penerimaan_code;
	}
	public void setPenerimaan_code(String penerimaan_code) {
		this.penerimaan_code = penerimaan_code;
	}
	public Date getTanggal_penerimaan() {
		return tanggal_penerimaan;
	}
	public void setTanggal_penerimaan(Date tanggal_penerimaan) {
		this.tanggal_penerimaan = tanggal_penerimaan;
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
	public String getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(String id_supplier) {
		this.id_supplier = id_supplier;
	}
	public String getNama_supplier() {
		return nama_supplier;
	}
	public void setNama_supplier(String nama_supplier) {
		this.nama_supplier = nama_supplier;
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
		return "PenerimaanSupplier [id=" + id + ","
				+ " tanggal_penerimaan=" + tanggal_penerimaan + ", "
				+ " lokasi_office=" + lokasi_office + ", "
				+ " id_supplier=" + id_supplier + ", "
				+ " nama_supplier=" + nama_supplier + ", "
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
