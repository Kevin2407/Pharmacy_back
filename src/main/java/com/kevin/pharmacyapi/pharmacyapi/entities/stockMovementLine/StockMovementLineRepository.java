package com.kevin.pharmacyapi.pharmacyapi.entities.stockMovementLine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMovementLineRepository extends JpaRepository<StockMovementLine, Long> {
    List<StockMovementLine> findByMovementId(Long movementId);
}