package com.gestionale.testGestionale.controller;

import com.gestionale.testGestionale.entity.BuEntity;
import com.gestionale.testGestionale.service.BuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bu")
public class BuController {

    @Autowired
    private BuService service;

    @GetMapping
    public ResponseEntity<List<BuEntity>> getAllBu() {
        List<BuEntity> bu = service.getAllBu();
        return ResponseEntity.ok(bu);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuEntity> getBuById(@PathVariable int id){
        BuEntity buId = service.getBuById(id);
        return ResponseEntity.ok(buId);
    }

    @PostMapping
    public ResponseEntity<Void> createBu(@RequestBody BuEntity bu) {
        service.createBu(bu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBu(@RequestBody BuEntity bu, @PathVariable int id) {
        service.updateBuById(id, bu);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuById(@PathVariable int id){
        service.removeBuById(id);
        return ResponseEntity.noContent().build();
    }

}
