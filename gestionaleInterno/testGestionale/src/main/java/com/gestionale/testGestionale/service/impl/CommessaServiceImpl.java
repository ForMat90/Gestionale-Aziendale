package com.gestionale.testGestionale.service.impl;

import com.gestionale.testGestionale.entity.CommessaEntity;
import com.gestionale.testGestionale.exception.MyCustomException;
import com.gestionale.testGestionale.repository.CommessaRepository;
import com.gestionale.testGestionale.service.CommessaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommessaServiceImpl implements CommessaService {

    @Autowired
    private CommessaRepository repo;

    @Override
    public List<CommessaEntity> getAllCommesse() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new MyCustomException("Si è verificato un errore durante il recupero di tutte le commesse", HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public CommessaEntity getCommessaById(int id) {
        Optional<CommessaEntity> idCommessa = repo.findById(id);
        if (idCommessa.isEmpty()) {
            throw new MyCustomException("La commessa con id " + id + " non trovata", HttpStatus.NOT_FOUND.value());
        }
        return idCommessa.get();
    }

    @Override
    @Transactional
    public void createCommessa(CommessaEntity commessa) {
        try {
            repo.save(commessa);
        } catch (DataIntegrityViolationException e) {
            throw new MyCustomException("Errore di integrità dei dati", HttpStatus.BAD_REQUEST.value());
        } catch (Exception e) {
            throw new MyCustomException("Errore sconosciuto", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @Transactional
    public void updateCommessaById(int id, CommessaEntity nuovaCommessa) {
        try {
            repo.findById(id)
                    .map(vecchiaCommessa -> {
                        vecchiaCommessa.setNomeProg(nuovaCommessa.getNomeProg());
                        vecchiaCommessa.setCommittente(nuovaCommessa.getCommittente());
                        vecchiaCommessa.setDataInizioCommessa(nuovaCommessa.getDataInizioCommessa());
                        return repo.save(vecchiaCommessa);
                    });
        } catch (NoSuchElementException e) {
            throw new MyCustomException("Commessa con id " + id + " non trovata", HttpStatus.NOT_FOUND.value());
        } catch (DataIntegrityViolationException e) {
            throw new MyCustomException("Errore di integrità dei dati", HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    @Transactional
    public void removeCommessa(int id) {
        try {
            CommessaEntity commessaDaEliminare = getCommessaById(id);
            repo.delete(commessaDaEliminare);
        } catch (NoSuchElementException e) {
            throw new MyCustomException("Commessa con id " + id + " non trovata", HttpStatus.NOT_FOUND.value());
        } catch (DataIntegrityViolationException e) {
            throw new MyCustomException("Errore di integrità dei dati", HttpStatus.BAD_REQUEST.value());
        }
    }
}
