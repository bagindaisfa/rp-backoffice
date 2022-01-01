package com.gbsystem.rpbackoffice.controllers;

import java.util.Date;
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

import com.gbsystem.rpbackoffice.entities.PenerimaanSupplier;
import com.gbsystem.rpbackoffice.repository.PenerimaanSupplierRepository;
import com.gbsystem.rpbackoffice.services.PenerimaanSupplierService;

@RestController
@RequestMapping("/penerimaanSupplier")
@CrossOrigin
public class PenerimaanSupplierController {
	
	@Autowired
	private PenerimaanSupplierService penerimaanSupplierService;
	
	@Autowired
	private PenerimaanSupplierRepository eRepo;

    @GetMapping("/all")
	public ResponseEntity<List<PenerimaanSupplier>> getAll() {
        return new ResponseEntity<>(penerimaanSupplierService.getAllPenerimaanSupplier(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PenerimaanSupplier>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(penerimaanSupplierService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String saveProduct(@RequestParam("id_office") int id_office,
    		@RequestParam("lokasi_office") String lokasi_office, @RequestParam("id_supplier") String id_supplier,
    		@RequestParam("nama_supplier") String nama_supplier, @RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori, @RequestParam("tipe") String tipe,
    		@RequestParam("nama_barang") String nama_barang, @RequestParam("kuantitas") double kuantitas, 
    		@RequestParam("ukuran") String ukuran, @RequestParam("foto_barang") MultipartFile foto_barang, 
    		@RequestParam("hpp") double hpp, @RequestParam("harga_jual") double harga_jual) throws Exception {
    	
    	if (artikel != "") {
    		penerimaanSupplierService.savePenerimaanSupplier(id_office,lokasi_office, id_supplier, nama_supplier, artikel, kategori, tipe, nama_barang, 
    				kuantitas, ukuran, foto_barang, hpp, harga_jual);
    	}
    	return "Insert Data Successs!";
		
    }
    
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String update(@RequestParam("id") Long id, @RequestParam("tanggal_penerimaan") Date tanggal_penerimaan,@RequestParam("penerimaan_code") String penerimaan_code,
    		@RequestParam("id_office") int id_office,@RequestParam("lokasi_office") String lokasi_office, @RequestParam("id_supplier") String id_supplier,
    		@RequestParam("nama_supplier") String nama_supplier, @RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori,@RequestParam("tipe") String tipe,
    		@RequestParam("nama_barang") String nama_barang,@RequestParam("kuantitas") double kuantitas,
    		@RequestParam("ukuran") String ukuran, @RequestParam("foto_barang") MultipartFile foto_barang,
    		@RequestParam("hpp") double hpp, @RequestParam("harga_jual") double harga_jual) throws Exception {
    	
    	if (artikel != "") {
    		penerimaanSupplierService.update(id, penerimaan_code, tanggal_penerimaan, id_office, lokasi_office, id_supplier, nama_supplier, artikel, kategori, tipe, nama_barang, 
    				kuantitas, ukuran, foto_barang, hpp, harga_jual);
    	}
    	return "Update Data Successs!";
		
    }
    
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
        	PenerimaanSupplier p = new PenerimaanSupplier();
        	XSSFRow row = worksheet.getRow(i);
        	p.setId_office((int)row.getCell(1).getNumericCellValue());
        	p.setLokasi_office(row.getCell(2).getStringCellValue());
        	p.setId_supplier(row.getCell(3).getStringCellValue());
        	p.setNama_supplier(row.getCell(4).getStringCellValue());
    		p.setArtikel(row.getCell(5).getStringCellValue());
    		p.setKategori(row.getCell(6).getStringCellValue());
			p.setTipe(row.getCell(7).getStringCellValue());
			p.setNama_barang(row.getCell(8).getStringCellValue());
			p.setKuantitas(row.getCell(9).getNumericCellValue());
			p.setUkuran(row.getCell(10).getStringCellValue());
			p.setHpp(row.getCell(11).getNumericCellValue());
			p.setHarga_jual(row.getCell(12).getNumericCellValue());
    		p.setRowstatus(1);
    		p.setTanggal_penerimaan(row.getCell(0).getDateCellValue());
    		eRepo.save(p);
        }
        
    }
    
    @GetMapping("/delete")
    public String deletePenerimaanSupplier(@RequestParam("id") Long id, @RequestParam("id_office") int id_office, @RequestParam("artikel") String artikel)
    {
    	penerimaanSupplierService.deletePenerimaanSupplierById(id,id_office,artikel);
    	return "redirect:/all";
    }

}
