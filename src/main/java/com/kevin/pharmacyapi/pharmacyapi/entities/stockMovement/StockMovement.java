package com.kevin.pharmacyapi.pharmacyapi.entities.stockMovement;
import com.kevin.pharmacyapi.pharmacyapi.config.audit.Auditable;
import com.kevin.pharmacyapi.pharmacyapi.entities.sale.Sale;
import com.kevin.pharmacyapi.pharmacyapi.entities.supplier.Supplier;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "stock_movement")
public class StockMovement extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = true)
    private Supplier supplier;

    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal total;

    @OneToOne
    @JoinColumn(name = "sale_id", nullable = true)
    private Sale sale;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MovementType movementType;

}