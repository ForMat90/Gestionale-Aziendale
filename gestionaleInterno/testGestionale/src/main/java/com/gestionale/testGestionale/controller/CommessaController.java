package com.gestionale.testGestionale.controller;

import com.gestionale.testGestionale.entity.CommessaEntity;
import com.gestionale.testGestionale.service.CommessaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commesse")
public class CommessaController {

    @Autowired
    private CommessaService service;

    @GetMapping
    public ResponseEntity<List<CommessaEntity>> getAllCommesse() {
        List<CommessaEntity> commesse = service.getAllCommesse();
        return ResponseEntity.ok(commesse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommessaEntity> getCommessaById(@PathVariable int id){
        CommessaEntity commessa = service.getCommessaById(id);
        return ResponseEntity.ok(commessa);
    }

    @PostMapping
    public ResponseEntity<Void> createCommessa(@RequestBody CommessaEntity commessa){
        service.createCommessa(commessa);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCommessaById(@RequestBody CommessaEntity commessa, @PathVariable int id){
        service.updateCommessaById(id, commessa);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommessa(@PathVariable int id){
        service.removeCommessa(id);
        return ResponseEntity.noContent().build();
    }

}
