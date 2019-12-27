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

import com.graacc.mirifici.artefato.entity.AddressEntity;
import com.graacc.mirifici.artefato.repository.AddressRepository;

@Controller
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressRepository addressRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<AddressEntity> cadastrar(@RequestBody AddressEntity address, UriComponentsBuilder uriBuilder) {
		
		addressRepository.save(address);

		URI uri = uriBuilder.path("/address/{id}").buildAndExpand(address.getIdAddress()).toUri();
		return ResponseEntity.created(uri).body(address);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> detalhar(@PathVariable Long id) {
		Optional<AddressEntity> address = addressRepository.findById(id);
		if (address.isPresent()) {
			return ResponseEntity.ok(address);
		}

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id,
			@RequestBody AddressEntity address) {
		
		return addressRepository.findById(id)
		           .map(record -> {
		               record.setRua(address.getRua());
		               record.setNumero(address.getNumero());
		               record.setComplemento(address.getComplemento());
		               record.setBairro(address.getBairro());
		               record.setCidade(address.getCidade());
		               record.setCep(address.getCep());
		               record.setUf(address.getUf());
		               record.setIdCustomer(address.getIdCustomer());
		               AddressEntity updated = addressRepository.save(record);
		               return ResponseEntity.ok().body(updated);
		           }).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<AddressEntity> address = addressRepository.findById(id);
		if (address.isPresent()) {
			addressRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
