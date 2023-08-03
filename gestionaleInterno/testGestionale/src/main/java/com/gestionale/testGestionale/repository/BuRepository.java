package com.gestionale.testGestionale.repository;

import com.gestionale.testGestionale.entity.BuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuRepository extends JpaRepository<BuEntity, Integer> {

    @Query(value = "SELECT id_bu, nome FROM bu", nativeQuery = true)
    List<Object[]> findIdAndNomeBu();

}
