package com.graacc.mirifici.artefato.controller;

import java.net.URI;
import java.util.List;
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

import com.graacc.mirifici.artefato.entity.ProductEntity;
import com.graacc.mirifici.artefato.repository.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProductEntity> cadastrar(@RequestBody ProductEntity product, UriComponentsBuilder uriBuilder) {
		
		productRepository.save(product);

		URI uri = uriBuilder.path("/product/{id}").buildAndExpand(product.getIdProduct()).toUri();
		return ResponseEntity.created(uri).body(product);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> detalhar(@PathVariable Long id) {
		Optional<ProductEntity> product = productRepository.findById(id);
		if (product.isPresent()) {
			return ResponseEntity.ok(product);
		}

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id,
			@RequestBody ProductEntity product) {
		
		return productRepository.findById(id)
		           .map(record -> {
		               record.setDateRegister(product.getDateRegister());
		               record.setDescription(product.getDescription());
		               record.setName(product.getName());
		               record.setValue(product.getValue());
		               ProductEntity updated = productRepository.save(record);
		               return ResponseEntity.ok().body(updated);
		           }).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<ProductEntity> product = productRepository.findById(id);
		if (product.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public ResponseEntity<List<ProductEntity>> listaTodos(){
		List<ProductEntity> lista =  productRepository.findAll();
	   
	   return ResponseEntity.ok().body(lista);
	}
	
	@DeleteMapping("/remover/products")
	@Transactional
	public  ResponseEntity<?> removerProdutos(@PathVariable Long id, UriComponentsBuilder uriBuilder) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
	}
