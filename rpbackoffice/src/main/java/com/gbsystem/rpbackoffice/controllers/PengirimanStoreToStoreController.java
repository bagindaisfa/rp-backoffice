package com.gbsystem.rpbackoffice.controllers;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gbsystem.rpbackoffice.entities.PengirimanStoreToStore;
import com.gbsystem.rpbackoffice.services.PengirimanStoreToStoreReportService;
import com.gbsystem.rpbackoffice.services.PengirimanStoreToStoreService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/pengirimanStore")
@CrossOrigin
public class PengirimanStoreToStoreController {
	
	@Autowired
	private PengirimanStoreToStoreService pengirimanStoreService;
	
	private PengirimanStoreToStoreReportService pengirimanStoreToStoreReportService;

    @GetMapping("/all")
	public ResponseEntity<List<PengirimanStoreToStore>> getAll() {
        return new ResponseEntity<>(pengirimanStoreService.getAllPengirimanStore(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PengirimanStoreToStore>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(pengirimanStoreService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    @ResponseBody
    public Map saveProduct(@RequestBody PengirimanStoreToStore pengirimanStoreToStore) throws Exception {
    	pengirimanStoreService.savePengirimanStore(pengirimanStoreToStore);
    	Map<String,String> response = new HashMap<>();
    	response.put("message", "Berhasil Dikirimkan");
    	return response;
    }
    
    @PostMapping("/update")
    @ResponseBody
    public String update(@RequestBody PengirimanStoreToStore pengirimanStoreToStore) throws Exception {
    	
    	String pengirimanStoreToStoreResponse = pengirimanStoreService.update(pengirimanStoreToStore);
    	return pengirimanStoreToStoreResponse;
		
    }
    
    @GetMapping("/delete")
    public String deletePengirimanStore(@RequestParam("id") Long id)
    {
    	String pengirimanStoreToStoreResponse = pengirimanStoreService.deletePengirimanStoreById(id);
    	return pengirimanStoreToStoreResponse;
    }
    
    @GetMapping("/transferRequest")
	public ResponseEntity<byte []> generatePdfTfRequest(@Param("pengiriman_code") String pengiriman_code) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pengirimanStoreToStoreReportService.TransferRequestStore(pengiriman_code));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/TransferRequest.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=TransferRequest.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}

}
