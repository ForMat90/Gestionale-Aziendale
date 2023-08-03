package com.gestionale.testGestionale.repository;

import com.gestionale.testGestionale.entity.AziendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AziendaRepository extends JpaRepository<AziendaEntity, Integer> {

    @Query(value = "SELECT id_az, ragione_sociale FROM aziende", nativeQuery = true)
    List<Object[]> findIdAndRagioneSociale();

}




