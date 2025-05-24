package com.kevin.pharmacyapi.pharmacyapi.entities.stockMovement;

import com.kevin.pharmacyapi.pharmacyapi.relations.stock.StockMovementLineDTO;

public class StockErrorResponse {
    private String message;
    private java.util.List<StockMovementLineDTO> badItems;

    public StockErrorResponse(String message, java.util.List<StockMovementLineDTO> badItems) {
        this.message = message;
        this.badItems = badItems;

    }

    // Getters y setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public java.util.List<StockMovementLineDTO> getBadItems() {
        return badItems;
    }

    public void setBadItems(java.util.List<StockMovementLineDTO> badItems) {
        this.badItems = badItems;
    }
}