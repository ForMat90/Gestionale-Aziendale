package com.gestionale.testGestionale.service.impl;

import com.gestionale.testGestionale.entity.AziendaEntity;
import com.gestionale.testGestionale.entity.BuEntity;
import com.gestionale.testGestionale.exception.MyCustomException;
import com.gestionale.testGestionale.repository.BuRepository;
import com.gestionale.testGestionale.service.BuService;
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
public class BuServiceImpl implements BuService {

    @Autowired
    private BuRepository repo;

    @Override
    public List<BuEntity> getAllBu() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new MyCustomException("Si è verificato un errore durante il recupero di tutte le BU", HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public BuEntity getBuById(int id) {
        Optional<BuEntity> idBu = repo.findById(id);
        if (idBu.isEmpty()) {
            throw new MyCustomException("La Bu con id " + id + " non trovata", HttpStatus.NOT_FOUND.value());
        }
        return idBu.get();
    }

    @Override
    @Transactional
    public void createBu(BuEntity bu) {
        try {
            repo.save(bu);
        } catch (DataIntegrityViolationException e) {
            throw new MyCustomException("Errore di integrità dei dati", HttpStatus.BAD_REQUEST.value());
        } catch (Exception e) {
            throw new MyCustomException("Errore sconosciuto", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @Transactional
    public void updateBuById(int id, BuEntity nuovaBu) {
        try {
            BuEntity vecchiaBu = getBuById(id);
            nuovaBu.setIdBu(vecchiaBu.getIdBu());
            repo.save(nuovaBu);
        } catch (NoSuchElementException e) {
            throw new MyCustomException("Bu con id " + id + " non trovata", HttpStatus.NOT_FOUND.value());
        } catch (DataIntegrityViolationException e) {
            throw new MyCustomException("Errore di integrità dei dati", HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    @Transactional
    public void removeBuById(int id) {
        try {
            BuEntity buDaEliminare = getBuById(id);
            repo.delete(buDaEliminare);
        } catch (NoSuchElementException e) {
            throw new MyCustomException("Bu con id " + id + " non trovata", HttpStatus.NOT_FOUND.value());
        } catch (DataIntegrityViolationException e) {
            throw new MyCustomException("Errore di integrità dei dati", HttpStatus.BAD_REQUEST.value());
        }
    }

    public Map<Integer, String> listToMapIdNomeBu() {
        return repo.findAll()
                .stream()
                .collect(Collectors.toMap(BuEntity::getIdBu, BuEntity::getNome));
    }
}
