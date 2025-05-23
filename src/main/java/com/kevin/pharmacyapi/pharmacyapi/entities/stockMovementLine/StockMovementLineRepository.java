package com.kevin.pharmacyapi.pharmacyapi.entities.stockMovementLine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMovementLineRepository extends JpaRepository<StockMovementLine, Long> {
    List<StockMovementLine> findByMovementId(Long movementId);
    @Query("""
           SELECT SUM(CASE 
                WHEN m.movementType = 'ENTRY' OR m.movementType = 'RETURN' THEN l.quantity 
                WHEN m.movementType = 'SALE' OR m.movementType = 'ADJUSTMENT' THEN -l.quantity 
                ELSE 0 END) 
           FROM StockMovementLine l 
           JOIN l.movement m 
           WHERE l.product.id = :productId
           """)
    Integer calculateStockForProduct(@Param("productId") Long productId);
}