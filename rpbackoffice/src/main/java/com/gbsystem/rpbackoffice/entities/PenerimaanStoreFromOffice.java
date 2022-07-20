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
public class PenerimaanStoreFromOffice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String penerimaan_code;
	private String pengiriman_code;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(columnDefinition="date")
	private Date tanggal_penerimaan;
	private int id_office;
	private String lokasi_office;
	private int id_store;
	private String lokasi_store;
	private int rowstatus;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "penerimaanStoreFromOffice", cascade = CascadeType.ALL)
	@Where(clause="rowstatus = 1")
	@JsonIgnoreProperties("penerimaanStoreFromOffice")
    private List<DetailPenerimaanStoreFromOffice> detailPenerimaanList = new ArrayList<>();
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
	public String getPengiriman_code() {
		return pengiriman_code;
	}
	public void setPengiriman_code(String pengiriman_code) {
		this.pengiriman_code = pengiriman_code;
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
	public int getId_store() {
		return id_store;
	}
	public void setId_store(int id_store) {
		this.id_store = id_store;
	}
	public String getLokasi_store() {
		return lokasi_store;
	}
	public void setLokasi_store(String lokasi_store) {
		this.lokasi_store = lokasi_store;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	public List<DetailPenerimaanStoreFromOffice> getDetailPenerimaanList() {
		return detailPenerimaanList;
	}
	public void setDetailPenerimaanList(List<DetailPenerimaanStoreFromOffice> detailPenerimaanList) {
		this.detailPenerimaanList = detailPenerimaanList;
	}

}
