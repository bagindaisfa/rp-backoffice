package com.gbsystem.rpbackoffice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MasterOffice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String office_name;
	private String no_tlpn;
	private String alamat;
	private String kepala_office;
	private int rowstatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOffice_name() {
		return office_name;
	}
	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}
	public String getNo_tlpn() {
		return no_tlpn;
	}
	public void setNo_tlpn(String no_tlpn) {
		this.no_tlpn = no_tlpn;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getKepala_office() {
		return kepala_office;
	}
	public void setKepala_office(String kepala_office) {
		this.kepala_office = kepala_office;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	@Override
	public String toString() {
		return "MasterOffice [id=" + id + ","
				+ " office_name=" + office_name + ", "
				+ " no_tlpn=" + no_tlpn + ", "
				+ " alamat=" + alamat + ", "
				+ " kepala_office=" + kepala_office + ", "
	            + " rowstatus=" + rowstatus + "]";
	}
}
