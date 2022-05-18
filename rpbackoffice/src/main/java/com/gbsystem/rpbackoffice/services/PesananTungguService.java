package com.gbsystem.rpbackoffice.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.BarangPesananTungguItem;
import com.gbsystem.rpbackoffice.entities.MasterProduct;
import com.gbsystem.rpbackoffice.entities.PesananTunggu;
import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
import com.gbsystem.rpbackoffice.repository.PesananTungguRepository;

@Service
public class PesananTungguService {
	@Autowired
	private PesananTungguRepository eRepo;

	@Autowired
	private MasterProductRepository eMasterProductRepository;
	
	public List<PesananTunggu> savePesananTunggu( PesananTunggu pesananTunggu) {
		PesananTunggu item = new PesananTunggu();
		String id_transaksi = "ANTRIAN-" + (eRepo.count() + 1);
		Date tanggal_pesanan = new Date();
		List<BarangPesananTungguItem> details = new ArrayList<>();
		Double total = 0.0;
		
		item.setTanggal_pesanan(tanggal_pesanan);
		item.setNo_pesanan(id_transaksi);
		item.setId_store(pesananTunggu.getId_store());
		item.setLokasi_store(pesananTunggu.getLokasi_store());
		item.setNama_pelanggan(pesananTunggu.getNama_pelanggan());
		item.setNo_hp_pelanggan(pesananTunggu.getNo_hp_pelanggan());
		item.setId_karyawan(pesananTunggu.getId_karyawan());
		item.setNama_karyawan(pesananTunggu.getNama_karyawan());
		item.setDiskon(pesananTunggu.getDiskon());
		item.setMetode_bayar(pesananTunggu.getMetode_bayar());
		item.setBank_name(pesananTunggu.getBank_name());
		item.setNo_rek(pesananTunggu.getNo_rek());
		item.setEkspedisi(pesananTunggu.getEkspedisi());
		item.setOngkir(pesananTunggu.getOngkir());
		
		item.setKembalian(pesananTunggu.getKembalian());
		item.setKet_pesanan(pesananTunggu.getKet_pesanan());
		
		
		for(int i = 0; i < pesananTunggu.getBarang_pesanan().size(); i++) {
			BarangPesananTungguItem d = new BarangPesananTungguItem();
			MasterProduct product = eMasterProductRepository.findBySkuCode(pesananTunggu.getBarang_pesanan().get(i).getSku_code());
			d.setNo_pesanan(id_transaksi);
			d.setImage(product.getImage());
			d.setId_store(pesananTunggu.getBarang_pesanan().get(i).getId_store());
			d.setLokasi_store(pesananTunggu.getBarang_pesanan().get(i).getLokasi_store());
			d.setArtikel(pesananTunggu.getBarang_pesanan().get(i).getArtikel());
			d.setSku_code(pesananTunggu.getBarang_pesanan().get(i).getSku_code());
			d.setType(product.getType());
			d.setType_name(product.getType_name());
			d.setKategori(product.getKategori());
			d.setNama_kategori(product.getNama_kategori());
			d.setNama_barang(product.getNama_product());
			d.setHarga(pesananTunggu.getBarang_pesanan().get(i).getHarga());
			d.setKuantitas(pesananTunggu.getBarang_pesanan().get(i).getKuantitas());
			d.setTotal(pesananTunggu.getBarang_pesanan().get(i).getTotal());
			d.setRowstatus(1);
			d.setPesanan_tunggu(item);
			total += pesananTunggu.getBarang_pesanan().get(i).getTotal();
			details.add(d);
			
		}
		item.setTotal(total);
		item.setRowstatus(1);
		item.setBarang_pesanan(details);
		eRepo.save(item);
		
		return getAllPesanan();
	}
	
	public List<PesananTunggu> getAllPesanan(){

		return eRepo.findByRowstatus(1);
	}
	
	public PesananTunggu getPesanan(String no_pesanan){

		return eRepo.findByIdTransaksi(no_pesanan);
	}
	
	public void delete(Long id) {
		eRepo.deleteById(id);
	}
}
