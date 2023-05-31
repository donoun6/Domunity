package hello.domunity.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

//    @PostMapping("/saveForm")
//    public String save(){
//    }
}
