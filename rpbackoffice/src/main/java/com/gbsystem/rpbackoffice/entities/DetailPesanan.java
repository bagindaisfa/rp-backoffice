package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.CascadeType;
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
public class DetailPesanan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date tanggal_transaksi;
	private String id_transaksi;
	private int id_store;
	private String lokasi_store;
	private String artikel;
	private String nama_barang;
	private Double harga;
	private Double kuantitas;
	private Double total;
	private int rowstatus;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "penjualan_id", referencedColumnName = "id")
    @JsonIgnoreProperties("detailPesananList")
    private Penjualan penjualan;
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
	public String getArtikel() {
		return artikel;
	}
	public void setArtikel(String artikel) {
		this.artikel = artikel;
	}
	public String getNama_barang() {
		return nama_barang;
	}
	public void setNama_barang(String nama_barang) {
		this.nama_barang = nama_barang;
	}
	public double getHarga() {
		return harga;
	}
	public void setHarga(double harga) {
		this.harga = harga;
	}
	public double getKuantitas() {
		return kuantitas;
	}
	public void setKuantitas(double kuantitas) {
		this.kuantitas = kuantitas;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	
	public Penjualan getPenjualan() {
		return penjualan;
	}
	public void setPenjualan(Penjualan penjualan) {
		this.penjualan = penjualan;
	}
	@Override
	public String toString() {
		return "Penjualan [id=" + id + ","
				+ " tanggal_transaksi=" + tanggal_transaksi + ", "
				+ " id_transaksi=" + id_transaksi + ", "
				+ "id_store=" + id_store + ","
				+ " lokasi_store=" + lokasi_store + ","
	            + " nama_barang=" + nama_barang + ","
	            + " harga=" + harga + ","
	            + " kuantitas=" + kuantitas + ","
	            + " total=" + total + ","
	            + " rowstatus=" + rowstatus + "]";
	}
	
}
