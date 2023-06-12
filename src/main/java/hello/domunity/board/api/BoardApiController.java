package hello.domunity.board.api;

import hello.domunity.board.domain.Board;
import hello.domunity.board.domain.Comment;
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

    /**
     * 댓글 등록 API
     * 데이터를 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다.
     * dto 사용하지 않은 이유는 프로젝트가 작기 때문이다. 큰 프로젝트에서는 dto를 사용하자
     * Ajax로 필요한 데이터를 객체형태로 모두 받은 후
     * Dto클래스를 따로 만들어 필요한 데이터만 domain형태로 만들어 주면 된다.
     * @param bid
     * @param comment
     * @param principal
     * @return
     */
    @PostMapping("/api/board/{bid}/comment")
    public BoardResponseDto<Integer> commentSave(@PathVariable int bid, @RequestBody Comment comment, @AuthenticationPrincipal MemberDetailsService principal) {
        boardService.saveComment(principal.getMember(), bid, comment);
        return new BoardResponseDto<>(HttpStatus.OK.value(), 1);
    }

}
