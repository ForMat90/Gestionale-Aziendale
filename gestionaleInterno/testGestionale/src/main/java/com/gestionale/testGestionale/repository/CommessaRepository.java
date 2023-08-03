package com.gestionale.testGestionale.repository;

import com.gestionale.testGestionale.entity.CommessaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommessaRepository extends JpaRepository<CommessaEntity, Integer> {

}
