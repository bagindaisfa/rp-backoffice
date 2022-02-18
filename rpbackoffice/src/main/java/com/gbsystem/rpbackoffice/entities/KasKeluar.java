package com.gbsystem.rpbackoffice.entities;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class KasKeluar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Double nominalKasKeluar;
	@JsonFormat(pattern="EEEE, dd MM yyyy | HH:mm")
	private LocalDateTime waktuKeluar;
	private int id_store;
	private String lokasi_store;
	private int id_karyawan;
	private String nama_karyawan;
	private String catatan;
	private int rowstatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNominalKasKeluar() {
		DecimalFormat decimalFormatter = new DecimalFormat("############");
		return decimalFormatter.format(Double.parseDouble(String.valueOf(nominalKasKeluar)));
	}
	public void setNominalKasKeluar(Double nominalKasKeluar) {
		this.nominalKasKeluar = nominalKasKeluar;
	}
	public LocalDateTime getWaktuKeluar() {
		return waktuKeluar;
	}
	public void setWaktuKeluar(LocalDateTime waktuKeluar) {
		this.waktuKeluar = waktuKeluar;
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
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	
}
