package com.gestionale.testGestionale.service;

import com.gestionale.testGestionale.entity.AziendaEntity;

import java.util.List;

public interface AziendaService {


    List<AziendaEntity> getAllAziende();

    AziendaEntity getAziendaById(int id);

    void createAzienda(AziendaEntity azienda);

    void updateAziendaById(int id, AziendaEntity azienda);

    void removeAzienda(int id);
}
