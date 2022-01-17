package com.gbsystem.rpbackoffice.controllers;

import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbsystem.rpbackoffice.services.PenerimaanByStoreReportService;
import com.gbsystem.rpbackoffice.services.PenerimaanBySupplierReportService;
import com.gbsystem.rpbackoffice.services.PengirimanGudangToStoreReportService;
import com.gbsystem.rpbackoffice.services.PengirimanStoreToStoreReportService;
import com.gbsystem.rpbackoffice.services.PenyimpananBarangKeluarReportService;
import com.gbsystem.rpbackoffice.services.PenyimpananBarangMasukReportService;
import com.gbsystem.rpbackoffice.services.PenyimpananStockOpnameReportService;
import com.gbsystem.rpbackoffice.services.PurchaseStoreByArticleService;
import com.gbsystem.rpbackoffice.services.PurchaseStoreBySummaryService;
import com.gbsystem.rpbackoffice.services.SalesByOfficeService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/report")
@CrossOrigin
public class ReportController {
	@Autowired
	private PurchaseStoreBySummaryService purchaseStoreBySummaryService;
	
	@Autowired
	private PurchaseStoreByArticleService purchaseStoreByArticleService;
	
	@Autowired
	private PenyimpananBarangMasukReportService penyimpananBarangMasukReportService;

	@Autowired
	private PenyimpananBarangKeluarReportService penyimpananBarangKeluarReportService;

	@Autowired
	private PenyimpananStockOpnameReportService penyimpananStockOpnameReportService;

	@Autowired
	private PengirimanGudangToStoreReportService pengirimanGudangToStoreReportService;

	@Autowired
	private PengirimanStoreToStoreReportService pengirimanStoreToStoreReportService;

	@Autowired
	private PenerimaanByStoreReportService penerimaanByStoreReportService;

	@Autowired
	private PenerimaanBySupplierReportService penerimaanBySupplierReportService;

	@Autowired
	private SalesByOfficeService salesByOfficeService;
	
	@GetMapping("/purchaseStoreBySummary")
	public ResponseEntity<byte []> generatePdfSummary(@Param("no_hp_pelanggan") String no_hp_pelanggan) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(purchaseStoreBySummaryService.PurchaseStoreBySummary(no_hp_pelanggan));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/PurchaseStoreBySummary.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PurchaseStoreBySummary.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/purchaseStoreByArticle")
	public ResponseEntity<byte []> generatePdfArticle(@Param("artikel") String artikel) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(purchaseStoreByArticleService.PurchaseStoreByArticle(artikel));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/PurchaseStoreByArticle.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PurchaseStoreByArticle.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/penyimpananMasuk")
	public ResponseEntity<byte []> generatePdfPenyimpananMasuk(@Param("tanggal_transaksi") @DateTimeFormat(pattern="yyyy-MM-dd") Date tanggal_transaksi) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(penyimpananBarangMasukReportService.PenyimpananBarangMasukReport(tanggal_transaksi));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/PenyimpananMasuk.jrxml"));

		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();

		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PenyimpananBarangMasukReport.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/penyimpananKeluar")
	public ResponseEntity<byte []> generatePdfPenyimpananKeluar(@Param("tanggal_transaksi") @DateTimeFormat(pattern="yyyy-MM-dd") Date tanggal_transaksi) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(penyimpananBarangKeluarReportService.PenyimpananBarangKeluarReport(tanggal_transaksi));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/PenyimpananMasuk.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PenyimpananBarangKeluarReport.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/stockOpname")
	public ResponseEntity<byte []> generatePdfStockOpname(@Param("tanggal_transaksi") @DateTimeFormat(pattern="yyyy-MM-dd") Date tanggal_transaksi) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(penyimpananStockOpnameReportService.PenyimpananStockOpnameReport(tanggal_transaksi));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/PenyimpananMasuk.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PenyimpananStockOpnameReport.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/pengirimanGudangToStore")
	public ResponseEntity<byte []> generatePdfPengirimanGudangToStore(@Param("tanggal_transaksi") @DateTimeFormat(pattern="yyyy-MM-dd") Date tanggal_transaksi) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pengirimanGudangToStoreReportService.PengirimanGudangToStoreReport(tanggal_transaksi));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/PenyimpananMasuk.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PengirimanGudangToStoreReport.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/pengirimanStoreToStore")
	public ResponseEntity<byte []> generatePdfPengirimanStoreToStore(@Param("tanggal_transaksi") @DateTimeFormat(pattern="yyyy-MM-dd") Date tanggal_transaksi) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pengirimanStoreToStoreReportService.PengirimanStoreToStoreReport(tanggal_transaksi));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/PenyimpananMasuk.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PengirimanStoreToStoreReport.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/penerimaanByStore")
	public ResponseEntity<byte []> generatePdfPenerimaanByStore(@Param("tanggal_transaksi") @DateTimeFormat(pattern="yyyy-MM-dd") Date tanggal_transaksi) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(penerimaanByStoreReportService.PenerimaanByStoreReport(tanggal_transaksi));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/PenyimpananMasuk.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PenerimaanByStoreReport.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/penerimaanBySupplier")
	public ResponseEntity<byte []> generatePdfPenerimaanBySupplier(@Param("tanggal_transaksi") @DateTimeFormat(pattern="yyyy-MM-dd") Date tanggal_transaksi) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(penerimaanBySupplierReportService.PenerimaanBySupplierReport(tanggal_transaksi));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/PenyimpananMasuk.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PenerimaanBySupplierReport.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/salesByOffice")
	public ResponseEntity<byte []> generatePdfSalesOffice(@Param("id_office") String id_office) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(salesByOfficeService.SalesByOffice(id_office));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/SalesByOffice.jrxml"));

		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=SalesByOffice.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}
