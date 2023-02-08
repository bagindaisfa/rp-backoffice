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
public class ProformaInvoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(columnDefinition="date")
	private Date tanggal_transaksi;
	private String pi_no;
	private String id_transaksi;
	private int id_office;
	private String lokasi_office;
	private String no_hp_pelanggan;
	private String nama_pelanggan;
	private int id_karyawan;
	private String nama_karyawan;
	private Double diskon;
	private String diskon_remark;
	private String metode_pembayaran;
	private String bank_name;
	private String no_rek;
	private Double ongkos_kirim;
	private String ekspedisi;
	private Double pajak_biaya;
	private Double dp;
	private Double total_penjualan;
	private Double total_dp;
	private int rowstatus;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "proformaInvoice", cascade = CascadeType.ALL)
	@Where(clause="rowstatus = 1")
	@JsonIgnoreProperties("proformaInvoice")
    private List<DetailProformaInvoice> detail_proforma_invoice = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getTanggal_transaksi() {
		return tanggal_transaksi;
	}
	public void setTanggal_transaksi(Date tanggal_transaksi) {
		this.tanggal_transaksi = tanggal_transaksi;
	}
	public String getPi_no() {
		return pi_no;
	}
	public void setPi_no(String pi_no) {
		this.pi_no = pi_no;
	}
	public String getId_transaksi() {
		return id_transaksi;
	}
	public void setId_transaksi(String id_transaksi) {
		this.id_transaksi = id_transaksi;
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
	
	public String getNo_hp_pelanggan() {
		return no_hp_pelanggan;
	}
	public void setNo_hp_pelanggan(String no_hp_pelanggan) {
		this.no_hp_pelanggan = no_hp_pelanggan;
	}
	public String getNama_pelanggan() {
		return nama_pelanggan;
	}
	public void setNama_pelanggan(String nama_pelanggan) {
		this.nama_pelanggan = nama_pelanggan;
	}
	public int getId_karyawan() {
		return id_karyawan;
	}
	public void setId_karyawan(int id_karyawan) {
		this.id_karyawan = id_karyawan;
	}
	public String getNama_karyawan() {
		return nama_karyawan;
	}
	public void setNama_karyawan(String nama_karyawan) {
		this.nama_karyawan = nama_karyawan;
	}
	
	public Double getDiskon() {
		return diskon;
	}
	public void setDiskon(Double diskon) {
		this.diskon = diskon;
	}
	public String getDiskon_remark() {
		return diskon_remark;
	}
	public void setDiskon_remark(String diskon_remark) {
		this.diskon_remark = diskon_remark;
	}
	public String getMetode_pembayaran() {
		return metode_pembayaran;
	}
	public void setMetode_pembayaran(String metode_pembayaran) {
		this.metode_pembayaran = metode_pembayaran;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getNo_rek() {
		return no_rek;
	}
	public void setNo_rek(String no_rek) {
		this.no_rek = no_rek;
	}
	public Double getOngkos_kirim() {
		return ongkos_kirim;
	}
	public void setOngkos_kirim(Double ongkos_kirim) {
		this.ongkos_kirim = ongkos_kirim;
	}
	public String getEkspedisi() {
		return ekspedisi;
	}
	public void setEkspedisi(String ekspedisi) {
		this.ekspedisi = ekspedisi;
	}
	public Double getPajak_biaya() {
		return pajak_biaya;
	}
	public void setPajak_biaya(Double pajak_biaya) {
		this.pajak_biaya = pajak_biaya;
	}
	public Double getTotal_penjualan() {
		return total_penjualan;
	}
	public void setTotal_penjualan(Double total_penjualan) {
		this.total_penjualan = total_penjualan;
	}
	public Double getDp() {
		return dp;
	}
	public void setDp(Double dp) {
		this.dp = dp;
	}
	public Double getTotal_dp() {
		return total_dp;
	}
	public void setTotal_dp(Double total_dp) {
		this.total_dp = total_dp;
	}
	public int getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(int rowstatus) {
		this.rowstatus = rowstatus;
	}
	public List<DetailProformaInvoice> getDetail_proforma_invoice() {
		return detail_proforma_invoice;
	}
	public void setDetail_proforma_invoice(List<DetailProformaInvoice> detail_proforma_invoice) {
		this.detail_proforma_invoice = detail_proforma_invoice;
	}
}
