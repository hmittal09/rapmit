package com.rapmit.inventoryservice.repository;

import com.rapmit.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {


    List<Inventory> findByProductCodeIn(List<String> productCodes);
}
