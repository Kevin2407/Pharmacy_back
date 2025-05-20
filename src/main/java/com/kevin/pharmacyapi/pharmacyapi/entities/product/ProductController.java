package com.kevin.pharmacyapi.pharmacyapi.entities.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org. springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/producto")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @GetMapping()
    public ResponseEntity<Iterable<Product>> findAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productRepository.findById(id));
    }
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product newProduct, UriComponentsBuilder builder) {
        Product savedProduct = productRepository.save(newProduct);
        URI uri = builder
                .path("/producto/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedProduct);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product productAct) {
        Product productAnt = productRepository.findById(id).get();
        if(productAnt != null) {
            productAct.setId(productAnt.getId());
            productRepository.save(productAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(productRepository.findById(id).get() != null) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
