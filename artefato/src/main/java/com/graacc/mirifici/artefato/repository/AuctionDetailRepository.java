package com.graacc.mirifici.artefato.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graacc.mirifici.artefato.entity.AuctionDetailEntity;

@Repository
public interface AuctionDetailRepository extends JpaRepository<AuctionDetailEntity, Long>{

	Optional<AuctionDetailEntity> findByThrowField(Double throwField);
}
