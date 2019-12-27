package com.graacc.mirifici.artefato.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graacc.mirifici.artefato.entity.AutenticationEntity;
@Repository
public interface AutenticationRepository extends JpaRepository<AutenticationEntity, Long>{
	public Optional<AutenticationEntity> findByLogin(String login);
	public Optional<AutenticationEntity> findByEmail(String email);

}
