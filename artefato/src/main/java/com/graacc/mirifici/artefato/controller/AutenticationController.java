package com.graacc.mirifici.artefato.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.graacc.mirifici.artefato.Utils.PasswordUtils;
import com.graacc.mirifici.artefato.entity.AutenticationEntity;
import com.graacc.mirifici.artefato.entity.ProductEntity;
import com.graacc.mirifici.artefato.repository.AutenticationRepository;

@Controller
@RequestMapping("/autentication")
public class AutenticationController {
	
	@Autowired
	private AutenticationRepository autenticationRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<AutenticationEntity> cadastrar(@RequestBody AutenticationEntity autentication, UriComponentsBuilder uriBuilder) {
		autentication.setSalt(PasswordUtils.getSalt(50));
		autentication.setPassword(PasswordUtils.generateSecurePassword(autentication.getPassword(), autentication.getSalt()));
		autenticationRepository.save(autentication);
		URI uri = uriBuilder.path("/autentication/{id}").buildAndExpand(autentication.getIdAutentication()).toUri();
		return ResponseEntity.created(uri).body(autentication);
	} 
	
	@PostMapping("/login")
	@Transactional
	public ResponseEntity<?> login(@RequestBody AutenticationEntity autentication, UriComponentsBuilder uriBuilder) {
		Optional<AutenticationEntity> findUser = autenticationRepository.findByLogin(autentication.getLogin());
		Boolean passLogin = false;
		String message = new String();
		if (findUser.isPresent()) {
			
			passLogin = PasswordUtils.verifyUserPassword(autentication.getPassword(), findUser.get().getPassword(), findUser.get().getSalt());
			message = passLogin ? "Login realizado com sucesso" : "Senha invalida";
				
		}
		else {
			message = "Este login não existe";
		}
		
		URI uri = uriBuilder.path("/autentication/{id}").buildAndExpand(autentication.getIdAutentication()).toUri();
		return ResponseEntity.created(uri).body(message);	
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id,
			@RequestBody AutenticationEntity product) {
		
		
		
		
		
		return autenticationRepository.findById(id)
		           .map(record -> {
		               record.setEmail(product.getEmail());
		               record.setLogin(product.getLogin());
		               record.setPassword(product.getPassword());
		               record.setSalt(product.getSalt());
		               AutenticationEntity updated = autenticationRepository.save(record);
		               return ResponseEntity.ok().body(updated);
		           }).orElse(ResponseEntity.notFound().build());
		
		
	}
	
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<AutenticationEntity> product = autenticationRepository.findById(id);
		if (product.isPresent()) {
			autenticationRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	
}