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
public class ReturGudang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pengiriman_code;
	@Column(columnDefinition = "date") @JsonFormat(pattern="yyyy-MM-dd")
	private Date tanggal_retur;
	private int id_store_asal;
	private String lokasi_store_asal;
	private int id_office_tujuan;
	private String lokasi_office_tujuan;
	private int rowstatus;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "returGudang", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("returGudang")
    private List<DetailReturGudang> detail_pengiriman = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPengiriman_code() {
		return pengiriman_code;
	}
	public void setPengiriman_code(String pengiriman_code) {
		this.pengiriman_code = pengiriman_code;
	}
	public Date getTanggal_retur() {
		return tanggal_retur;
	}
	public void setTanggal_retur(Date tanggal_retur) {
		this.tanggal_retur = tanggal_retur;
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
	public int getId_office_tujuan() {
		return id_office_tujuan;
	}
	public void setId_office_tujuan(int id_office_tujuan) {
		this.id_office_tujuan = id_office_tujuan;
	}
	public String getLokasi_office_tujuan() {
		return lokasi_office_tujuan;
	}
	public void setLokasi_office_tujuan(String lokasi_office_tujuan) {
		this.lokasi_office_tujuan = lokasi_office_tujuan;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	public List<DetailReturGudang> getDetail_pengiriman() {
		return detail_pengiriman;
	}
	public void setDetail_pengiriman(List<DetailReturGudang> detail_pengiriman) {
		this.detail_pengiriman = detail_pengiriman;
	}
}
