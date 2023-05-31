package hello.domunity.board.controller;

import hello.domunity.board.domain.Board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/saveForm")
    public String saveForm(Model model){
        model.addAttribute("board",new Board());
        return "board/saveForm";
    }

}
