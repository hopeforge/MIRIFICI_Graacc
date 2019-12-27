package com.graacc.mirifici.artefato.controller;

import java.net.URI;
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
import com.graacc.mirifici.artefato.repository.AuctionDetailRepository;
import com.graacc.mirifici.artefato.repository.AuctionRepository;
import com.graacc.mirifici.artefato.repository.ProductRepository;

@Controller
@RequestMapping("/auction")
public class AuctionController {

	@Autowired
	private AuctionRepository auctionRepository;

	@Autowired
	private AuctionDetailRepository auctionDetailRepository;

	@Autowired
	private ProductRepository productRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<AuctionEntity> cadastrar(@RequestBody AuctionEntity auction,
			UriComponentsBuilder uriBuilder) {

		auctionRepository.save(auction);

		URI uri = uriBuilder.path("/auction/{id}").buildAndExpand(auction.getIdAuction()).toUri();
		return ResponseEntity.created(uri).body(auction);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> detalhar(@PathVariable Long id) {
		Optional<ProductEntity> product = productRepository.findById(id);
		if (product.isPresent()) {
			Optional<AuctionEntity> auction = auctionRepository.findByIdProduto(id);
			if (auction.isPresent()) {
				return ResponseEntity.ok(auction);
			}
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody AuctionEntity auction) {

		return auctionRepository.findById(id).map(record -> {
			record.setIdProduto(auction.getIdProduto());
			record.setMinValueIncrease(auction.getMinValueIncrease());
			record.setInitAuctionDate(auction.getInitAuctionDate());
			record.setFinalAuctionDate(auction.getFinalAuctionDate());
			AuctionEntity updated = auctionRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/valor/{id}")
	@Transactional
	public ResponseEntity<?> atualizarValorLance(@PathVariable Long id,
			@RequestBody AuctionDetailEntity auctionDetail) {
		Double valorLance = 0.0;
		auctionRepository.findById(id);
		auctionDetailRepository.findAll();

		return auctionDetailRepository.findById(id).map(record -> {
			record.setThrowField(valorLance);
			AuctionDetailEntity updated = auctionDetailRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<AuctionEntity> auction = auctionRepository.findById(id);
		if (auction.isPresent()) {
			auctionRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/remover/leilao")
	@Transactional
	public ResponseEntity<?> removerLeilao(@PathVariable Long id) {
		auctionRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
