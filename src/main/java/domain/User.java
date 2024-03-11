package domain;


import domain.common.BaseEntity;
import domain.enums.Grade;
import domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String loginType;

    private String socialId;

    private String profileImage;

    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("1")
    private int status; //  0: 비활성화, 1: 활성

    private Role role;//enum type으로 가수, 팬, 둘 다로 생성

    private Grade grade;//enum type으로 등급 정하기

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;


    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Follower> followerList = new ArrayList<>();//follower로 좋아하는 가수 설정

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public void setProfileImage(String profileImage){
        this.profileImage = profileImage;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public void setGrade(Grade grade){
        this.grade = grade;
    }
}
