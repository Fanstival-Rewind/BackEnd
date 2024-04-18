package com.example.fanstivalv2.repository;

import com.example.fanstivalv2.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository <Comment, Long> {
}
