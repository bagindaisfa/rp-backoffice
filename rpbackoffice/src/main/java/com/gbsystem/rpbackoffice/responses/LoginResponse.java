package com.gbsystem.rpbackoffice.responses;

public class LoginResponse {
	
	private String token;
	private String namaPengguna;
	private String[] akses_modul;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNamaPengguna() {
		return namaPengguna;
	}

	public void setNamaPengguna(String namaPengguna) {
		this.namaPengguna = namaPengguna;
	}

	public String[] getAkses_modul() {
		return akses_modul;
	}

	public void setAkses_modul(String[] akses_modul) {
		this.akses_modul = akses_modul;
	}
	
	

}
