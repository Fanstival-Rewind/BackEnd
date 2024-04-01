package domain;

import domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
    private User seller;

    private String name;

    @Lob
    private String description;

    private BigDecimal price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
        this.updatedAt = LocalDateTime.now();
    }
}
