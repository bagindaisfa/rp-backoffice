package com.gbsystem.rpbackoffice.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class JournalDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nomorJournal;
	private String nama_akun;
	private double debit_amount;
	private double credit_amount;
	private int rowstatus;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "generalJournal_id", referencedColumnName = "id")
	@JsonIgnoreProperties("journalDetail")
    private GeneralJournal generalJournal;
	
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

	public String getNama_akun() {
		return nama_akun;
	}

	public void setNama_akun(String nama_akun) {
		this.nama_akun = nama_akun;
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

	public int getRowstatus() {
		return rowstatus;
	}

	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}

	public GeneralJournal getGeneralJournal() {
		return generalJournal;
	}

	public void setGeneralJournal(GeneralJournal generalJournal) {
		this.generalJournal = generalJournal;
	}
	
}
