package com.gestionale.testGestionale.service.impl;

import com.gestionale.testGestionale.entity.AziendaEntity;
import com.gestionale.testGestionale.exception.MyCustomException;
import com.gestionale.testGestionale.repository.AziendaRepository;
import com.gestionale.testGestionale.service.AziendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AziendaServiceImpl implements AziendaService {

    @Autowired
    private AziendaRepository repo;

    @Override
    public List<AziendaEntity> getAllAziende() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new MyCustomException("Si è verificato un errore durante il recupero di tutte le aziende", HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public AziendaEntity getAziendaById(int id) {
        Optional<AziendaEntity> idAz = repo.findById(id);
        if (idAz.isEmpty()) {
            throw new MyCustomException("Azienda con id " + id + " non trovata", HttpStatus.NOT_FOUND.value());
        }
        return idAz.get();
    }

    @Override
    @Transactional
    public void createAzienda(AziendaEntity azienda) {
        try {
            repo.save(azienda);
        } catch (DataIntegrityViolationException e) {
            throw new MyCustomException("Errore di integrità dei dati", HttpStatus.BAD_REQUEST.value());
        } catch (Exception e) {
            throw new MyCustomException("Errore sconosciuto", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @Transactional
    public void updateAziendaById(int id, AziendaEntity nuovaAzienda) {
        try {
            repo.findById(id)
                    .map(vecchiaAzienda -> {
                        vecchiaAzienda.setCitta(nuovaAzienda.getCitta());
                        vecchiaAzienda.setIndirizzo(nuovaAzienda.getIndirizzo());
                        vecchiaAzienda.setRagioneSociale(nuovaAzienda.getRagioneSociale());
                        vecchiaAzienda.setpIva(nuovaAzienda.getpIva());
                        return repo.save(vecchiaAzienda);
                    });
        } catch (NoSuchElementException e) {
            throw new MyCustomException("Azienda con id " + id + " non trovata", HttpStatus.NOT_FOUND.value());
        } catch (DataIntegrityViolationException e) {
            throw new MyCustomException("Errore di integrità dei dati", HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    @Transactional
    public void removeAzienda(int id) {
        try {
            AziendaEntity aziendaDaEliminare = getAziendaById(id);
            repo.delete(aziendaDaEliminare);
        } catch (NoSuchElementException e) {
            throw new MyCustomException("Azienda con id " + id + " non trovata", HttpStatus.NOT_FOUND.value());
        } catch (DataIntegrityViolationException e) {
            throw new MyCustomException("Errore di integrità dei dati", HttpStatus.BAD_REQUEST.value());
        }
    }

    public Map<Integer, String> listToMapIdRagSoc() {
    return repo.findAll()
            .stream()
            .collect(Collectors.toMap(AziendaEntity::getIdAz, AziendaEntity::getRagioneSociale));
    }


}
