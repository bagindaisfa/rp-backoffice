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
public class DetailPenjualanOffice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date tanggal_transaksi;
	private String id_transaksi;
	private String sku_code;
	private String artikel;
	private String type;
	private String type_name;
	private String kategori;
	private String nama_kategori;
	private String nama_barang;
	private double kuantitas;
	private String ukuran;
	private String metode_pembayaran;
	private double harga_satuan_barang;
	private double ongkos_kirim;
	private double pajak_biaya;
	private double total;
	private int rowstatus;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "penjualan_office_id", referencedColumnName = "id")
	@JsonIgnoreProperties("detailPenjualanList")
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
	public String getMetode_pembayaran() {
		return metode_pembayaran;
	}
	public void setMetode_pembayaran(String metode_pembayaran) {
		this.metode_pembayaran = metode_pembayaran;
	}
	public double getHarga_satuan_barang() {
		return harga_satuan_barang;
	}
	public void setHarga_satuan_barang(double harga_satuan_barang) {
		this.harga_satuan_barang = harga_satuan_barang;
	}
	public double getOngkos_kirim() {
		return ongkos_kirim;
	}
	public void setOngkos_kirim(double ongkos_kirim) {
		this.ongkos_kirim = ongkos_kirim;
	}
	public double getPajak_biaya() {
		return pajak_biaya;
	}
	public void setPajak_biaya(double pajak_biaya) {
		this.pajak_biaya = pajak_biaya;
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
	public PenjualanOffice getPenjualanOffice() {
		return penjualanOffice;
	}
	public void setPenjualanOffice(PenjualanOffice penjualanOffice) {
		this.penjualanOffice = penjualanOffice;
	}
}
