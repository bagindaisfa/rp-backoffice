package com.gbsystem.rpbackoffice.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Pembelian {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pembelian_code;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(columnDefinition="date")
	private Date tanggal_transaksi;
	private String id_supplier;
	private String nama_supplier;
	private int rowstatus;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pembelian", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("pembelian")
    private List<DetailPembelian> detail_pembelian = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPembelian_code() {
		return pembelian_code;
	}

	public void setPembelian_code(String pembelian_code) {
		this.pembelian_code = pembelian_code;
	}

	public Date getTanggal_transaksi() {
		return tanggal_transaksi;
	}

	public void setTanggal_transaksi(Date tanggal_transaksi) {
		this.tanggal_transaksi = tanggal_transaksi;
	}

	public String getId_supplier() {
		return id_supplier;
	}

	public void setId_supplier(String id_supplier) {
		this.id_supplier = id_supplier;
	}

	public String getNama_supplier() {
		return nama_supplier;
	}

	public void setNama_supplier(String nama_supplier) {
		this.nama_supplier = nama_supplier;
	}

	public int getRowstatus() {
		return rowstatus;
	}

	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}

	public List<DetailPembelian> getDetail_pembelian() {
		return detail_pembelian;
	}

	public void setDetail_pembelian(List<DetailPembelian> detail_pembelian) {
		this.detail_pembelian = detail_pembelian;
	}

}
