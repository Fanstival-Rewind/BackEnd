package com.example.fanstivalv2.domain;

import com.example.fanstivalv2.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
