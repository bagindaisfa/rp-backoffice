package com.gbsystem.rpbackoffice.entities;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChartPenjualanPerhari {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable=false, insertable=false)
	private Double total;
	
	@Column(updatable=false, insertable=false)
	private String hari;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTotal() {
		DecimalFormat format = new DecimalFormat("0.#");
		return format.format(total);
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getHari() {
		return hari;
	}

	public void setHari(String hari) {
		this.hari = hari;
	}
	
	
}
