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
import com.example.fanstivalv2.web.dto.board.BoardRequestDto;
import com.example.fanstivalv2.web.dto.board.BoardResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardCommandServiceImpl implements BoardCommandService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    @Transactional
    public Board createBoard(HttpServletRequest httpServletRequest, BoardRequestDto.CreateBoardDto request){

        Board board = BoardConverter.toBoard(request);
        //email 가져오는 메서드
        String accessToken = jwtService.extractAccessToken(httpServletRequest)
                .orElseThrow(() -> new RuntimeException("액세스 토큰이 누락되었거나 잘못되었습니다."));
        String email = jwtService.extractEmail(accessToken)
                .orElseThrow(() -> new RuntimeException("액세스 토큰에서 이메일을 추출할 수 없습니다."));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        board.setUser(user);

        return boardRepository.save(board);
    }

    @Override
    @Transactional
    public Board updateBoard(HttpServletRequest httpServletRequest, Long id, BoardRequestDto.UpdateBoardDto request){

        String accessToken = jwtService.extractAccessToken(httpServletRequest)
                .orElseThrow(() -> new RuntimeException("액세스 토큰이 누락되었거나 잘못되었습니다."));
        String email = jwtService.extractEmail(accessToken)
                .orElseThrow(() -> new RuntimeException("액세스 토큰에서 이메일을 추출할 수 없습니다."));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        Board board = boardRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new BoardHandler(ErrorStatus.BOARD_NOT_FOUND));

        if(request.getTitle() != null){
            board.setTitle(request.getTitle());
        }
        if(request.getContents() != null){
            board.setContent(request.getContents());
        }
        board.setUpdatedAt(LocalDateTime.now());
        return board;
    }

    @Override
    @Transactional
    public BoardResponseDto.DeleteBoardResultDTO deleteBoard(HttpServletRequest httpServletRequest, Long id){
        String accessToken = jwtService.extractAccessToken(httpServletRequest)
                .orElseThrow(() -> new RuntimeException("액세스 토큰이 누락되었거나 잘못되었습니다."));
        String email = jwtService.extractEmail(accessToken)
                .orElseThrow(() -> new RuntimeException("액세스 토큰에서 이메일을 추출할 수 없습니다."));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        Board board = boardRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new BoardHandler(ErrorStatus.BOARD_NOT_FOUND));

        BoardResponseDto.DeleteBoardResultDTO deleteBoardResultDTO = BoardConverter.toDeleteResultDTO(board);

        boardRepository.delete(board);

        return deleteBoardResultDTO;
    }
}
