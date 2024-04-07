package com.example.fanstivalv2.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String location;
    private String price;//티켓 가격
    private String startDate;//시작 날짜
    private String endDate;//끝 날짜
    private String startTime;//공연 시작 시간
    private String endTime;//공연 끝나는 시간
}
