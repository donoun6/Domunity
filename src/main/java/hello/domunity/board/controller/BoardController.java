package hello.domunity.board.controller;

import hello.domunity.board.domain.Board;
import hello.domunity.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/saveForm")
    public String saveForm(Model model){
        model.addAttribute("board",new Board());
        return "board/saveForm";
    }

    @GetMapping("/view/{bid}")
    public String boardView(@PathVariable int bid, Model model) {
        model.addAttribute("board", boardService.viewBoard(bid));
        return "board/detail";
    }

    @GetMapping("/list/{category}")
    public String boardList(@PathVariable String category, Model model) {
        model.addAttribute("category", category);
        model.addAttribute("boardView", boardService.viewBoardByCategory(category));
        return "board/boardList";
    }

}
