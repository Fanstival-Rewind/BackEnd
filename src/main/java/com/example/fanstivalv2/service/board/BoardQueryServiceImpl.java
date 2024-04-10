package com.example.fanstivalv2.service.board;

import com.example.fanstivalv2.apiPayload.code.status.ErrorStatus;
import com.example.fanstivalv2.apiPayload.exception.handler.BoardHandler;
import com.example.fanstivalv2.apiPayload.exception.handler.UserHandler;
import com.example.fanstivalv2.converter.BoardConverter;
import com.example.fanstivalv2.domain.Board;
import com.example.fanstivalv2.domain.User;
import com.example.fanstivalv2.jwt.jwtservice.JwtService;
import com.example.fanstivalv2.repository.BoardRepository;
import com.example.fanstivalv2.repository.UserRepository;
import com.example.fanstivalv2.service.user.UserService;
import com.example.fanstivalv2.web.dto.board.BoardRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardQueryServiceImpl implements BoardQueryService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public Board getBoard(HttpServletRequest httpServletRequest, Long id){
        String accessToken = jwtService.extractAccessToken(httpServletRequest)
                .orElseThrow(() -> new RuntimeException("액세스 토큰이 누락되었거나 잘못되었습니다."));
        String email = jwtService.extractEmail(accessToken)
                .orElseThrow(() -> new RuntimeException("액세스 토큰에서 이메일을 추출할 수 없습니다."));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        Board board = boardRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new BoardHandler(ErrorStatus.BOARD_NOT_FOUND));

        return board;
    }

    @Override
    public List<Board> getBoardList(HttpServletRequest httpServletRequest){
        String accessToken = jwtService.extractAccessToken(httpServletRequest)
                .orElseThrow(() -> new RuntimeException("액세스 토큰이 누락되었거나 잘못되었습니다."));
        String email = jwtService.extractEmail(accessToken)
                .orElseThrow(() -> new RuntimeException("액세스 토큰에서 이메일을 추출할 수 없습니다."));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        List <Board> boardList = boardRepository.findAllByUserId(user);
        return boardList;
    }
}
