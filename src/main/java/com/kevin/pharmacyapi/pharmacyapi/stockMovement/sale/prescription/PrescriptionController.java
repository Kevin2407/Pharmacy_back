package com.kevin.pharmacyapi.pharmacyapi.stockMovement.sale.prescription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receta")
public class PrescriptionController {
    @Autowired
    PrescriptionRepository prescriptionRepository;

    @GetMapping()
    public ResponseEntity<List<Prescription>> findAll() {
        return ResponseEntity.ok(prescriptionRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Prescription>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(prescriptionRepository.findById(id));
    }

    @GetMapping("/sale/{saleId}")
    public ResponseEntity<List<Prescription>> findBySaleId(@PathVariable Long saleId) {
        return ResponseEntity.ok(prescriptionRepository.findBySaleId(saleId));
    }

    @PostMapping
    public ResponseEntity<Prescription> create(@RequestBody Prescription newPrescription, UriComponentsBuilder builder) {
        Prescription savedPrescription = prescriptionRepository.save(newPrescription);
        URI uri = builder
                .path("/prescription/{id}")
                .buildAndExpand(savedPrescription.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedPrescription);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescription> update(@PathVariable Long id, @RequestBody Prescription prescriptionAct) {
        return prescriptionRepository.findById(id)
                .map(prescriptionAnt -> {
                    prescriptionAct.setId(prescriptionAnt.getId());
                    prescriptionRepository.save(prescriptionAct);
                    return ResponseEntity.noContent().<Prescription>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return prescriptionRepository.findById(id)
                .map(prescription -> {
                    prescriptionRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}