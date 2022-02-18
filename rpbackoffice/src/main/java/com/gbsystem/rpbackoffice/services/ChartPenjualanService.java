package com.gbsystem.rpbackoffice.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.repository.ChartPenjualanBulananRepository;
import com.gbsystem.rpbackoffice.repository.ChartPenjualanHarianRepository;
import com.gbsystem.rpbackoffice.repository.ChartPenjualanMingguanRepository;

import com.gbsystem.rpbackoffice.entities.ChartPenjualanPerbulan;
import com.gbsystem.rpbackoffice.entities.ChartPenjualanPerhari;
import com.gbsystem.rpbackoffice.entities.ChartPenjualanPerminggu;

@Service
public class ChartPenjualanService {
	@Autowired
	private ChartPenjualanHarianRepository eHarianRepo;
	
	@Autowired
	private ChartPenjualanMingguanRepository eMingguanRepo;
	
	@Autowired
	private ChartPenjualanBulananRepository eBulananRepo;
	
	public Map harian (int id_store) {
		ChartPenjualanPerhari senin = new ChartPenjualanPerhari();
		senin = eHarianRepo.totalMon(id_store);
		
		ChartPenjualanPerhari selasa = new ChartPenjualanPerhari();
		selasa = eHarianRepo.totalTue(id_store);
		
		ChartPenjualanPerhari rabu = new ChartPenjualanPerhari();
		rabu = eHarianRepo.totalWed(id_store);
		
		ChartPenjualanPerhari kamis = new ChartPenjualanPerhari();
		kamis = eHarianRepo.totalThu(id_store);
		
		ChartPenjualanPerhari jumat = new ChartPenjualanPerhari();
		jumat = eHarianRepo.totalFri(id_store);
		
		ChartPenjualanPerhari sabtu = new ChartPenjualanPerhari();
		sabtu = eHarianRepo.totalSat(id_store);
		
		ChartPenjualanPerhari minggu = new ChartPenjualanPerhari();
		minggu = eHarianRepo.totalSun(id_store);
		
		Map<String, Object> response = new HashMap<>();
		response.put("Mon", senin);
		response.put("Tue", selasa);
		response.put("Wed", rabu);
		response.put("Thu", kamis);
		response.put("Fri", jumat);
		response.put("Sat", sabtu);
		response.put("Sun", minggu);
		return response;
	}
	
	public Map bulanan (int id_store) {
		ChartPenjualanPerbulan jan = new ChartPenjualanPerbulan();
		jan = eBulananRepo.totalJan(id_store);
		ChartPenjualanPerbulan feb = new ChartPenjualanPerbulan();
		feb = eBulananRepo.totalFeb(id_store);
		ChartPenjualanPerbulan mar = new ChartPenjualanPerbulan();
		mar = eBulananRepo.totalMar(id_store);
		ChartPenjualanPerbulan apr = new ChartPenjualanPerbulan();
		apr = eBulananRepo.totalApr(id_store);
		ChartPenjualanPerbulan mei = new ChartPenjualanPerbulan();
		mei = eBulananRepo.totalMay(id_store);
		ChartPenjualanPerbulan jun = new ChartPenjualanPerbulan();
		jun = eBulananRepo.totalJun(id_store);
		ChartPenjualanPerbulan jul = new ChartPenjualanPerbulan();
		jul = eBulananRepo.totalJul(id_store);
		ChartPenjualanPerbulan agt = new ChartPenjualanPerbulan();
		agt = eBulananRepo.totalAug(id_store);
		ChartPenjualanPerbulan sep = new ChartPenjualanPerbulan();
		sep = eBulananRepo.totalSept(id_store);
		ChartPenjualanPerbulan oct = new ChartPenjualanPerbulan();
		oct = eBulananRepo.totalOct(id_store);
		ChartPenjualanPerbulan nov = new ChartPenjualanPerbulan();
		nov = eBulananRepo.totalNov(id_store);
		ChartPenjualanPerbulan dec = new ChartPenjualanPerbulan();
		dec = eBulananRepo.totalDec(id_store);
		
		Map<String, Object> response = new HashMap<>();
		response.put("Jan", jan);
        response.put("Feb", feb);
        response.put("Mar", mar);
        response.put("Apr", apr);
        response.put("Mei", mei);
        response.put("Jun", jun);
        response.put("Jul", jul);
        response.put("Agt", agt);
        response.put("Sep", sep);
        response.put("Oct", oct);
        response.put("Nov", nov);
        response.put("Dec", dec);
		return response;
	}
	
	public Map mingguan (int id_store) {
		ChartPenjualanPerminggu week1 = new ChartPenjualanPerminggu();
		week1 = eMingguanRepo.totalFirstWeek(id_store);
		
		ChartPenjualanPerminggu week2 = new ChartPenjualanPerminggu();
		week2 = eMingguanRepo.totalSecondWeek(id_store);
		
		ChartPenjualanPerminggu week3 = new ChartPenjualanPerminggu();
		week3 = eMingguanRepo.totalThirdWeek(id_store);
		
		ChartPenjualanPerminggu week4 = new ChartPenjualanPerminggu();
		week4 = eMingguanRepo.totalFourthWeek(id_store);
		
		Map<String, Object> response = new HashMap<>();
		response.put("Week 1", week1);
		response.put("Week 2", week2);
		response.put("Week 3", week3);
		response.put("Week 4", week4);
		return response;
	}

}
