package com.example.fanstivalv2.domain;

import com.example.fanstivalv2.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    private String title;

    @Lob
    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = LocalDateTime.now();
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = LocalDateTime.now();
    }
}
