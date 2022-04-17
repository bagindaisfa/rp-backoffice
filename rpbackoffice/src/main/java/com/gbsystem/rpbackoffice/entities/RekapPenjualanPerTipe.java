package com.gbsystem.rpbackoffice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RekapPenjualanPerTipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type_name;
	private String harga_jual;
	private Double total_terjual;
	private Double penjualan_kotor;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getHarga_jual() {
		return harga_jual;
	}
	public void setHarga_jual(String harga_jual) {
		this.harga_jual = harga_jual;
	}
	public Double getTotal_terjual() {
		return total_terjual;
	}
	public void setTotal_terjual(Double total_terjual) {
		this.total_terjual = total_terjual;
	}
	public Double getPenjualan_kotor() {
		return penjualan_kotor;
	}
	public void setPenjualan_kotor(Double penjualan_kotor) {
		this.penjualan_kotor = penjualan_kotor;
	}
}
