package com.gestionale.testGestionale.controller;

import com.gestionale.testGestionale.DTO.LavoratoreDTO;
import com.gestionale.testGestionale.entity.LavoratoreEntity;
import com.gestionale.testGestionale.service.LavoratoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lavoratori")
public class LavoratoreController {

    @Autowired
    private LavoratoreService service;

    @GetMapping
    public ResponseEntity<List<LavoratoreEntity>> getAllLavoratori() {
        List<LavoratoreEntity> lavoratori = service.getAllLavoratori();
        return ResponseEntity.ok(lavoratori);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LavoratoreEntity> getLavoratoreById(@PathVariable int id){
        LavoratoreEntity lavoratore = service.getLavoratoreById(id);
        return ResponseEntity.ok(lavoratore);
    }

    @PostMapping
    public ResponseEntity<Void> createLavoratoreCommesse(@RequestBody LavoratoreEntity lavoratore){
        service.createLavoratore(lavoratore);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLavoratore(@RequestBody LavoratoreEntity lavoratore, @PathVariable int id){
        service.updateLavoratoreById(id, lavoratore);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLavoratore(@PathVariable int id){
        service.removeLavoratore(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<LavoratoreEntity> searchFiscalCodeLavoratore(@RequestParam String codFisc) {
        LavoratoreEntity lavoratore = service.searchByFiscalCode(codFisc);
        return ResponseEntity.ok(lavoratore);
    }

    public LavoratoreController(LavoratoreService lavoratoreService) {
        this.service = lavoratoreService;
    }

    @GetMapping("/getLavoratoriByAzienda/{idAzienda}")
    public List<LavoratoreDTO> getLavoratoriByAzienda(@PathVariable int idAzienda) {
        return service.getLavoratoriByAzienda(idAzienda);
    }
}
