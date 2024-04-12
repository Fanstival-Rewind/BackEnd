package com.example.fanstivalv2.repository;

import com.example.fanstivalv2.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository <Song, Long> {
}
