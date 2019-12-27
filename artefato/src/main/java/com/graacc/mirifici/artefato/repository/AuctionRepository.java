package com.graacc.mirifici.artefato.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graacc.mirifici.artefato.entity.AuctionDetailEntity;
import com.graacc.mirifici.artefato.entity.AuctionEntity;

@Repository
public interface AuctionRepository extends JpaRepository<AuctionEntity,Long>{

	Optional<AuctionEntity> findByIdProduto(Long idProduto);
}
