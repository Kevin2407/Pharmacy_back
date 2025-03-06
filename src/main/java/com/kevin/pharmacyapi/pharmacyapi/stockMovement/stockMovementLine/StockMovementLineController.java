package com.kevin.pharmacyapi.pharmacyapi.stockMovement.stockMovementLine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lineaMovimiento")
public class StockMovementLineController {
    @Autowired
    StockMovementLineRepository stockMovementLineRepository;

    @GetMapping()
    public ResponseEntity<List<StockMovementLine>> findAll() {
        return ResponseEntity.ok(stockMovementLineRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<StockMovementLine>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(stockMovementLineRepository.findById(id));
    }

    @GetMapping("/movement/{movementId}")
    public ResponseEntity<List<StockMovementLine>> findByMovementId(@PathVariable Long movementId) {
        return ResponseEntity.ok(stockMovementLineRepository.findByMovementId(movementId));
    }

    @PostMapping
    public ResponseEntity<StockMovementLine> create(@RequestBody StockMovementLine newLine, UriComponentsBuilder builder) {
        StockMovementLine savedLine = stockMovementLineRepository.save(newLine);
        URI uri = builder
                .path("/stockMovementLine/{id}")
                .buildAndExpand(savedLine.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedLine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockMovementLine> update(@PathVariable Long id, @RequestBody StockMovementLine lineAct) {
        return stockMovementLineRepository.findById(id)
                .map(lineAnt -> {
                    lineAct.setId(lineAnt.getId());
                    stockMovementLineRepository.save(lineAct);
                    return ResponseEntity.noContent().<StockMovementLine>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return stockMovementLineRepository.findById(id)
                .map(line -> {
                    stockMovementLineRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}