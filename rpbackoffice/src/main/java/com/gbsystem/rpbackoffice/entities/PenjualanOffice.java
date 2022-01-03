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
public class PenjualanOffice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date tanggal_transaksi;
	private String id_transaksi;
	private int id_office;
	private String lokasi_office;
	private String artikel;
	private String tipe;
	private String kategori;
	private String nama_barang;
	private Double kuantitas;
	private String ukuran;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String foto_barang;
	private String metode_pembayaran;
	private Double harga_satuan_barang;
	private Double ongkos_kirim;
	private Double pajak_biaya;
	private Double total;
	private int rowstatus;
	
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
	public String getArtikel() {
		return artikel;
	}
	public void setArtikel(String artikel) {
		this.artikel = artikel;
	}
	public String getTipe() {
		return tipe;
	}
	public void setTipe(String tipe) {
		this.tipe = tipe;
	}
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
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
	
	@Override
	public String toString() {
		return "PenjualanOffice [id=" + id + ","
				+ " tanggal_transaksi=" + tanggal_transaksi + ", "
				+ " id_transaksi=" + id_transaksi + ", "
				+ " id_office=" + id_office + ","
				+ " lokasi_office=" + lokasi_office + ","
				+ " artikel=" + artikel + ","
				+ " tipe=" + tipe + ","
				+ " kategori=" + kategori + ","
				+ " nama_barang=" + nama_barang + ","
	            + " kuantitas=" + kuantitas + ","
	            + " ukuran=" + ukuran + ","
	            + " foto_barang=" + foto_barang + ","
	            + " metode_pembayaran=" + metode_pembayaran + ","
	            + " harga_satuan_barang=" + harga_satuan_barang + ","
	            + " ongkos_kirim=" + ongkos_kirim + ","
	            + " pajak_biaya=" + pajak_biaya + ","
	            + " total=" + total + ","
	            + " rowstatus=" + rowstatus + "]";
	}
}
