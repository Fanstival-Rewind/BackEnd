package com.example.fanstivalv2.repository;

import com.example.fanstivalv2.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
