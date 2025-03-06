package com.kevin.pharmacyapi.pharmacyapi.product.medication.prescriptionRequirement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRequirementRepository extends CrudRepository<PrescriptionRequirement, Long> {
}