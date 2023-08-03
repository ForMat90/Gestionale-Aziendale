package com.gestionale.testGestionale.repository;

import com.gestionale.testGestionale.entity.LavoratoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LavoratoreRepository extends JpaRepository<LavoratoreEntity, Integer> {

    Optional<LavoratoreEntity> findByCodFisc(String codFisc);
    List<LavoratoreEntity> findByAziende(int idAzienda);
}
