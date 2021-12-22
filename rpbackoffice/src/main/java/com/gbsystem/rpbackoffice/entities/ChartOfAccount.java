package com.gbsystem.rpbackoffice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChartOfAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nama_akun;
	private String kelompok;
	private String tipe;
	private String relasi;
	private String jenis_beban;
	private int rowstatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNama_akun() {
		return nama_akun;
	}
	public void setNama_akun(String nama_akun) {
		this.nama_akun = nama_akun;
	}
	public String getKelompok() {
		return kelompok;
	}
	public void setKelompok(String kelompok) {
		this.kelompok = kelompok;
	}
	public String getTipe() {
		return tipe;
	}
	public void setTipe(String tipe) {
		this.tipe = tipe;
	}
	public String getRelasi() {
		return relasi;
	}
	public void setRelasi(String relasi) {
		this.relasi = relasi;
	}
	public String getJenis_beban() {
		return jenis_beban;
	}
	public void setJenis_beban(String jenis_beban) {
		this.jenis_beban = jenis_beban;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	@Override
	public String toString() {
		return "ChartOfAccount [id=" + id + ","
				+ " nama_akun=" + nama_akun + ", "
				+ " kelompok=" + kelompok + ", "
				+ " tipe=" + tipe + ", "
				+ " relasi=" + relasi + ", "
				+ " rowstatus=" + rowstatus + "]";
	}
}
