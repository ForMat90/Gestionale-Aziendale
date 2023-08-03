package com.gestionale.testGestionale.service.impl;

import com.gestionale.testGestionale.DTO.LavoratoreDTO;
import com.gestionale.testGestionale.entity.AziendaEntity;
import com.gestionale.testGestionale.entity.LavoratoreEntity;
import com.gestionale.testGestionale.exception.MyCustomException;
import com.gestionale.testGestionale.repository.LavoratoreRepository;
import com.gestionale.testGestionale.service.LavoratoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class LavoratoreServiceImpl implements LavoratoreService {

    @Autowired
    private LavoratoreRepository repo;

    @Autowired
    private AziendaServiceImpl aziendaService;

    @Autowired
    private BuServiceImpl buService;

    @Override
    public List<LavoratoreEntity> getAllLavoratori() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new MyCustomException("Si è verificato un errore durante il recupero di tutti i lavoratori", HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public LavoratoreEntity getLavoratoreById(int id) {
        Optional<LavoratoreEntity> idLavoratore = repo.findById(id);
        if (idLavoratore.isEmpty()) {
            throw new MyCustomException("Il lavoratore con id " + id + " non trovato", HttpStatus.NOT_FOUND.value());
        }
        return idLavoratore.get();
    }

    @Override
    @Transactional
    public LavoratoreEntity createLavoratore(LavoratoreEntity lavoratore) {
        try {
            repo.save(lavoratore);
        } catch (DataIntegrityViolationException e) {
            throw new MyCustomException("Errore di integrità dei dati", HttpStatus.BAD_REQUEST.value());
        } catch (Exception e) {
            throw new MyCustomException("Errore sconosciuto", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return lavoratore;
    }

    @Override
    @Transactional
    public void updateLavoratoreById(int id, LavoratoreEntity nuovoLavoratore) {
        try {
            repo.findById(id)
                    .map(vecchioLavoratore -> {
                        vecchioLavoratore.setCognome(nuovoLavoratore.getCognome());
                        vecchioLavoratore.setNome(nuovoLavoratore.getNome());
                        vecchioLavoratore.setAziende(nuovoLavoratore.getAziende());
                        vecchioLavoratore.setBu(nuovoLavoratore.getBu());
                        vecchioLavoratore.setCodFisc(nuovoLavoratore.getCodFisc());
                        try {
                            vecchioLavoratore.setDataAssunzione(nuovoLavoratore.getDataAssunzione().toString());
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        if (!nuovoLavoratore.getDataFine().toString().isEmpty()) {
                            try {
                                vecchioLavoratore.setDataFine(nuovoLavoratore.getDataFine().toString());
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            try {
                                vecchioLavoratore.setDataFine(null);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        vecchioLavoratore.setCodTl(nuovoLavoratore.getCodTl());
                        vecchioLavoratore.setCodManag(nuovoLavoratore.getCodManag());
                        return repo.save(vecchioLavoratore);
                    });
        } catch (NoSuchElementException e) {
            throw new MyCustomException("Il lavoratore con id " + id + " non trovato", HttpStatus.NOT_FOUND.value());
        } catch (DataIntegrityViolationException e) {
            throw new MyCustomException("Errore di integrità dei dati", HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    @Transactional
    public Object removeLavoratore(int id) {
        try {
            LavoratoreEntity lavoratoreDaEliminare = getLavoratoreById(id);
            repo.delete(lavoratoreDaEliminare);
        } catch (NoSuchElementException e) {
            throw new MyCustomException("Il lavoratore con id " + id + " non trovato", HttpStatus.NOT_FOUND.value());
        } catch (DataIntegrityViolationException e) {
            throw new MyCustomException("Errore di integrità dei dati", HttpStatus.BAD_REQUEST.value());
        }
        return null;
    }



    @Override
    public LavoratoreEntity searchByFiscalCode(String codFisc) {
        Optional<LavoratoreEntity> fiscalCodeLavoratore = repo.findByCodFisc(codFisc);
        if (fiscalCodeLavoratore.isEmpty()) {
            throw new MyCustomException("Il lavoratore con il codice fiscale " + codFisc + " non trovato", HttpStatus.NOT_FOUND.value());
        }
        return fiscalCodeLavoratore.get();
    }

    public List<LavoratoreDTO> getAllLavoratoriDTO() {
        List<LavoratoreEntity> lavoratori = getAllLavoratori();
        List<LavoratoreDTO> nuovaListaRagSocAndBu = new ArrayList<>();
        Map<Integer, String> mappaRagSoc = aziendaService.listToMapIdRagSoc();
        Map<Integer, String> mappaBu = buService.listToMapIdNomeBu();

        for (LavoratoreEntity lavoratore : lavoratori) {

            LavoratoreDTO nuovoLavoratore = new LavoratoreDTO();
            nuovoLavoratore.setIdLav(lavoratore.getIdLav());
            nuovoLavoratore.setCognome(lavoratore.getCognome());
            nuovoLavoratore.setNome(lavoratore.getNome());
            nuovoLavoratore.setCodFisc(lavoratore.getCodFisc());

            String azienda = mappaRagSoc.get(lavoratore.getAziende());
            nuovoLavoratore.setRagioneSociale(azienda);
            String bu = mappaBu.get(lavoratore.getBu());
            nuovoLavoratore.setNomeBu(bu);

            nuovoLavoratore.setDataAssunzione(lavoratore.getDataAssunzione());
            nuovoLavoratore.setDataFine(lavoratore.getDataFine());
            nuovoLavoratore.setTeamLeader(lavoratore.getCodTl());
            nuovoLavoratore.setManager(lavoratore.getCodManag());

            nuovaListaRagSocAndBu.add(nuovoLavoratore);
        }

        return nuovaListaRagSocAndBu;
    }

    public List<LavoratoreDTO> getLavoratoriByIdAzDTO() {
        List<LavoratoreEntity> lavoratori = getAllLavoratori();
        List<LavoratoreDTO> nuovaListaRagSocAndBu = new ArrayList<>();
        Map<Integer, String> mappaRagSoc = aziendaService.listToMapIdRagSoc();
        Map<Integer, String> mappaBu = buService.listToMapIdNomeBu();

        for (LavoratoreEntity lavoratore : lavoratori) {

            LavoratoreDTO nuovoLavoratore = new LavoratoreDTO();
            nuovoLavoratore.setIdLav(lavoratore.getIdLav());
            nuovoLavoratore.setCognome(lavoratore.getCognome());
            nuovoLavoratore.setNome(lavoratore.getNome());
            nuovoLavoratore.setCodFisc(lavoratore.getCodFisc());

            String azienda = mappaRagSoc.get(lavoratore.getAziende());
            nuovoLavoratore.setRagioneSociale(azienda);
            String bu = mappaBu.get(lavoratore.getBu());
            nuovoLavoratore.setNomeBu(bu);

            nuovoLavoratore.setDataAssunzione(lavoratore.getDataAssunzione());
            nuovoLavoratore.setDataFine(lavoratore.getDataFine());
            nuovoLavoratore.setTeamLeader(lavoratore.getCodTl());
            nuovoLavoratore.setManager(lavoratore.getCodManag());

            nuovaListaRagSocAndBu.add(nuovoLavoratore);
        }

        return nuovaListaRagSocAndBu;
    }

    public List<LavoratoreDTO> getLavoratoriByAzienda(int idAzienda) {
        List<LavoratoreEntity> lavoratori = getLavoratoriByAziendaId(idAzienda);
        List<LavoratoreDTO> nuovaListaRagSocAndBu = new ArrayList<>();
        Map<Integer, String> mappaRagSoc = aziendaService.listToMapIdRagSoc();
        Map<Integer, String> mappaBu = buService.listToMapIdNomeBu();

        for (LavoratoreEntity lavoratore : lavoratori) {
            LavoratoreDTO nuovoLavoratore = new LavoratoreDTO();
            nuovoLavoratore.setIdLav(lavoratore.getIdLav());
            nuovoLavoratore.setCognome(lavoratore.getCognome());
            nuovoLavoratore.setNome(lavoratore.getNome());
            nuovoLavoratore.setCodFisc(lavoratore.getCodFisc());

            String azienda = mappaRagSoc.get(lavoratore.getAziende());
            nuovoLavoratore.setRagioneSociale(azienda);
            String bu = mappaBu.get(lavoratore.getBu());
            nuovoLavoratore.setNomeBu(bu);

            nuovoLavoratore.setDataAssunzione(lavoratore.getDataAssunzione());
            nuovoLavoratore.setDataFine(lavoratore.getDataFine());
            nuovoLavoratore.setTeamLeader(lavoratore.getCodTl());
            nuovoLavoratore.setManager(lavoratore.getCodManag());

            nuovaListaRagSocAndBu.add(nuovoLavoratore);
        }

        return nuovaListaRagSocAndBu;
    }

    private List<LavoratoreEntity> getLavoratoriByAziendaId(int idAzienda) {
        return repo.findByAziende(idAzienda);
    }

}

