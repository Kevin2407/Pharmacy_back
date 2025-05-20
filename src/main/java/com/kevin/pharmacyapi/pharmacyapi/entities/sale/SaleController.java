package com.kevin.pharmacyapi.pharmacyapi.entities.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/venta")
public class SaleController {
    @Autowired
    SaleRepository saleRepository;

    @GetMapping()
    public ResponseEntity<List<Sale>> findAll() {
        return ResponseEntity.ok(saleRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Sale>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(saleRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Sale> create(@RequestBody Sale newSale, UriComponentsBuilder builder) {
        Sale savedSale = saleRepository.save(newSale);
        URI uri = builder
                .path("/sale/{id}")
                .buildAndExpand(savedSale.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedSale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> update(@PathVariable Long id, @RequestBody Sale saleAct) {
        return saleRepository.findById(id)
                .map(saleAnt -> {
                    saleAct.setId(saleAnt.getId());
                    saleRepository.save(saleAct);
                    return ResponseEntity.noContent().<Sale>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return saleRepository.findById(id)
                .map(sale -> {
                    saleRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}