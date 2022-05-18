package com.gbsystem.rpbackoffice.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class BarangPesananTungguItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String no_pesanan;
	private int id_store;
	private String lokasi_store;
	private String sku_code;
	private String artikel;
	private int type;
	private String type_name;
	private String kategori;
	private String nama_kategori;
	private String nama_barang;
	private Double harga;
	private Double kuantitas;
	private Double total;
	private int rowstatus;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pesanan_tunggu_id", referencedColumnName = "id")
	@Where(clause="rowstatus = 1")
    @JsonIgnoreProperties("barang_pesanan")
    private PesananTunggu pesanan_tunggu;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNo_pesanan() {
		return no_pesanan;
	}
	public void setNo_pesanan(String no_pesanan) {
		this.no_pesanan = no_pesanan;
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
	public Double getHarga() {
		return harga;
	}
	public void setHarga(Double harga) {
		this.harga = harga;
	}
	public Double getKuantitas() {
		return kuantitas;
	}
	public void setKuantitas(Double kuantitas) {
		this.kuantitas = kuantitas;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public PesananTunggu getPesanan_tunggu() {
		return pesanan_tunggu;
	}
	public void setPesanan_tunggu(PesananTunggu pesanan_tunggu) {
		this.pesanan_tunggu = pesanan_tunggu;
	}
	
	
}
