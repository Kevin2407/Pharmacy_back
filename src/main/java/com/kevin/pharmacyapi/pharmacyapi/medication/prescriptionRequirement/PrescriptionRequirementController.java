package com.kevin.pharmacyapi.pharmacyapi.medication.prescriptionRequirement;

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
@RequestMapping("/prescriptionRequirement")
public class PrescriptionRequirementController {
    @Autowired
    PrescriptionRequirementRepository prescriptionRequirementRepository;

    @GetMapping()
    public ResponseEntity<Iterable<PrescriptionRequirement>> findAll() {
        return ResponseEntity.ok(prescriptionRequirementRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PrescriptionRequirement>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(prescriptionRequirementRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<PrescriptionRequirement> create(@RequestBody PrescriptionRequirement newPrescriptionRequirement, UriComponentsBuilder builder) {
        PrescriptionRequirement savedPrescriptionRequirement = prescriptionRequirementRepository.save(newPrescriptionRequirement);
        URI uri = builder
                .path("/prescriptionRequirement/{id}")
                .buildAndExpand(savedPrescriptionRequirement.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedPrescriptionRequirement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionRequirement> update(@PathVariable Long id, @RequestBody PrescriptionRequirement prescriptionRequirementAct) {
        PrescriptionRequirement prescriptionRequirementAnt = prescriptionRequirementRepository.findById(id).get();
        if(prescriptionRequirementAnt != null) {
            prescriptionRequirementAct.setId(prescriptionRequirementAnt.getId());
            prescriptionRequirementRepository.save(prescriptionRequirementAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(prescriptionRequirementRepository.findById(id).get() != null) {
            prescriptionRequirementRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}