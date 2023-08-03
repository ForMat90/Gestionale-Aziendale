package com.gestionale.testGestionale.service;

import com.gestionale.testGestionale.entity.CommessaEntity;

import java.util.List;

public interface CommessaService {
    List<CommessaEntity> getAllCommesse();

    CommessaEntity getCommessaById(int id);

    void createCommessa(CommessaEntity commessa);

    void updateCommessaById(int id, CommessaEntity commessa);

    void removeCommessa(int id);
}
