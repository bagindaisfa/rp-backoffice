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
	private String noAkun;
	private String namaAkun;
	private String kelompok;
	private String tipe;
	private String saldo_normal;
	private double saldo_awal;
	private int is_delete;
	private int rowstatus;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNoAkun() {
		return noAkun;
	}

	public void setNoAkun(String noAkun) {
		this.noAkun = noAkun;
	}

	public String getNamaAkun() {
		return namaAkun;
	}

	public void setNamaAkun(String namaAkun) {
		this.namaAkun = namaAkun;
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

	public String getSaldo_normal() {
		return saldo_normal;
	}

	public void setSaldo_normal(String saldo_normal) {
		this.saldo_normal = saldo_normal;
	}

	public double getSaldo_awal() {
		return saldo_awal;
	}

	public void setSaldo_awal(double saldo_awal) {
		this.saldo_awal = saldo_awal;
	}

	public int getRowstatus() {
		return rowstatus;
	}

	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	
	public int getIs_delets() {
		return is_delete;
	}

	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}
	@Override
	public String toString() {
		return "ChartOfAccount [id=" + id + ","
				+ " noAkun=" + noAkun + ", "
				+ " namaAkun=" + namaAkun + ", "
				+ " kelompok=" + kelompok + ", "
				+ " tipe=" + tipe + ", "
				+ " saldo_normal=" + saldo_normal + ", "
				+ " saldo_awal=" + saldo_awal + ", "
				+ " rowstatus=" + rowstatus + "]";
	}
}
