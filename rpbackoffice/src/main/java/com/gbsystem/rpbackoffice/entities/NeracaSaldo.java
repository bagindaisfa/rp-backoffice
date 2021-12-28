package com.gbsystem.rpbackoffice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NeracaSaldo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable=false, insertable=false)
	private String noAkun;
	@Column(updatable=false, insertable=false)
	private String nama_akun;
	@Column(updatable=false, insertable=false)
	private double debit_amount;
	@Column(updatable=false, insertable=false)
	private double credit_amount;
	public String getNoAkun() {
		return noAkun;
	}
	public String getNama_akun() {
		return nama_akun;
	}
	public double getDebit_amount() {
		return debit_amount;
	}
	public double getCredit_amount() {
		return credit_amount;
	}
	
}
