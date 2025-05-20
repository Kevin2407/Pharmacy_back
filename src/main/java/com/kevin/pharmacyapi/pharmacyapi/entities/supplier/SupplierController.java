package com.kevin.pharmacyapi.pharmacyapi.entities.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/proveedor")
public class SupplierController {
    @Autowired
    SupplierRepository supplierRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Supplier>> findAll() {
        return ResponseEntity.ok(supplierRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Supplier>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Supplier> create(@RequestBody Supplier newSupplier, UriComponentsBuilder builder) {
        Supplier savedSupplier = supplierRepository.save(newSupplier);
        URI uri = builder
                .path("/supplier/{id}")
                .buildAndExpand(savedSupplier.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedSupplier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> update(@PathVariable Long id, @RequestBody Supplier supplierAct) {
        return supplierRepository.findById(id)
                .map(supplierAnt -> {
                    supplierAct.setId(supplierAnt.getId());
                    supplierRepository.save(supplierAct);
                    return ResponseEntity.noContent().<Supplier>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return supplierRepository.findById(id)
                .map(supplier -> {
                    supplierRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}