package com.scalable.shoppify.repository;

import com.scalable.shoppify.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface InventoryRepository extends MongoRepository<Inventory, Integer> {
    Optional<Inventory> findByItemId(int itemId);
}

