package com.gbsystem.rpbackoffice.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbsystem.rpbackoffice.entities.ChartPenjualanPerhari;
import com.gbsystem.rpbackoffice.entities.KasKeluar;
import com.gbsystem.rpbackoffice.entities.KasMasuk;
import com.gbsystem.rpbackoffice.entities.RekapPenjualanStore;
import com.gbsystem.rpbackoffice.services.ChartPenjualanService;
import com.gbsystem.rpbackoffice.services.KasKeluarService;
import com.gbsystem.rpbackoffice.services.KasMasukService;
import com.gbsystem.rpbackoffice.services.RekapPenjualanStoreService;

@RestController
@RequestMapping("/rekapKas")
@CrossOrigin
public class RekapKasController {
	
	@Autowired
	private KasKeluarService kasKeluarService;
	@Autowired
	private KasMasukService kasMasukService;
	@Autowired
	private RekapPenjualanStoreService rekapPenjualanStoreService;
	@Autowired
	private ChartPenjualanService chartPenjualanService;
	
	@PostMapping("/addKasMasuk")
	public Map saveKasMasuk(@RequestBody KasMasuk kasMasuk) 
	{
		kasMasukService.save(kasMasuk);
		Map<String,String> response = new HashMap<>();
    	response.put("message", "Insert Success");
		return response;
	}
	
	@PostMapping("/addKasKeluar")
	public Map saveKasKeluar(@RequestBody KasKeluar kasKeluar) 
	{
		kasKeluarService.save(kasKeluar);
		Map<String,String> response = new HashMap<>();
    	response.put("message", "Insert Success");
		return response;
	}
	
	@PostMapping("/tutupKasir")
	public Map tutupKasir(@RequestBody RekapPenjualanStore rekapPenjualanStore) 
	{
		rekapPenjualanStoreService.save(rekapPenjualanStore);
		Map<String,String> response = new HashMap<>();
    	response.put("message", "Insert Success");
		return response;
	}
	
	@GetMapping("/allKas")
	public Map allKas(@Param("id_store") int id_store){
		List<KasMasuk> masuk = kasMasukService.all(id_store);
		List<KasKeluar> keluar = kasKeluarService.all(id_store);
		
		Map<String, Map<String, Object>> response = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		map.put("val A", masuk);
		map.put("val B", keluar);
		response.put("result", map);
		return response;
	}
	
	@GetMapping("/chart")
	public Map allChart(@Param("id_store") int id_store) {
		Object harian = chartPenjualanService.harian(id_store);
		
		Object mingguan = chartPenjualanService.mingguan(id_store);
				
		Object bulanan = chartPenjualanService.bulanan(id_store);
		
		Map<String, Map<String, Object>> response = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		map.put("harian", harian);
		map.put("mingguan", mingguan);
		map.put("bulanan", bulanan);
		response.put("result", map);
		return response;
	}

}
