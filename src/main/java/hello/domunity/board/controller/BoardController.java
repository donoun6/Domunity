package hello.domunity.board.controller;

import hello.domunity.board.domain.Board;
import hello.domunity.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
