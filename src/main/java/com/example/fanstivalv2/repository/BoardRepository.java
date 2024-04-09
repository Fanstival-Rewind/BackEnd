package com.example.fanstivalv2.repository;

import com.example.fanstivalv2.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByIdAndUserId(Long boardId, Long userId);
}
