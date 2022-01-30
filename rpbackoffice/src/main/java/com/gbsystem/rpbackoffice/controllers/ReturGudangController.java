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

import com.gbsystem.rpbackoffice.entities.ReturGudang;
import com.gbsystem.rpbackoffice.services.DetailReturGudangService;
import com.gbsystem.rpbackoffice.services.ReturGudangService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/returGudang")
@CrossOrigin
public class ReturGudangController {
	
	@Autowired
	private ReturGudangService returGudangService;
	
	@Autowired
	private DetailReturGudangService detailReturGudangService;

    @GetMapping("/all")
	public ResponseEntity<List<ReturGudang>> getAll() {
        return new ResponseEntity<>(returGudangService.getAllReturGudang(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<ReturGudang>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(returGudangService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    @ResponseBody
    public ReturGudang saveProduct(@RequestBody ReturGudang returGudang) throws Exception {
    	ReturGudang returGudangResponse = returGudangService.saveReturGudang(returGudang);
    	return returGudangResponse;
    }
    
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ReturGudang update(@RequestBody ReturGudang returGudang) throws Exception {
    	
    	ReturGudang returGudangResponse = returGudangService.update(returGudang);
    	return returGudangResponse;
		
    }
    
    @GetMapping("/delete")
    public String deleteReturGudang(
    		@RequestParam("id") Long id)
    {
    	returGudangService.deleteReturGudangById(id);
    	return "redirect:/all";
    }
    
    @GetMapping("/deliveryReceipt")
	public ResponseEntity<byte []> generatePdfReturGudang(@Param("pengiriman_code") String pengiriman_code) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(detailReturGudangService.ReturGudangReport(pengiriman_code));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/DeliveryReceipt.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=DeliveryReceipt.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}
