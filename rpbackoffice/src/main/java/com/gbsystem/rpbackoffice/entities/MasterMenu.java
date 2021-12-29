package com.gbsystem.rpbackoffice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MasterMenu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String kode_menu;
	private String nama_menu;
	private int rowstatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKode_menu() {
		return kode_menu;
	}
	public void setKode_menu(String kode_menu) {
		this.kode_menu = kode_menu;
	}
	public String getNama_menu() {
		return nama_menu;
	}
	public void setNama_menu(String nama_menu) {
		this.nama_menu = nama_menu;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	
}
