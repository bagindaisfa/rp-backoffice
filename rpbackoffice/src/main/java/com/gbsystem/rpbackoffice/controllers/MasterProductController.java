package com.gbsystem.rpbackoffice.controllers;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.gbsystem.rpbackoffice.entities.MasterProduct;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;
import com.gbsystem.rpbackoffice.services.MasterProductService;

@RestController
@RequestMapping("/master/product")
@CrossOrigin
public class MasterProductController {
	@Autowired
	private MasterProductService masterProductService;
	
	@Autowired
	private MasterProductRepository eRepo;
	@Autowired
	private StockOfficeRepository eStockRepo;
	
    @GetMapping("/all")
	public ResponseEntity<List<MasterProduct>> getAll() {
        return new ResponseEntity<>(masterProductService.getAllMasterProduct(), HttpStatus.OK);
    }
    
    @GetMapping("/dropdown")
	public ResponseEntity<List<MasterProduct>> getAllDropdown(@RequestParam List<String> artikel_product) {
    	
        return new ResponseEntity<>(masterProductService.getMasterProduct(artikel_product), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<MasterProduct>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(masterProductService.search(keyword), HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody MasterProduct saveMaster_product(
    		@RequestParam("image") MultipartFile image,
    		@RequestParam("artikel_product") String artikel_product,
    		@RequestParam("nama_product") String nama_product,
    		@RequestParam("type") String type,
    		@RequestParam("type_name") String type_name,
    		@RequestParam("kategori") String kategori,
    		@RequestParam("nama_kategori") String nama_kategori,
    		@RequestParam("artikel_frame") String artikel_frame,
    		@RequestParam("artikel_lens") String artikel_lens,
    		@RequestParam("ukuran") String ukuran,
    		@RequestParam("kuantitas") double kuantitas,
    		@RequestParam("hpp") double hpp,
    		@RequestParam("harga_jual") double harga_jual,
    		@RequestParam("remarks") String remarks
    		) 
	{
    	MasterProduct masterProductResponse = masterProductService.saveMasterProduct(image,artikel_product, nama_product,type, type_name, kategori,nama_kategori, artikel_frame, artikel_lens, ukuran,kuantitas, hpp, harga_jual, remarks);
		return masterProductResponse;
	}
    
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String update(
    		@RequestParam("id") Long id,
    		@RequestParam("image") MultipartFile image,
    		@RequestParam("artikel_product") String artikel_product,
    		@RequestParam("nama_product") String nama_product,
    		@RequestParam("type") String type,
    		@RequestParam("type_name") String type_name,
    		@RequestParam("kategori") String kategori,
    		@RequestParam("nama_kategori") String nama_kategori,
    		@RequestParam("artikel_frame") String artikel_frame,
    		@RequestParam("artikel_lens") String artikel_lens,
    		@RequestParam("ukuran") String ukuran,
    		@RequestParam("kuantitas") double kuantitas,
    		@RequestParam("hpp") double hpp,
    		@RequestParam("harga_jual") double harga_jual,
    		@RequestParam("remarks") String remarks
    		) throws Exception {
    	
    	if (artikel_product != "") {
    		masterProductService.update(id, image, artikel_product,nama_product,type,type_name,kategori,nama_kategori,artikel_frame,artikel_lens,ukuran,kuantitas,hpp,harga_jual,remarks);
    	}
    	return "Update Data Successs!";
		
    }
    
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        
        for(int i=1; i<worksheet.getPhysicalNumberOfRows(); i++) {
            MasterProduct masterProduct = new MasterProduct();
            StockOffice q = new StockOffice();
        	XSSFRow row = worksheet.getRow(i);
        	masterProduct.setImage(null);
        	masterProduct.setArtikel_product(row.getCell(0).getStringCellValue());
        	masterProduct.setNama_product(row.getCell(1).getStringCellValue());
        	masterProduct.setType(row.getCell(2).getStringCellValue());
        	masterProduct.setType_name(row.getCell(3).getStringCellValue());
        	masterProduct.setKategori(row.getCell(4).getStringCellValue());
        	masterProduct.setNama_kategori(row.getCell(5).getStringCellValue());
        	masterProduct.setArtikel_frame(row.getCell(6).getStringCellValue());
        	masterProduct.setArtikel_lens(row.getCell(7).getStringCellValue());
        	masterProduct.setUkuran(row.getCell(8).getStringCellValue());
        	masterProduct.setKuantitas(row.getCell(9).getNumericCellValue());
        	masterProduct.setHpp(row.getCell(10).getNumericCellValue());
        	masterProduct.setHarga_jual(row.getCell(11).getNumericCellValue());
        	masterProduct.setRemarks(row.getCell(12).getStringCellValue());
        	masterProduct.setRowstatus(1);
        	
        	q.setId_office(1);
    		q.setLokasi_office("Kantor Pusat");
    		q.setArtikel(row.getCell(0).getStringCellValue());
    		q.setKategori(row.getCell(4).getStringCellValue());
    		q.setTipe(row.getCell(2).getStringCellValue());
    		q.setNama_barang(row.getCell(1).getStringCellValue());
    		q.setKuantitas(row.getCell(9).getNumericCellValue());
    		q.setUkuran(row.getCell(8).getStringCellValue());
    		
    		q.setHpp(row.getCell(10).getNumericCellValue());
    		q.setHarga_jual(row.getCell(11).getNumericCellValue());
    		q.setRowstatus(1);
    		eStockRepo.save(q);
    		eRepo.save(masterProduct);
        }
        
    }
    
    @GetMapping("/delete")
    public String deleteMasterProduct(@RequestParam("id") Long id)
    {
    	
    	masterProductService.deleteMasterProductById(id);
    	return "redirect:/kategori";
    }
}
