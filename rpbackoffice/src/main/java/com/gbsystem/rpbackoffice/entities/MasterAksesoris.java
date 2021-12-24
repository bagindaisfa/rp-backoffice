package com.gbsystem.rpbackoffice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MasterAksesoris {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String artikel_aksesoris;
	private String nama_aksesoris;
	private String type;
	private String type_name;
	private String kategori;
	private String nama_kategori;
	private double kuantitas;
	private double hpp;
	private double harga_jual;
	private int rowstatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getArtikel_aksesoris() {
		return artikel_aksesoris;
	}
	public void setArtikel_aksesoris(String artikel_aksesoris) {
		this.artikel_aksesoris = artikel_aksesoris;
	}
	public String getNama_aksesoris() {
		return nama_aksesoris;
	}
	public void setNama_aksesoris(String nama_aksesoris) {
		this.nama_aksesoris = nama_aksesoris;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
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
	public double getKuantitas() {
		return kuantitas;
	}
	public void setKuantitas(double kuantitas) {
		this.kuantitas = kuantitas;
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
		return "MasterAksesoris [id=" + id + ","
				+ " artikel_aksesoris=" + artikel_aksesoris + ", "
				+ " nama_aksesoris=" + nama_aksesoris + ", "
				+ " type=" + type + ", "
				+ " type_name=" + type_name + ", "
				+ " kategori=" + kategori + ", "
				+ " nama_kategori=" + nama_kategori + ", "
				+ " kuantitas=" + kuantitas + ", "
				+ " kuantitas=" + kuantitas + ", "
				+ " hpp=" + hpp + ", "
				+ " harga_jual=" + harga_jual + ", "
	            + " rowstatus=" + rowstatus + "]";
	}
}
