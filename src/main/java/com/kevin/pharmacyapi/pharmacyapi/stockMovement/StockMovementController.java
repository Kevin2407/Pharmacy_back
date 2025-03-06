package com.kevin.pharmacyapi.pharmacyapi.stockMovement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/movimiento")
public class StockMovementController {
    @Autowired
    StockMovementRepository stockMovementRepository;

    @GetMapping()
    public ResponseEntity<Iterable<StockMovement>> findAll() {
        return ResponseEntity.ok(stockMovementRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<StockMovement>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(stockMovementRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<StockMovement> create(@RequestBody StockMovement newStockMovement, UriComponentsBuilder builder) {
        StockMovement savedStockMovement = stockMovementRepository.save(newStockMovement);
        URI uri = builder
                .path("/stockMovement/{id}")
                .buildAndExpand(savedStockMovement.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedStockMovement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockMovement> update(@PathVariable Long id, @RequestBody StockMovement stockMovementAct) {
        return stockMovementRepository.findById(id)
                .map(stockMovementAnt -> {
                    stockMovementAct.setId(stockMovementAnt.getId());
                    stockMovementRepository.save(stockMovementAct);
                    return ResponseEntity.noContent().<StockMovement>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return stockMovementRepository.findById(id)
                .map(stockMovement -> {
                    stockMovementRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}