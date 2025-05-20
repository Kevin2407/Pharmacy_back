package com.kevin.pharmacyapi.pharmacyapi.relations.stock;
import com.kevin.pharmacyapi.pharmacyapi.entities.sale.Sale;
import com.kevin.pharmacyapi.pharmacyapi.entities.stockMovement.MovementType;
import com.kevin.pharmacyapi.pharmacyapi.entities.supplier.Supplier;
import com.kevin.pharmacyapi.pharmacyapi.relations.stock.StockMovementLineDTO;
import lombok.Data;

import java.util.List;

@Data
public class StockMovementDTO {
    private MovementType movementType;
    private Supplier supplier;
    private Sale sale;
    private List<StockMovementLineDTO> lines;
}
