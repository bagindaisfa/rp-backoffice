package com.gbsystem.rpbackoffice.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class PengirimanStoreToStore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pengiriman_code;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(columnDefinition="date")
	private Date tanggal_pengiriman;
	private int id_store_asal;
	private String lokasi_store_asal;
	private int id_store_tujuan;
	private String lokasi_store_tujuan;
	private int rowstatus;
	private int id_karyawan;
	private String nama_karyawan;
	private String keterangan;
	@Column(updatable=false, insertable=false)
	private Double total_pindah;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pengirimanStoreToStore", cascade = CascadeType.ALL)
	@Where(clause="rowstatus = 1")
	@JsonIgnoreProperties("pengirimanStoreToStore")
    private List<DetailPengirimanStoreToStore> detailPengirimanList = new ArrayList<>();
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
	public int getId_store_tujuan() {
		return id_store_tujuan;
	}
	public void setId_store_tujuan(int id_store_tujuan) {
		this.id_store_tujuan = id_store_tujuan;
	}
	public String getLokasi_store_tujuan() {
		return lokasi_store_tujuan;
	}
	public void setLokasi_store_tujuan(String lokasi_store_tujuan) {
		this.lokasi_store_tujuan = lokasi_store_tujuan;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	
	public int getId_karyawan() {
		return id_karyawan;
	}
	public void setId_karyawan(int id_karyawan) {
		this.id_karyawan = id_karyawan;
	}
	public String getNama_karyawan() {
		return nama_karyawan;
	}
	public void setNama_karyawan(String nama_karyawan) {
		this.nama_karyawan = nama_karyawan;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public Double getTotal_pindah() {
		return total_pindah;
	}
	public void setTotal_pindah(Double total_pindah) {
		this.total_pindah = total_pindah;
	}
	public List<DetailPengirimanStoreToStore> getDetailPengirimanList() {
		return detailPengirimanList;
	}
	public void setDetailPengirimanList(List<DetailPengirimanStoreToStore> detailPengirimanList) {
		this.detailPengirimanList = detailPengirimanList;
	}
	
}
