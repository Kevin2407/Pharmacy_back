package com.kevin.pharmacyapi.pharmacyapi.repository;

import com.kevin.pharmacyapi.pharmacyapi.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
