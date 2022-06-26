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

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class PenerimaanSupplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String penerimaan_code;
	private String pembelian_code;
	@Column(columnDefinition = "date") @JsonFormat(pattern="yyyy-MM-dd")
	private Date tanggal_penerimaan;
	private int id_office;
	private String lokasi_office;
	private String id_supplier;
	private String nama_supplier;
	private int rowstatus;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "penerimaanSupplier", cascade = CascadeType.ALL)
	@Where(clause="rowstatus = 1")
	@JsonIgnoreProperties("penerimaanSupplier")
	List<DetailPenerimaanSupplier> detailPenerimaanList = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPenerimaan_code() {
		return penerimaan_code;
	}
	public void setPenerimaan_code(String penerimaan_code) {
		this.penerimaan_code = penerimaan_code;
	}
	public String getPembelian_code() {
		return pembelian_code;
	}
	public void setPembelian_code(String pembelian_code) {
		this.pembelian_code = pembelian_code;
	}
	public Date getTanggal_penerimaan() {
		return tanggal_penerimaan;
	}
	public void setTanggal_penerimaan(Date tanggal_penerimaan) {
		this.tanggal_penerimaan = tanggal_penerimaan;
	}
	public int getId_office() {
		return id_office;
	}
	public void setId_office(int id_office) {
		this.id_office = id_office;
	}
	public String getLokasi_office() {
		return lokasi_office;
	}
	public void setLokasi_office(String lokasi_office) {
		this.lokasi_office = lokasi_office;
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
	public List<DetailPenerimaanSupplier> getDetailPenerimaanList() {
		return detailPenerimaanList;
	}
	public void setDetailPenerimaanList(List<DetailPenerimaanSupplier> detailPenerimaanList) {
		this.detailPenerimaanList = detailPenerimaanList;
	}
	
}
