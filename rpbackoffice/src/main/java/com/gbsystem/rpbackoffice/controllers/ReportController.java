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

import com.gbsystem.rpbackoffice.services.InvoiceService;
import com.gbsystem.rpbackoffice.services.PembelianService;
import com.gbsystem.rpbackoffice.services.PenerimaanByStoreReportService;
import com.gbsystem.rpbackoffice.services.PenerimaanBySupplierReportService;
import com.gbsystem.rpbackoffice.services.PengirimanGudangToStoreReportService;
import com.gbsystem.rpbackoffice.services.PengirimanStoreToStoreReportService;
import com.gbsystem.rpbackoffice.services.PenyimpananBarangKeluarReportService;
import com.gbsystem.rpbackoffice.services.PenyimpananBarangMasukReportService;
import com.gbsystem.rpbackoffice.services.PenyimpananStockOpnameReportService;
import com.gbsystem.rpbackoffice.services.ProformaInvoiceService;
import com.gbsystem.rpbackoffice.services.PurchaseStoreByArticleService;
import com.gbsystem.rpbackoffice.services.PurchaseStoreBySummaryService;
import com.gbsystem.rpbackoffice.services.SalesByOfficeService;
import com.gbsystem.rpbackoffice.services.SalesByStoreService;

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
	
	@Autowired
	private SalesByStoreService salesByStoreService;
	
	@Autowired
	private PembelianService pembelianService;
	
	@Autowired
	private InvoiceService invoiceOfficeService;
	
	@Autowired
	private ProformaInvoiceService proformaInvoiceService;
	
	@GetMapping("/purchaseStoreBySummary")
	public ResponseEntity<byte []> generatePdfSummary(@Param("no_hp_pelanggan") String no_hp_pelanggan, @Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, @Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(purchaseStoreBySummaryService.PurchaseStoreBySummary(no_hp_pelanggan, date_from, date_to));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/PurchaseStoreBySummary.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PurchaseStoreBySummary.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/purchaseStoreByArticle")
	public ResponseEntity<byte []> generatePdfArticle(@Param("artikel") String artikel, @Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, @Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(purchaseStoreByArticleService.PurchaseStoreByArticle(artikel, date_from, date_to));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/PurchaseStoreByArticle.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PurchaseStoreByArticle.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/penyimpananMasuk")
	public ResponseEntity<byte []> generatePdfPenyimpananMasuk(@Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, @Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(penyimpananBarangMasukReportService.PenyimpananBarangMasukReport(date_from, date_to));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/PenyimpananMasuk.jrxml"));

		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();

		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PenyimpananBarangMasukReport.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/penyimpananKeluar")
	public ResponseEntity<byte []> generatePdfPenyimpananKeluar(@Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, @Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(penyimpananBarangKeluarReportService.PenyimpananBarangKeluarReport(date_from, date_to));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/PenyimpananKeluar.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PenyimpananBarangKeluarReport.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/stockOpname")
	public ResponseEntity<byte []> generatePdfStockOpname(@Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, @Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(penyimpananStockOpnameReportService.PenyimpananStockOpnameReport(date_from, date_to));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/PenyimpananStockOpname.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PenyimpananStockOpnameReport.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/stockOpnameStore")
	public ResponseEntity<byte []> generatePdfStockOpnameStore(@Param("id_store") int id_store, @Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, @Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(penyimpananStockOpnameReportService.PenyimpananStockOpnameStoreReport(id_store,date_from, date_to));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/PenyimpananStockOpnameStore.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PenyimpananStockOpnameStoreReport.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/pengirimanGudangToStore")
	public ResponseEntity<byte []> generatePdfPengirimanGudangToStore(@Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, @Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pengirimanGudangToStoreReportService.PengirimanGudangToStoreReport(date_from, date_to));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/PengirimanGudangToStore.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PengirimanGudangToStoreReport.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/pengirimanStoreToStore")
	public ResponseEntity<byte []> generatePdfPengirimanStoreToStore(@Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, @Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pengirimanStoreToStoreReportService.PengirimanStoreToStoreReport(date_from, date_to));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/PengirimanStoreToStore.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PengirimanStoreToStoreReport.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/penerimaanByStore")
	public ResponseEntity<byte []> generatePdfPenerimaanByStore(@Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, @Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(penerimaanByStoreReportService.PenerimaanByStoreReport(date_from, date_to));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/PenerimaanByStore.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PenerimaanByStoreReport.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/penerimaanBySupplier")
	public ResponseEntity<byte []> generatePdfPenerimaanBySupplier(@Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, @Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(penerimaanBySupplierReportService.PenerimaanBySupplierReport(date_from, date_to));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/PenerimaanBySupplier.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=PenerimaanBySupplierReport.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/salesByOffice")
	public ResponseEntity<byte []> generatePdfSalesOffice(@Param("id_office") String id_office, @Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, @Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(salesByOfficeService.SalesByOffice(id_office, date_from, date_to));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/SalesByOffice.jrxml"));

		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=SalesByOffice.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/salesByStore")
	public ResponseEntity<byte []> generatePdfSalesStore(@Param("id_store") String id_store, @Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, @Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(salesByStoreService.SalesByStore(id_store, date_from, date_to));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/SalesByStore.jrxml"));

		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=SalesByStore.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/bestArticleByStore")
	public ResponseEntity<byte []> generatePdfBestArticleStore(@Param("id_store") String id_store, @Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, @Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(purchaseStoreByArticleService.BestArticle(id_store, date_from, date_to));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/BestArticleByStore.jrxml"));
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=BestArticleByStore.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/bestArticleByOffice")
	public ResponseEntity<byte []> generatePdfBestArticleOffice(@Param("id_office") String id_office, @Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, @Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(salesByOfficeService.BestArticle(id_office, date_from, date_to));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/BestArticleByOffice.jrxml"));

		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=BestArticleByOffice.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/invoice")
	public ResponseEntity<byte []> generatePdfInvoiceOffice(@Param("id_office") int id_office, @Param("id_transaksi") String id_transaksi) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(invoiceOfficeService.Invoice(id_office, id_transaksi));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/Invoice.jrxml"));

		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=Invoice_"+ id_transaksi +".pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/proformaInvoice")
	public ResponseEntity<byte []> generatePdfProformaInvoiceOffice(@Param("id_office") int id_office, @Param("pi_no") String pi_no) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(proformaInvoiceService.ProformaInvoice(id_office, pi_no));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/ProformaInvoice.jrxml"));

		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=ProformaInvoice_"+ pi_no +".pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/laporanPembelian")
	public ResponseEntity<byte []> generatePdfLaporanPembelian(@Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, @Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to) throws Exception, JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pembelianService.laporanPembelian(date_from, date_to));
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("classes/templates/LaporanPembelian.jrxml"));

		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "_blank;filename=LaporanPembelian.pdf");
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}
