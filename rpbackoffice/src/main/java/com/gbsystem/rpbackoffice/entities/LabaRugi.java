package com.gbsystem.rpbackoffice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LabaRugi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable=false, insertable=false)
	private String noAkun;
	@Column(updatable=false, insertable=false)
	private String namaAkun;
	@Column(updatable=false, insertable=false)
	private double saldo;
	public Long getId() {
		return id;
	}
	public String getNoAkun() {
		return noAkun;
	}
	public String getNamaAkun() {
		return namaAkun;
	}
	public double getSaldo() {
		return saldo;
	}
	
}
