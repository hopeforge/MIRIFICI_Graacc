package com.graacc.mirifici.artefato.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.graacc.mirifici.artefato.repository.AuctionDetailRepository;
import com.graacc.mirifici.artefato.repository.AuctionRepository;

@Controller
@RequestMapping("/auctiondetail")
public class AuctionDetailController {
	
	@Autowired
	private AuctionDetailRepository auctionDetailRepository;
	
	@Autowired
	private SalesController salesController;
	
	@Autowired
	private AuctionRepository auctionRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<AuctionDetailEntity> cadastrar(@RequestBody AuctionDetailEntity auctionDetail, UriComponentsBuilder uriBuilder) {
		
		auctionDetailRepository.save(auctionDetail);

		URI uri = uriBuilder.path("/auctiondetail/{id}").buildAndExpand(auctionDetail.getIdAuction()).toUri();
		return ResponseEntity.created(uri).body(auctionDetail);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> detalhar(@PathVariable Long id) {
		Optional<AuctionDetailEntity> auctionDetail = auctionDetailRepository.findById(id);
		if (auctionDetail.isPresent()) {
			return ResponseEntity.ok(auctionDetail);
		}

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id,
			@RequestBody AuctionDetailEntity auctionDetail) {
		
		return auctionDetailRepository.findById(id)
		           .map(record -> {
		               record.setIdAuctionDetail(auctionDetail.getIdAuctionDetail());
		               record.setThrowField(auctionDetail.getThrowField());
		               record.setThrowDate(auctionDetail.getThrowDate());
		               record.setIdAuction(auctionDetail.getIdAuction());
		               record.setIdCustomer(auctionDetail.getIdCustomer());
		               AuctionDetailEntity updated = auctionDetailRepository.save(record);
		               return ResponseEntity.ok().body(updated);
		           }).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<AuctionDetailEntity> auctionDetail = auctionDetailRepository.findById(id);
		if (auctionDetail.isPresent()) {
			auctionDetailRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	
	@GetMapping("/maiorlance/{id}")
	@Transactional
	public ResponseEntity<?> maiorLance(@PathVariable Long id) {
		Double maiorLance = 0.0;
		Date dataMaiorLance = null;
		Optional<AuctionEntity> auction = auctionRepository.findById(id);
		if (auction.isPresent()) {
			List<AuctionDetailEntity> auctionDetail = auctionDetailRepository.findAll();
			if(!auctionDetail.isEmpty()) {
			for(int i=0; i<auctionDetail.size();i++) {
				Double lance = auctionDetail.get(i).getThrowField();
				Date dataLance = auctionDetail.get(i).getThrowDate();
				if(lance > maiorLance) {
					maiorLance = lance;
					dataMaiorLance = dataLance;
				}
				else 
					if (lance == maiorLance) {
						if(dataLance.before(dataMaiorLance)) {
							maiorLance = lance;
							dataMaiorLance = dataLance;
						}
					}
			}
			}
			Optional<AuctionDetailEntity> aucMaiorLance = auctionDetailRepository.findByThrowField(maiorLance);
			return ResponseEntity.ok(aucMaiorLance);
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/lance/{id}")
	@Transactional
	public ResponseEntity<?> darLance(@PathVariable Long id, @RequestBody AuctionDetailEntity auctionDetail, UriComponentsBuilder uriBuilder) {
		AuctionEntity ae = new AuctionEntity();

		Date dataLance = auctionDetail.getThrowDate();
		 ae.setFinalAuctionDate(auctionRepository.findById(auctionDetail.getIdAuction()).get().getFinalAuctionDate());
		if(dataLance.before(ae.getFinalAuctionDate()) || dataLance.equals(ae.getFinalAuctionDate())) {
			auctionDetailRepository.save(auctionDetail);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		}
		else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Leilão Encerrado.");
		}
	}
	
	@PostMapping(value = "/teste")
	public String encerrarLeilao(@PathVariable Long id, @RequestBody AuctionDetailEntity auctionDetail, UriComponentsBuilder uriBuilder) {
		Double maiorLance = 0.0;
		List<AuctionDetailEntity> auctionDetailUpd = auctionDetailRepository.findAll();
		if(!auctionDetailUpd.isEmpty()) {
			for(int i=0; i < auctionDetailUpd.size(); i++) {
				Double lance = auctionDetailUpd.get(i).getThrowField();
				if(lance > maiorLance) {
				maiorLance = lance;
				}
			}
		}
		Optional<AuctionDetailEntity> auctionDetailUpdT = auctionDetailRepository.findByThrowField(maiorLance);
		salesController.atualizarVenda(auctionDetailUpdT.get() , uriBuilder);
		return "redirect:/sales/atualizar";
	}
}
