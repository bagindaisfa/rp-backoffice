package com.gbsystem.rpbackoffice.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.gbsystem.rpbackoffice.entities.Pembelian;
import com.gbsystem.rpbackoffice.repository.PembelianRepository;
import com.gbsystem.rpbackoffice.services.PembelianService;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class PembelianController {
	
	@Autowired
	private PembelianService pembelianService;
	
	@Autowired
	private PembelianRepository eRepo;

    @GetMapping("/pembelian")
	public ResponseEntity<List<Pembelian>> getAll() {
        return new ResponseEntity<>(pembelianService.getAllPembelian(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/addPembelian", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String saveProduct(@RequestParam("image") MultipartFile image,@RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori,@RequestParam("tipe") String tipe,
    		@RequestParam("nama_barang") String nama_barang,@RequestParam("kuantitas") double kuantitas,@RequestParam("ukuran") String ukuran,
    		@RequestParam("hpp") double hpp,@RequestParam("harga_jual") double harga_jual) throws Exception {
    	
    	if (artikel != "") {
    		pembelianService.savePembelian(image, artikel, kategori, tipe, nama_barang, kuantitas, ukuran, hpp, harga_jual);
    	}
    	return "Insert Data Successs!";
		
    }
    
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            Pembelian pembelian = new Pembelian();
        	XSSFRow row = worksheet.getRow(i);
        	pembelian.setImage(null);
    		pembelian.setArtikel(row.getCell(0).getStringCellValue());
    		pembelian.setKategori(row.getCell(4).getStringCellValue());
			pembelian.setTipe(row.getCell(7).getStringCellValue());
			pembelian.setNama_barang(row.getCell(6).getStringCellValue());
			pembelian.setKuantitas(row.getCell(5).getNumericCellValue());
			pembelian.setUkuran(row.getCell(8).getStringCellValue());
			pembelian.setHpp(row.getCell(1).getNumericCellValue());
    		pembelian.setHarga_jual(row.getCell(2).getNumericCellValue());
    		pembelian.setTotal_hpp((row.getCell(5).getNumericCellValue()) * (row.getCell(1).getNumericCellValue()));
    		pembelian.setRowstatus(1);
    		pembelian.setTanggal_transaksi(new Date());
    		eRepo.save(pembelian);
        }
        
    }
    
    @GetMapping("/deletePembelian/{id}")
    public String deletePembelian(@PathVariable("id") Long id)
    {
    	
    	pembelianService.deletePembelianById(id);
    	return "redirect:/pembelian";
    }
    
    @PostMapping("/changeTanggal_transaksi")
    public String changeTanggal_transaksi(@RequestParam("id") Long id ,@RequestParam("newTanggal_transaksi") Date tanggal_transaksi)
    {
    	pembelianService.changePembelianTanggal_transaksi(id, tanggal_transaksi);
    	return "redirect:/pembelian"; 
    }
    @PostMapping("/changeArtikel")
    public String changeArtikel(@RequestParam("id") Long id ,@RequestParam("newArtikel") String artikel)
    {
    	pembelianService.changePembelianArtikel(id, artikel);
    	return "redirect:/pembelian"; 
    }
    @PostMapping("/changeKategori")
    public String changePembelianKategori(@RequestParam("id") Long id , @RequestParam("newKategori") String kategori)
    {
    	pembelianService.changePembelianKategori(id, kategori);
    	return "redirect:/pembelian"; 
    }
    @PostMapping("/changeTipe")
    public String changePembelianTipe(@RequestParam("id") Long id ,@RequestParam("newTipe") String tipe)
    {
    	pembelianService.changePembelianTipe(id, tipe);
    	return "redirect:/pembelian"; 
    }
    @PostMapping("/changeNamaBarang")
    public String changePembelianNamaBarang(@RequestParam("id") Long id ,@RequestParam("newNamaBarang") String nama_barang)
    {
    	pembelianService.changePembelianNamaBarang(id, nama_barang);
    	return "redirect:/pembelian"; 
	}
    @PostMapping("/changeKuantitas")
    public String changePembelianKuantitas(@RequestParam("id") Long id ,@RequestParam("newKuantitas") int kuantitas)
    {
    	pembelianService.changePembelianKuantitas(id, kuantitas);
    	return "redirect:/pembelian"; 
	}
    @PostMapping("/changeUkuran")
    public String changePembelianUkuran(@RequestParam("id") Long id ,@RequestParam("newUkuran") String ukuran)
    {
    	pembelianService.changePembelianUkuran(id, ukuran);
    	return "redirect:/pembelian"; 
	}
    @PostMapping("/changeHpp")
    public String changePembelianHpp(@RequestParam("id") Long id ,@RequestParam("newHpp") double hpp)
    {
    	pembelianService.changePembelianHpp(id, hpp);
    	return "redirect:/pembelian"; 
	}
    @PostMapping("/changeHargaJual")
    public String changePembelianHargaJual(@RequestParam("id") Long id ,@RequestParam("newHargaJual") double harga_jual)
    {
    	pembelianService.changePembelianHargaJual(id, harga_jual);
    	return "redirect:/pembelian"; 
	}
    
    @PostMapping("/changeTotal_hpp")
    public String changePembelianTotal_hpp(@RequestParam("id") Long id)
    {
    	pembelianService.changePembelianTotal_hpp(id);
    	return "redirect:/pembelian"; 
	}
}
