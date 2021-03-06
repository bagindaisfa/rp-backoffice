package com.gbsystem.rpbackoffice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class MasterProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String sku_code;
	private String artikel_product;
	private String nama_product;
	private Integer type;
	private String type_name;
	private String kategori;
	private String nama_kategori;
	private String artikel_frame;
	private String artikel_lens;
	private String ukuran;
	private Double kuantitas;
	private Double hpp;
	private Double harga_jual;
	private String remarks;
	private int rowstatus;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSku_code() {
		return sku_code;
	}
	public void setSku_code(String sku_code) {
		this.sku_code = sku_code;
	}
	public String getArtikel_product() {
		return artikel_product;
	}
	public void setArtikel_product(String artikel_product) {
		this.artikel_product = artikel_product;
	}
	public String getNama_product() {
		return nama_product;
	}
	public void setNama_product(String nama_product) {
		this.nama_product = nama_product;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
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
	
	public String getArtikel_frame() {
		return artikel_frame;
	}
	public void setArtikel_frame(String artikel_frame) {
		this.artikel_frame = artikel_frame;
	}
	public String getArtikel_lens() {
		return artikel_lens;
	}
	public void setArtikel_lens(String artikel_lens) {
		this.artikel_lens = artikel_lens;
	}
	public String getUkuran() {
		return ukuran;
	}
	public void setUkuran(String ukuran) {
		this.ukuran = ukuran;
	}
	public Double getKuantitas() {
		return kuantitas;
	}
	public void setKuantitas(Double kuantitas) {
		this.kuantitas = kuantitas;
	}
	public Double getHpp() {
		return hpp;
	}
	public void setHpp(Double hpp) {
		this.hpp = hpp;
	}
	public Double getHarga_jual() {
		return harga_jual;
	}
	public void setHarga_jual(Double harga_jual) {
		this.harga_jual = harga_jual;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "MasterProject [id=" + id + ","
				+ " artikel_product=" + artikel_product + ", "
				+ " nama_product=" + nama_product + ", "
				+ " type=" + type + ", "
				+ " type_name=" + type_name + ", "
				+ " kategori=" + kategori + ", "
				+ " nama_kategori=" + nama_kategori + ", "
				+ " artikel_frame=" + artikel_frame + ", "
				+ " artikel_lens=" + artikel_lens + ", "
				+ " kuantitas=" + kuantitas + ", "
				+ " hpp=" + hpp + ", "
				+ " harga_jual=" + harga_jual + ", "
				+ " remarks=" + remarks + ", "
				+ " rowstatus=" + rowstatus + ", "
	            + " image=" + image + "]";
	}
}
