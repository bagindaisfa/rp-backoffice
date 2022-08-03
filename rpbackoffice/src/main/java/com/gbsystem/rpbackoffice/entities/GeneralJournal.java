package com.gbsystem.rpbackoffice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class GeneralJournal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomorJournal;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(columnDefinition="date")
	private Date tanggal_transaksi;
	private String noAkun;
	private String nama_akun;
	private String kelompok;
	private String rincian_transaksi;
	private double debit_amount;
	private double credit_amount;
	private int project_id;
	private String project_name;
	@Column(updatable=false, insertable=false)
	private double saldo_akhir;
	private int rowstatus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomorJournal() {
		return nomorJournal;
	}
	public void setNomorJournal(String nomorJournal) {
		this.nomorJournal = nomorJournal;
	}
	public Date getTanggal_transaksi() {
		return tanggal_transaksi;
	}
	public void setTanggal_transaksi(Date tanggal_transaksi) {
		this.tanggal_transaksi = tanggal_transaksi;
	}
	public String getNoAkun() {
		return noAkun;
	}
	public void setNoAkun(String noAkun) {
		this.noAkun = noAkun;
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
	public String getRincian_transaksi() {
		return rincian_transaksi;
	}
	public void setRincian_transaksi(String rincian_transaksi) {
		this.rincian_transaksi = rincian_transaksi;
	}
	public double getDebit_amount() {
		return debit_amount;
	}
	public void setDebit_amount(double debit_amount) {
		this.debit_amount = debit_amount;
	}
	public double getCredit_amount() {
		return credit_amount;
	}
	public void setCredit_amount(double credit_amount) {
		this.credit_amount = credit_amount;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	public double getSaldo_akhir() {
		return saldo_akhir;
	}
	
}

