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

import com.graacc.mirifici.artefato.entity.CustomerEntity;
import com.graacc.mirifici.artefato.repository.CustomerRepository;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<CustomerEntity> cadastrar(@RequestBody CustomerEntity customer, UriComponentsBuilder uriBuilder) {
		
		customerRepository.save(customer);

		URI uri = uriBuilder.path("/customer/{id}").buildAndExpand(customer.getIdCustomer()).toUri();
		return ResponseEntity.created(uri).body(customer);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> detalhar(@PathVariable Long id) {
		Optional<CustomerEntity> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			return ResponseEntity.ok(customer);
		}

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id,
			@RequestBody CustomerEntity customer) {
//		Optional<customer> optional = customerRepository.findById(id);
//		if (optional.isPresent()) {
//			Topico topico = topicoForm.atualizar(id, topicoRepository);
//			return ResponseEntity.ok(new TopicoDto(topico));
//		}
//
//		return ResponseEntity.notFound().build();
		
		return customerRepository.findById(id)
		           .map(record -> {
		               record.setBornDate(customer.getBornDate());
		               record.setCpf(customer.getCpf());
		               record.setName(customer.getName());
		               record.setLastName(customer.getLastName());
		               record.setNick(customer.getNick());
		               CustomerEntity updated = customerRepository.save(record);
		               return ResponseEntity.ok().body(updated);
		           }).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<CustomerEntity> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			customerRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
