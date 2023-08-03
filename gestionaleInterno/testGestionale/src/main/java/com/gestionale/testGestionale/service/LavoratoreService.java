package com.gestionale.testGestionale.service;

import com.gestionale.testGestionale.DTO.LavoratoreDTO;
import com.gestionale.testGestionale.entity.LavoratoreEntity;

import java.util.List;

public interface LavoratoreService {

    List<LavoratoreEntity> getAllLavoratori();

    LavoratoreEntity getLavoratoreById(int id);

    LavoratoreEntity createLavoratore(LavoratoreEntity lavoratore);

    void updateLavoratoreById(int id, LavoratoreEntity lavoratore);

    Object removeLavoratore(int id);

    LavoratoreEntity searchByFiscalCode(String codFisc);

    Object getAllLavoratoriDTO();

    List<LavoratoreDTO> getLavoratoriByAzienda(int idAzienda);
}
