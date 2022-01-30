package com.gbsystem.rpbackoffice.controllers;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

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

import com.gbsystem.rpbackoffice.entities.PengirimanOfficeToStore;
import com.gbsystem.rpbackoffice.services.PengirimanGudangToStoreReportService;
import com.gbsystem.rpbackoffice.services.PengirimanOfficeToStoreService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/pengirimanOffice")
@CrossOrigin
public class PengirimanOfficeToStoreController {
	
	@Autowired
	private PengirimanOfficeToStoreService pengirimanGudangService;
	
	@Autowired
	private PengirimanGudangToStoreReportService pengirimanGudangToStoreReportService;

    @GetMapping("/all")
	public ResponseEntity<List<PengirimanOfficeToStore>> getAll() {
        return new ResponseEntity<>(pengirimanGudangService.getAllPengirimanGudang(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PengirimanOfficeToStore>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(pengirimanGudangService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    @ResponseBody
    public PengirimanOfficeToStore saveProduct(@RequestBody PengirimanOfficeToStore pengirimanOfficeToStore) throws Exception {
    	PengirimanOfficeToStore pengirimanResponse = pengirimanGudangService.savePengirimanOffice(pengirimanOfficeToStore);
    	return pengirimanResponse;
    	
    }
    
    @PostMapping("/update")
    @ResponseBody
    public PengirimanOfficeToStore update(@RequestBody PengirimanOfficeToStore pengirimanOfficeToStore) throws Exception {
    	
    	PengirimanOfficeToStore pengirimanResponse = pengirimanGudangService.update(pengirimanOfficeToStore);
    	return pengirimanResponse;
		
    }
    
    @GetMapping("/delete")
    public String deletePengirimanGudang(@RequestParam("id") Long id)
    {
    	pengirimanGudangService.deletePengirimanGudangById(id);
    	return "redirect:/all";
    }

    @GetMapping("/transferRequest")
	public ResponseEntity<byte []> generatePdfTfRequest(@Param("pengiriman_code") String pengiriman_code) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pengirimanGudangToStoreReportService.TransferRequest(pengiriman_code));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/TransferRequest.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=TransferRequest.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}
