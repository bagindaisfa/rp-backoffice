package com.gbsystem.rpbackoffice.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
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
import com.gbsystem.rpbackoffice.entities.PenyimpananMasuk;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
import com.gbsystem.rpbackoffice.repository.PenerimaanSupplierRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananMasukRepository;
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
	
	@Autowired
	private PenerimaanSupplierRepository ePenerimaanSuppRepo;
	
	@Autowired
	private PenyimpananMasukRepository ePenyimpananRepo;
	
    @GetMapping("/all")
	public ResponseEntity<List<MasterProduct>> getAll() {
        return new ResponseEntity<>(masterProductService.getAllMasterProduct(), HttpStatus.OK);
    }
    
    @GetMapping("/getById")
	public ResponseEntity<MasterProduct> getById(@Param("id") String id) {
        return new ResponseEntity<>(masterProductService.findById(id), HttpStatus.OK);
    }
    
    @GetMapping("/getBySkuCode")
	public ResponseEntity<MasterProduct> getBySkuCode(@Param("sku_code") String sku_code) {
        return new ResponseEntity<>(masterProductService.findBySkuCode(sku_code), HttpStatus.OK);
    }
    
    @GetMapping("/getByType")
	public ResponseEntity<List<MasterProduct>> getByType(@Param("type") int type) {
        return new ResponseEntity<>(masterProductService.findByType(type), HttpStatus.OK);
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
    		@RequestParam("sku_code") String sku_code,
    		@RequestParam("artikel_product") String artikel_product,
    		@RequestParam("nama_product") String nama_product,
    		@RequestParam("type") int type,
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
    	MasterProduct masterProductResponse = masterProductService.saveMasterProduct(image,sku_code,artikel_product, nama_product,type, type_name, kategori,nama_kategori, artikel_frame, artikel_lens, ukuran,kuantitas, hpp, harga_jual, remarks);
		return masterProductResponse;
	}
    
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String update(
    		@RequestParam("id") Long id,
    		@RequestParam("image") MultipartFile image,
    		@RequestParam("sku_code") String sku_code,
    		@RequestParam("artikel_product") String artikel_product,
    		@RequestParam("nama_product") String nama_product,
    		@RequestParam("type") int type,
    		@RequestParam("type_name") String type_name,
    		@RequestParam("kategori") String kategori,
    		@RequestParam("nama_kategori") String nama_kategori,
    		@RequestParam("artikel_frame") String artikel_frame,
    		@RequestParam("artikel_lens") String artikel_lens,
    		@RequestParam("ukuran") String ukuran,
    		@RequestParam("hpp") double hpp,
    		@RequestParam("harga_jual") double harga_jual,
    		@RequestParam("remarks") String remarks
    		) throws Exception {
    	
    	if (artikel_product != "") {
    		masterProductService.update(id, image,sku_code, artikel_product,nama_product,type,type_name,kategori,nama_kategori,artikel_frame,artikel_lens,ukuran,hpp,harga_jual,remarks);
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
        	DataFormatter formatter = new DataFormatter();
        	masterProduct.setImage(null);
        	masterProduct.setArtikel_product(formatter.formatCellValue(row.getCell(0)));
        	masterProduct.setNama_product(formatter.formatCellValue(row.getCell(1)));
        	masterProduct.setType(Integer.valueOf(formatter.formatCellValue(row.getCell(2))));
        	masterProduct.setType_name(formatter.formatCellValue(row.getCell(3)));
        	masterProduct.setKategori(formatter.formatCellValue(row.getCell(4)));
        	masterProduct.setNama_kategori(formatter.formatCellValue(row.getCell(5)));
        	masterProduct.setArtikel_frame(formatter.formatCellValue(row.getCell(6)));
        	masterProduct.setArtikel_lens(formatter.formatCellValue(row.getCell(7)));
        	masterProduct.setUkuran(formatter.formatCellValue(row.getCell(8)));
        	masterProduct.setKuantitas(Double.valueOf(formatter.formatCellValue(row.getCell(9))));
        	masterProduct.setHpp(Double.valueOf(formatter.formatCellValue(row.getCell(10))));
        	masterProduct.setHarga_jual(Double.valueOf(formatter.formatCellValue(row.getCell(11))));
        	masterProduct.setRemarks(formatter.formatCellValue(row.getCell(12)));
        	masterProduct.setSku_code(formatter.formatCellValue(row.getCell(13)));
        	masterProduct.setRowstatus(1);
        	
        	q.setId_office(1);
    		q.setLokasi_office("Kantor Pusat");
    		q.setArtikel(formatter.formatCellValue(row.getCell(0)));
    		q.setKategori(formatter.formatCellValue(row.getCell(4)));
    		q.setNama_kategori(formatter.formatCellValue(row.getCell(5)));
    		q.setType(Integer.valueOf(formatter.formatCellValue(row.getCell(2))));
    		q.setType_name(formatter.formatCellValue(row.getCell(3)));
    		q.setNama_barang(formatter.formatCellValue(row.getCell(1)));
    		q.setKuantitas(Double.valueOf(formatter.formatCellValue(row.getCell(9))));
    		q.setUkuran(formatter.formatCellValue(row.getCell(8)));
    		q.setSku_code(formatter.formatCellValue(row.getCell(13)));
    		q.setHpp(Double.valueOf(formatter.formatCellValue(row.getCell(10))));
    		q.setHarga_jual(Double.valueOf(formatter.formatCellValue(row.getCell(11))));
    		q.setRowstatus(1);
    		eStockRepo.save(q);
    		eRepo.save(masterProduct);
    		
    		if (Double.valueOf(formatter.formatCellValue(row.getCell(9))) != null) {
    			if (Double.valueOf(formatter.formatCellValue(row.getCell(9))) > 0) {
    				PenyimpananMasuk f = new PenyimpananMasuk();
    				f.setId_office(1);
    				f.setLokasi_office("Kantor Pusat");
    				f.setPenerimaan_code("PS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (ePenerimaanSuppRepo.count()+1));
    				f.setTanggal_masuk(new Date());
    				f.setArtikel(formatter.formatCellValue(row.getCell(0)));
    	    		f.setKategori(formatter.formatCellValue(row.getCell(4)));
    	    		f.setNama_kategori(formatter.formatCellValue(row.getCell(5)));
    	    		f.setType(Integer.valueOf(formatter.formatCellValue(row.getCell(2))));
    	    		f.setType_name(formatter.formatCellValue(row.getCell(3)));
    	    		f.setNama_barang(formatter.formatCellValue(row.getCell(1)));
    	    		f.setKuantitas(Double.valueOf(formatter.formatCellValue(row.getCell(9))));
    	    		f.setUkuran(formatter.formatCellValue(row.getCell(8)));
    	    		f.setSku_code(formatter.formatCellValue(row.getCell(13)));
    	    		f.setHpp(Double.valueOf(formatter.formatCellValue(row.getCell(10))));
    	    		f.setHarga_jual(Double.valueOf(formatter.formatCellValue(row.getCell(11))));
    				f.setKeterangan("Barang Masuk Dari Master Product");
    				f.setRowstatus(1);
    				ePenyimpananRepo.save(f);
    			}
    		}
        }
        
    }
    
    @GetMapping("/delete")
    public String deleteMasterProduct(@RequestParam("id") Long id)
    {
    	
    	masterProductService.deleteMasterProductById(id);
    	return "Terhapus";
    }
}
