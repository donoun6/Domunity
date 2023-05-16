package hello.domunity.member.controller;

import hello.domunity.member.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("member", new Member());
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Member member) {
        System.out.println("member = " + member);
        return "index";
    }
}
