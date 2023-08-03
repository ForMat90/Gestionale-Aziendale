package com.gestionale.testGestionale.service;

import com.gestionale.testGestionale.entity.BuEntity;

import java.util.List;

public interface BuService {
    List<BuEntity> getAllBu();

    BuEntity getBuById(int id);

    void createBu(BuEntity bu);

    void updateBuById(int id, BuEntity nuovaBu);

    void removeBuById(int id);
}
