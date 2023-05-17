package hello.domunity.member.controller;

import hello.domunity.member.domain.Member;
import hello.domunity.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("member", new Member());
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Member member, BindingResult bindingResult) {

        //검증 로직
        if (!StringUtils.hasText(member.getMemberId())) {
            bindingResult.addError(new FieldError("member", "memberId", member.getMemberId(), false, null, null, "필수 입력값 입니다."));
        }
        if (!StringUtils.hasText(member.getMemberId())) {
            bindingResult.addError(new FieldError("member", "mPasswd", member.getMemberPw(), false, null, null, "필수 입력값 입니다."));
        }
        if (!StringUtils.hasText(member.getMemberName())) {
            bindingResult.addError(new FieldError("member", "mName", member.getMemberName(), false, null, null, "필수 입력값 입니다."));
        }
        if (bindingResult.hasErrors()) {
            return "member/signup";
        }

        memberService.save(member);
        return "redirect:/domunity";
    }

    @GetMapping("/signin")
    public String signinForm(Model model) {
        model.addAttribute("member", new Member());
        return "member/signin";
    }

    @PostMapping("/signin")
    public String signin(@ModelAttribute Member member, BindingResult bindingResult) {
        //검증 로직
        if (!StringUtils.hasText(member.getMemberId())) {
            bindingResult.addError(new FieldError("member", "memberId", member.getMemberId(), false, null, null, "필수 입력값 입니다."));
        }
        if (!StringUtils.hasText(member.getMemberPw())) {
            bindingResult.addError(new FieldError("member", "mPasswd", member.getMemberPw(), false, null, null, "필수 입력값 입니다."));
        }
        if (bindingResult.hasErrors()) {
            return "member/signin";
        }

        return "member/signin";
    }

}
