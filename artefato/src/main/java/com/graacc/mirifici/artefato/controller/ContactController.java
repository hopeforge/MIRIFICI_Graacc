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

import com.graacc.mirifici.artefato.entity.ContactEntity;
import com.graacc.mirifici.artefato.repository.ContactRepository;

@Controller
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private ContactRepository contactRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<ContactEntity> cadastrar(@RequestBody ContactEntity contact, UriComponentsBuilder uriBuilder) {
		
		contactRepository.save(contact);

		URI uri = uriBuilder.path("/contact/{id}").buildAndExpand(contact.getIdContato()).toUri();
		return ResponseEntity.created(uri).body(contact);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> detalhar(@PathVariable Long id) {
		Optional<ContactEntity> contact = contactRepository.findById(id);
		if (contact.isPresent()) {
			return ResponseEntity.ok(contact);
		}

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id,
			@RequestBody ContactEntity contact) {
		
		return contactRepository.findById(id)
		           .map(record -> {
		               record.setIdCustomer(contact.getIdCustomerr());
		               record.setDdd(contact.getDdd());
		               record.setNumber(contact.getNumber());
		               record.setMobile(contact.isMobile());
		               ContactEntity updated = contactRepository.save(record);
		               return ResponseEntity.ok().body(updated);
		           }).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<ContactEntity> contact = contactRepository.findById(id);
		if (contact.isPresent()) {
			contactRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
