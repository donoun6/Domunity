package hello.domunity.board.api;

import hello.domunity.board.domain.Board;
import hello.domunity.board.dto.BoardResponseDto;
import hello.domunity.board.service.BoardService;
import hello.domunity.security.MemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    /**
     * 게시물 등록 API
     * @param board
     * @param principal
     * @return
     */
    @PostMapping("/api/board")
    public BoardResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal MemberDetailsService principal) {
        boardService.save(board, principal.getMember());
        return new BoardResponseDto<>(HttpStatus.OK.value(), 1);
    }

    /**
     * 게시물 삭제 API
     * @param bid
     * @param principal
     * @return
     */
    @DeleteMapping("/api/board/{bid}")
    public BoardResponseDto<Integer> deleteById(@PathVariable int bid, Principal principal) {
        boardService.deleteBoard(bid);
        return new BoardResponseDto<>(HttpStatus.OK.value(), 1);
    }
}
