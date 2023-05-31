package hello.domunity.board.api;

import hello.domunity.board.domain.Board;
import hello.domunity.board.dto.BoardResponseDto;
import hello.domunity.board.service.BoardService;
import hello.domunity.security.MemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public BoardResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal MemberDetailsService principal) {
        boardService.save(board, principal.getMember());
        return new BoardResponseDto<>(HttpStatus.OK.value(), 1);
    }
}
