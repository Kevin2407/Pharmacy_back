package com.kevin.pharmacyapi.pharmacyapi.stockMovement;
import com.kevin.pharmacyapi.pharmacyapi.config.audit.Auditable;
import com.kevin.pharmacyapi.pharmacyapi.product.Product;
import com.kevin.pharmacyapi.pharmacyapi.stockMovement.sale.Sale;
import com.kevin.pharmacyapi.pharmacyapi.stockMovement.supplier.Supplier;
import com.kevin.pharmacyapi.pharmacyapi.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
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
    private MovementType movement_type;

}