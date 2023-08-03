package com.gestionale.testGestionale.controller;

import com.gestionale.testGestionale.entity.AziendaEntity;
import com.gestionale.testGestionale.service.AziendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aziende")
public class AziendaController {

    @Autowired
    private AziendaService service;

    @GetMapping
    public ResponseEntity<List<AziendaEntity>> getAllAziende() {
        List<AziendaEntity> aziende = service.getAllAziende();
        return ResponseEntity.ok(aziende);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AziendaEntity> getAziendaById(@PathVariable int id){
        AziendaEntity aziendaById = service.getAziendaById(id);
        return ResponseEntity.ok(aziendaById);
    }

    @PostMapping
    public ResponseEntity<Void> createAzienda(@RequestBody AziendaEntity azienda){
        service.createAzienda(azienda);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAzienda(@RequestBody AziendaEntity azienda, @PathVariable int id){
        service.updateAziendaById(id, azienda);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAzienda(@PathVariable int id){
        service.removeAzienda(id);
        return ResponseEntity.noContent().build();
    }
}
