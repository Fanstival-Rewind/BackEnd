package domain;

import domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Follower extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followed_id", referencedColumnName = "followed_id")
    private User followed;

    @Builder.Default
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private List<Follower> followerList = new ArrayList<>();

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public void setFollowed(User followed) {
        this.followed = followed;
    }
}
