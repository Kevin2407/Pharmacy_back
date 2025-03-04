package com.kevin.pharmacyapi.pharmacyapi.medication.prescriptionRequirement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRequirementRepository extends CrudRepository<PrescriptionRequirement, Long> {
}