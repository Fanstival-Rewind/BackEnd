package domain;

import domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

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

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
