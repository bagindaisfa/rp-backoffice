package com.gbsystem.rpbackoffice.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class PenerimaanStoreFromStore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String penerimaan_code;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date tanggal_penerimaan;
	private int id_store_asal;
	private String lokasi_store_asal;
	private int id_store_tujuan;
	private String lokasi_store_tujuan;
	private int rowstatus;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "penerimaanStoreFromStore", cascade = CascadeType.ALL)
	@Where(clause="rowstatus = 1")
	@JsonIgnoreProperties("penerimaanStoreFromStore")
    private List<DetailPenerimaanStoreFromStore> detailPenerimaanList = new ArrayList<>();
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
	public Date getTanggal_penerimaan() {
		return tanggal_penerimaan;
	}
	public void setTanggal_penerimaan(Date tanggal_penerimaan) {
		this.tanggal_penerimaan = tanggal_penerimaan;
	}
	public int getId_store_asal() {
		return id_store_asal;
	}
	public void setId_store_asal(int id_store_asal) {
		this.id_store_asal = id_store_asal;
	}
	public String getLokasi_store_asal() {
		return lokasi_store_asal;
	}
	public void setLokasi_store_asal(String lokasi_store_asal) {
		this.lokasi_store_asal = lokasi_store_asal;
	}
	public int getId_store_tujuan() {
		return id_store_tujuan;
	}
	public void setId_store_tujuan(int id_store_tujuan) {
		this.id_store_tujuan = id_store_tujuan;
	}
	public String getLokasi_store_tujuan() {
		return lokasi_store_tujuan;
	}
	public void setLokasi_store_tujuan(String lokasi_store_tujuan) {
		this.lokasi_store_tujuan = lokasi_store_tujuan;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	public List<DetailPenerimaanStoreFromStore> getDetailPenerimaanList() {
		return detailPenerimaanList;
	}
	public void setDetailPenerimaanList(List<DetailPenerimaanStoreFromStore> detailPenerimaanList) {
		this.detailPenerimaanList = detailPenerimaanList;
	}

}
