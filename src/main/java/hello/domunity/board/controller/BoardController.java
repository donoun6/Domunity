package hello.domunity.board.controller;

import hello.domunity.board.domain.Board;
import hello.domunity.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    

    /**
     * 게시물 등록 Form
     * @param model
     * @return
     */
    @GetMapping("/saveForm")
    public String saveForm(Model model){
        model.addAttribute("board",new Board());
        return "board/saveForm";
    }

    /**
     * 게시물 상세 보기
     * @param bid
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/view/{bid}")
    public String boardView(@PathVariable int bid, Model model, Principal principal) {
        model.addAttribute("principalName", principal.getName());
        Board board = boardService.viewBoard(bid);
        model.addAttribute("board", boardService.viewBoard(bid));
        return "board/detail";
    }

    /**
     * 카테고리별 게시물 보기
     * @param category
     * @param model
     * @param pageable
     * @return
     */
    @GetMapping("/list/{category}")
    public String boardList(@PathVariable String category, Model model , @PageableDefault(size = 10, sort = "bid", direction = Sort.Direction.DESC)Pageable pageable) {
        model.addAttribute("category", category);
        model.addAttribute("boardView", boardService.viewBoardByCategory(category,pageable));
        return "board/boardList";
    }


}
