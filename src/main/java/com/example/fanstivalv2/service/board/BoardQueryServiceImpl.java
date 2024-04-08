package com.example.fanstivalv2.service.board;

import com.example.fanstivalv2.converter.BoardConverter;
import com.example.fanstivalv2.domain.Board;
import com.example.fanstivalv2.repository.BoardRepository;
import com.example.fanstivalv2.repository.UserRepository;
import com.example.fanstivalv2.service.user.UserService;
import com.example.fanstivalv2.web.dto.board.BoardRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardQueryServiceImpl{
}
