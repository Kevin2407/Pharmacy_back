package com.kevin.pharmacyapi.pharmacyapi.product.medication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/medicamento")
public class MedicationController {
    @Autowired
    MedicationRepository medicationRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Medication>> findAll() {
        return ResponseEntity.ok(medicationRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Medication>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(medicationRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Medication> create(@RequestBody Medication newMedication, UriComponentsBuilder builder) {
        Medication savedMedication = medicationRepository.save(newMedication);
        URI uri = builder
                .path("/medicamento/{id}")
                .buildAndExpand(savedMedication.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedMedication);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medication> update(@PathVariable Long id, @RequestBody Medication medicationAct) {
        Medication medicationAnt = medicationRepository.findById(id).get();
        if(medicationAnt != null) {
            medicationAct.setId(medicationAnt.getId());
            medicationRepository.save(medicationAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(medicationRepository.findById(id).get() != null) {
            medicationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}