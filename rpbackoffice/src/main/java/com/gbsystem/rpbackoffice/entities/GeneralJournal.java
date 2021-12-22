package com.gbsystem.rpbackoffice.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class GeneralJournal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nomorJournal;
	private LocalDateTime tanggal_transaksi;
	private String project;
	private String project_name;
	private String rincian_transaksi;
	private int rowstatus;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "generalJournal", cascade = CascadeType.ALL)
	@Where(clause = "rowstatus = 1")
	@JsonIgnoreProperties("general_journal")
	private List<JournalDetail> journalDetail = new ArrayList<>();
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNomorJournal() {
		return nomorJournal;
	}

	public void setNomorJournal(String nomorJournal) {
		this.nomorJournal = nomorJournal;
	}

	public LocalDateTime getTanggal_transaksi() {
		return tanggal_transaksi;
	}
	public void setTanggal_transaksi(LocalDateTime tanggal_transaksi) {
		this.tanggal_transaksi = tanggal_transaksi;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getRincian_transaksi() {
		return rincian_transaksi;
	}
	public void setRincian_transaksi(String rincian_transaksi) {
		this.rincian_transaksi = rincian_transaksi;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	
	public List<JournalDetail> getJournalDetail() {
		return journalDetail;
	}
	public void setJournalDetail(List<JournalDetail> journalDetail) {
		this.journalDetail = journalDetail;
	}
	
}

