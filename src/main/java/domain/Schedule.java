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
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    private String title;

    @Lob
    private String description;

    private String location;

    private Timestamp startTime;

    private Timestamp endTime;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public void setLocation(String location) {
        this.location = location;
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
