package com.gbsystem.rpbackoffice.controllers;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gbsystem.rpbackoffice.entities.PenjualanOffice;
import com.gbsystem.rpbackoffice.repository.PenjualanOfficeRepository;
import com.gbsystem.rpbackoffice.services.PenjualanOfficeService;

@RestController
@RequestMapping("/office")
@CrossOrigin
public class PenjualanOfficeController {

	@Autowired
	private PenjualanOfficeService penjualanOfficeService;
	
	@Autowired
	private PenjualanOfficeRepository eRepo;
	
	@GetMapping("/penjualan")
	public ResponseEntity<List<PenjualanOffice>> getAll(){
		return new ResponseEntity<>(penjualanOfficeService.getAllPenjualan(), HttpStatus.OK);
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<PenjualanOffice>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(penjualanOfficeService.search(keyword), HttpStatus.OK);
    }
	
	@PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String savePenjualan_office(@RequestParam("id_office") String id_office,
    		@RequestParam("lokasi_office") String lokasi_office, @RequestParam("artikel") String artikel,
    		@RequestParam("tipe") String tipe, @RequestParam("kategori") String kategori, 
    		@RequestParam("nama_barang") String nama_barang, @RequestParam("kuantitas") double kuantitas, 
    		@RequestParam("ukuran") String ukuran, @RequestParam("foto_barang") MultipartFile foto_barang, 
    		@RequestParam("metode_pembayaran") String metode_pembayaran, @RequestParam("harga_satuan_barang") double harga_satuan_barang, 
    		@RequestParam("ongkos_kirim") double ongkos_kirim, @RequestParam("pajak_biaya") double pajak_biaya, 
    		@RequestParam("total") double total) 
	{
		penjualanOfficeService.savePenjualanOffice(id_office, lokasi_office, artikel, tipe, kategori, 
				nama_barang, kuantitas, ukuran, foto_barang, metode_pembayaran, 
				harga_satuan_barang, ongkos_kirim, pajak_biaya, total);
		return "redirect:/penjualan";
	}
	
	@PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String update(@RequestParam("id") Long id, @RequestParam("id_office") String id_office,
    		@RequestParam("lokasi_office") String lokasi_office, @RequestParam("artikel") String artikel,
    		@RequestParam("tipe") String tipe, @RequestParam("kategori") String kategori, 
    		@RequestParam("nama_barang") String nama_barang, @RequestParam("kuantitas") double kuantitas, 
    		@RequestParam("ukuran") String ukuran, @RequestParam("foto_barang") MultipartFile foto_barang, 
    		@RequestParam("metode_pembayaran") String metode_pembayaran, @RequestParam("harga_satuan_barang") double harga_satuan_barang, 
    		@RequestParam("ongkos_kirim") double ongkos_kirim, @RequestParam("pajak_biaya") double pajak_biaya, 
    		@RequestParam("total") double total) throws Exception {
    	
    	penjualanOfficeService.update(id, id_office, lokasi_office, artikel, tipe, kategori, 
    				nama_barang, kuantitas, ukuran, foto_barang, metode_pembayaran, 
    				harga_satuan_barang, ongkos_kirim, pajak_biaya, total);
    	return "Update Data Successs!";
		
    }
	
	@PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
        	PenjualanOffice p = new PenjualanOffice();
        	XSSFRow row = worksheet.getRow(i);
        	
        	String id_transaksi = "INV-" + new SimpleDateFormat("yyMM").format(row.getCell(0).getDateCellValue()) + "-O" + (eRepo.count() + i);
        	
        	p.setId_transaksi(id_transaksi);
        	p.setId_office(row.getCell(1).getStringCellValue());
        	p.setLokasi_office(row.getCell(2).getStringCellValue());
        	p.setArtikel(row.getCell(3).getStringCellValue());
        	p.setTipe(row.getCell(4).getStringCellValue());
        	p.setKategori(row.getCell(5).getStringCellValue());
        	p.setNama_barang(row.getCell(6).getStringCellValue());
        	p.setKuantitas(row.getCell(7).getNumericCellValue());
        	p.setUkuran(row.getCell(8).getStringCellValue());
        	p.setMetode_pembayaran(row.getCell(9).getStringCellValue());
        	p.setHarga_satuan_barang(row.getCell(10).getNumericCellValue());
        	p.setOngkos_kirim(row.getCell(11).getNumericCellValue());
        	p.setPajak_biaya(row.getCell(12).getNumericCellValue());
        	p.setTotal(row.getCell(13).getNumericCellValue());
    		p.setRowstatus(1);
    		p.setTanggal_transaksi(row.getCell(0).getDateCellValue());
    		eRepo.save(p);
        }
    }
	
	@GetMapping("/delete")
    public String deletePenjualan(@RequestParam("id") Long id)
    {
    	
    	penjualanOfficeService.deletePenjualanById(id);
    	return "redirect:/penjualan";
    }
	    
}
