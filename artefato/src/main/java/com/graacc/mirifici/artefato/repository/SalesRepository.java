package com.graacc.mirifici.artefato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graacc.mirifici.artefato.entity.SalesEntity;

@Repository
public interface SalesRepository extends JpaRepository<SalesEntity, Long>{

}
