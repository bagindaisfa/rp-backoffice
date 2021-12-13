package com.gbsystem.rpbackoffice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MasterKategori {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String kategori_name;
	private int rowstatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKategori_name() {
		return kategori_name;
	}
	public void setKategori_name(String kategori_name) {
		this.kategori_name = kategori_name;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	@Override
	public String toString() {
		return "MasterKategori [id=" + id + ","
				+ " kategori_name=" + kategori_name + ", "
	            + " rowstatus=" + rowstatus + "]";
	}
}
