package domain;

import domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Lob
    private String content;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public void setContent(String content) {
        this.content = content;
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
