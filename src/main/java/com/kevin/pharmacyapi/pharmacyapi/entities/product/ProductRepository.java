package com.kevin.pharmacyapi.pharmacyapi.entities.product;

import com.kevin.pharmacyapi.pharmacyapi.relations.stock.QueryStock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query(value = """
    SELECT 
        p.id AS id,
        p.name AS name,
        p.description AS description,
        p.price AS price,
        COALESCE(SUM(
            CASE 
                WHEN sm.movement_type IN ('ENTRY', 'RETURN') THEN sml.quantity
                WHEN sm.movement_type IN ('SALE', 'ADJUSTMENT') THEN -sml.quantity
                ELSE 0
            END
        ), 0) AS stock
    FROM product p
    LEFT JOIN stock_movement_line sml ON p.id = sml.product_id
    LEFT JOIN stock_movement sm ON sml.movement_id = sm.id
    GROUP BY p.id, p.name, p.description
    ORDER BY p.id
    """, nativeQuery = true)
    List<QueryStock> findAllProductsWithStock();
}
