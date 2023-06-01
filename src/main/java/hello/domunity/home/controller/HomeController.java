package hello.domunity.home.controller;

import hello.domunity.board.domain.Board;
import hello.domunity.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;
    /**
     * 메인 화면
     */
    @GetMapping
    public String homePage(Model model) {
        List<Board> boardsQnA = boardService.viewBoardByCategory("QnA");
        List<Board> boardsCommunity = boardService.viewBoardByCategory("커뮤니티");
        List<Board> boardWorry = boardService.viewBoardByCategory("고민");
        List<Board> boardStudy = boardService.viewBoardByCategory("스터디");

        System.out.println("boardStudy = " + boardStudy);
        model.addAttribute("boardsQnA",boardsQnA);
        model.addAttribute("boardsCommunity",boardsCommunity);
        model.addAttribute("boardWorry",boardWorry);
        model.addAttribute("boardStudy",boardStudy);

        return "index";
    }
}
