package com.graacc.mirifici.artefato.controller;

import java.net.URI;
import java.util.Calendar;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.graacc.mirifici.artefato.entity.AuctionDetailEntity;
import com.graacc.mirifici.artefato.entity.AuctionEntity;
import com.graacc.mirifici.artefato.entity.ProductEntity;
import com.graacc.mirifici.artefato.entity.SalesEntity;
import com.graacc.mirifici.artefato.repository.AuctionRepository;
import com.graacc.mirifici.artefato.repository.ProductRepository;
import com.graacc.mirifici.artefato.repository.SalesRepository;

@Controller
@RequestMapping("/sales")
public class SalesController {

	@Autowired
	private SalesRepository salesRepository;
	
	@Autowired
	private AuctionRepository auctionRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductController productController;
	
	@Autowired
	private AuctionController auctionController;
	
	@PostMapping
	@Transactional
	public ResponseEntity<SalesEntity> cadastrar(@RequestBody SalesEntity sales, UriComponentsBuilder uriBuilder) {
		
		salesRepository.save(sales);

		URI uri = uriBuilder.path("/sales/{id}").buildAndExpand(sales.getIdSales()).toUri();
		return ResponseEntity.created(uri).body(sales);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> detalhar(@PathVariable Long id) {
		Optional<SalesEntity> sales = salesRepository.findById(id);
		if (sales.isPresent()) {
			return ResponseEntity.ok(sales);
		}

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id,
			@RequestBody SalesEntity sales) {
		
		return salesRepository.findById(id)
		           .map(record -> {
		               record.setStatus(sales.getStatus());
		               record.setValue(sales.getValue());
		               record.setPayoutDate(sales.getPayoutDate());
		               record.setIdProduct(sales.getIdProduct());
		               record.setIdCustomer(sales.getIdCustomer());
		               SalesEntity updated = salesRepository.save(record);
		               return ResponseEntity.ok().body(updated);
		           }).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/atualizar")
	@Transactional
	public ResponseEntity<?> atualizarVenda(@RequestBody AuctionDetailEntity auctionDetailEntity, UriComponentsBuilder uriBuilder) {
		SalesEntity salesEntity = new SalesEntity();
		
		Calendar payoutDate = Calendar.getInstance();
		
		payoutDate.add(Calendar.DATE, 7);
		
		salesEntity.setIdCustomer(auctionDetailEntity.getIdCustomer());
		salesEntity.setPayoutDate(payoutDate.getTime());
		salesEntity.setStatus("Vendido");
		salesEntity.setValue(auctionDetailEntity.getThrowField());
		salesEntity.setIdProduct(auctionRepository.findById(auctionDetailEntity.getIdAuction()).get().getIdProduto().longValue());
		salesRepository.save(salesEntity);
		
		URI uri = uriBuilder.path("/sales/{id}").buildAndExpand(salesEntity.getIdSales()).toUri();
		return ResponseEntity.created(uri).body(salesEntity);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<SalesEntity> sales = salesRepository.findById(id);
		if (sales.isPresent()) {
			salesRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/teste/{id}")
	@Transactional
	public String removerProdutos(@PathVariable Long id, @RequestBody ProductEntity product, UriComponentsBuilder uriBuilder) {
		Optional<SalesEntity> sales = salesRepository.findById(id);
		if (sales.isPresent() && "Vendido".equals(sales.get().getStatus())) {
			Optional<ProductEntity> productsUpd = productRepository.findById(id);
			if(productsUpd.isPresent()) {
				productController.removerProdutos(productsUpd.get().getIdProduct(), uriBuilder);
				return "redirect:/remover/products";	
			}
		}

		return "redirect:/";
	}
	
	@DeleteMapping("/testeleilao/{id}")
	@Transactional
	public String removerLeilao(@PathVariable Long id, UriComponentsBuilder uriBuilder) {
		Optional<SalesEntity> sales = salesRepository.findById(id);
		if (sales.isPresent() && "Vendido".equals(sales.get().getStatus())) {
			Optional<AuctionEntity> auction = auctionRepository.findById(id);
			auctionController.removerLeilao(auction.get().getIdAuction());
			return "redirect:/remover/leilao";
		}

		return "redirect:/";
	}
}
