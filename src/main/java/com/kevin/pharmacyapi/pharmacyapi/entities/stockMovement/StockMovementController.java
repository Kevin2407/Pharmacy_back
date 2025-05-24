package com.kevin.pharmacyapi.pharmacyapi.entities.stockMovement;

import com.kevin.pharmacyapi.pharmacyapi.entities.product.ProductRepository;
import com.kevin.pharmacyapi.pharmacyapi.entities.stockMovementLine.StockMovementLine;
import com.kevin.pharmacyapi.pharmacyapi.entities.stockMovementLine.StockMovementLineRepository;
import com.kevin.pharmacyapi.pharmacyapi.relations.stock.StockMovementDTO;
import com.kevin.pharmacyapi.pharmacyapi.relations.stock.StockMovementLineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/movimiento")
public class StockMovementController {
    @Autowired
    StockMovementRepository stockMovementRepository;

    @Autowired
    StockMovementLineRepository stockMovementLineRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping()
    public ResponseEntity<Iterable<StockMovement>> findAll() {
        return ResponseEntity.ok(stockMovementRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<StockMovement>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(stockMovementRepository.findById(id));
    }

//    @PostMapping
//    public ResponseEntity<StockMovement> create(@RequestBody StockMovement newStockMovement, UriComponentsBuilder builder) {
//        StockMovement savedStockMovement = stockMovementRepository.save(newStockMovement);
//        URI uri = builder
//                .path("/stockMovement/{id}")
//                .buildAndExpand(savedStockMovement.getId())
//                .toUri();
//        return ResponseEntity.created(uri).body(savedStockMovement);
//    }

    @PostMapping
    public ResponseEntity<?> createWithLines(@RequestBody StockMovementDTO dto, UriComponentsBuilder builder) {
    System.out.println("DTO: " + dto.getMovementType());
    System.out.println("Condition: " + MovementType.SALE.equals(dto.getMovementType()));
        if (MovementType.SALE.equals(dto.getMovementType()) || MovementType.ADJUSTMENT.equals(dto.getMovementType())) {
            List<StockMovementLineDTO> badLines = new ArrayList<>();
            for (StockMovementLineDTO lineDTO : dto.getLines()) {
                Long productId = lineDTO.getProduct().getId();
                int requestedQuantity = lineDTO.getQuantity();

                Integer availableStock = stockMovementLineRepository.calculateStockForProduct(productId);

                if (availableStock == null) {
                    availableStock = 0;
                }

                if (availableStock < requestedQuantity) {
                    badLines.add(lineDTO);
                }
            }
            if (!badLines.isEmpty()) {
                StockErrorResponse errorResponse = new StockErrorResponse("INSUFFICIENT_STOCK", badLines);
                return ResponseEntity.unprocessableEntity().body(errorResponse);
            }
        }

        StockMovement movement = new StockMovement();
        movement.setMovementType(dto.getMovementType());

        if("ENTRY".equals(dto.getMovementType())) {
            movement.setSupplier(dto.getSupplier());
        } else if("SALE".equals(dto.getMovementType())) {
            movement.setSale(dto.getSale());
        }

        StockMovement savedMovement = stockMovementRepository.save(movement);

        List<StockMovementLine> lines = new ArrayList<>();
        for (StockMovementLineDTO lineDTO : dto.getLines()) {
            StockMovementLine line = new StockMovementLine();
            line.setMovement(savedMovement);
            line.setProduct(lineDTO.getProduct());
            line.setQuantity(lineDTO.getQuantity());
            line.setBatch_number(lineDTO.getBachNumber());
            line.setExpiration_date(lineDTO.getExpirationDate());

            stockMovementLineRepository.save(line);
            lines.add(line);
        }

        URI uri = builder
                .path("/movimientoStock/{id}")
                .buildAndExpand(savedMovement.getId())
                .toUri();

        return ResponseEntity.created(uri).body(savedMovement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockMovement> update(@PathVariable Long id, @RequestBody StockMovement stockMovementAct) {
        return stockMovementRepository.findById(id)
                .map(stockMovementAnt -> {
                    stockMovementAct.setId(stockMovementAnt.getId());
                    stockMovementRepository.save(stockMovementAct);
                    return ResponseEntity.noContent().<StockMovement>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return stockMovementRepository.findById(id)
                .map(stockMovement -> {
                    stockMovementRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}