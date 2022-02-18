package com.gbsystem.rpbackoffice.responses;

public class LoginResponse {
	
	private String token;
	private Long id_pengguna;
	private String namaPengguna;
	private int id_office;
	private String lokasi_office;
	private int id_store;
	private String lokasi_store;
	private String[] akses_modul;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId_pengguna() {
		return id_pengguna;
	}

	public void setId_pengguna(Long id_pengguna) {
		this.id_pengguna = id_pengguna;
	}

	public String getNamaPengguna() {
		return namaPengguna;
	}

	public void setNamaPengguna(String namaPengguna) {
		this.namaPengguna = namaPengguna;
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

	public String[] getAkses_modul() {
		return akses_modul;
	}

	public void setAkses_modul(String[] akses_modul) {
		this.akses_modul = akses_modul;
	}
	
	

}
