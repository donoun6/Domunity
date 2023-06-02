package hello.domunity.home.controller;

import hello.domunity.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("boardsQnA",boardService.HomeViewBoardByCategory("qna"));
        model.addAttribute("boardsCommunity",boardService.HomeViewBoardByCategory("community"));
        model.addAttribute("boardWorry",boardService.HomeViewBoardByCategory("worry"));
        model.addAttribute("boardStudy",boardService.HomeViewBoardByCategory("study"));

        return "index";
    }
}
