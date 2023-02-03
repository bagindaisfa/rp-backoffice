package com.gbsystem.rpbackoffice.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.PenyimpananStoreKeluar;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreMasuk;
import com.gbsystem.rpbackoffice.entities.StockPerStoreList;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreKeluarRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockPerStoreListRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
public class StockPerStoreService {
	
	@Autowired
	private PenyimpananStoreMasukRepository eMasukRepo;
	
	@Autowired
	private PenyimpananStoreKeluarRepository eKeluarRepo;
	
	@Autowired
	private StockStoreRepository eStockStoreRepo;
	
	@Autowired
	private StockPerStoreListRepository eListRepo;
	
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Lokasi Store", "Artikel", "Nama Barang", "Kuantitas" };
	static String SHEET = "Stock Store";
	  
	public List<PenyimpananStoreMasuk> storeMasuk (int id_store, Date date_from, Date date_to) {
		List<PenyimpananStoreMasuk> storeMasuk = eMasukRepo.allPenyimpananStoreMasuk(id_store, date_from, date_to);
		return storeMasuk;
	}
	
	public List<PenyimpananStoreKeluar> storeKeluar (int id_store, Date date_from, Date date_to) {
		List<PenyimpananStoreKeluar> storeMasuk = eKeluarRepo.allPenyimpananStoreKeluar(id_store, date_from, date_to);
		return storeMasuk;
	}
	
	public List<StockStore> stockAllItemPerStore(int id_store) {
		
		return eStockStoreRepo.stockAllItemPerStore(id_store);
	}
	
	public List<StockPerStoreList> allStock() {
		
		return eListRepo.allStock();
	}
	
	public List<StockPerStoreList> searchStock(String keyword) {
		List<StockPerStoreList> response = new ArrayList<>();
		
		if (keyword.length() > 0) {
			response = eListRepo.search(keyword);
		} else {
			response = eListRepo.allStock();
		}
		
		return response;
	}
	
	public static ByteArrayInputStream generateExcel(List<StockStore> stocks) {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
		      Sheet sheet = workbook.createSheet(SHEET);

		      // Header
		      Row headerRow = sheet.createRow(0);

		      for (int col = 0; col < HEADERs.length; col++) {
		        Cell cell = headerRow.createCell(col);
		        cell.setCellValue(HEADERs[col]);
		      }

		      int rowIdx = 1;
		      for (StockStore stock : stocks) {
		        Row row = sheet.createRow(rowIdx++);

		        row.createCell(0).setCellValue(stock.getLokasi_store());
		        row.createCell(1).setCellValue(stock.getArtikel());
		        row.createCell(2).setCellValue(stock.getNama_barang());
		        row.createCell(3).setCellValue(stock.getKuantitas());
		      }

		      workbook.write(out);
		      return new ByteArrayInputStream(out.toByteArray());
		    } catch (IOException e) {
		      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		    }
	}
	public ByteArrayInputStream loadExcel() {
	    List<StockStore> stocks = eStockStoreRepo.findByRowstatus(1);

	    ByteArrayInputStream in = generateExcel(stocks);
	    return in;
	  }
}
