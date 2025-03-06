package com.kevin.pharmacyapi.pharmacyapi.stockMovement.sale.prescription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findBySaleId(Long saleId);
}