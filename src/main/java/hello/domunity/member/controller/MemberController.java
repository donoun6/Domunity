package hello.domunity.member.controller;

import hello.domunity.member.domain.Member;
import hello.domunity.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입 폼 화면
     * @param model Member 객체 담기
     * @return
     */
    @GetMapping("auth/signup")
    public String signupForm(Model model) {
        model.addAttribute("member", new Member());
        return "member/signup";
    }

    /**
     * 회원가입
     * @param bindingResult 검증 로직 수행
     */
    @PostMapping("auth/signup")
    public String signup(@ModelAttribute Member member, BindingResult bindingResult) {
        //검증 로직
        if (!StringUtils.hasText(member.getMemberId())) {
            bindingResult.addError(new FieldError("member", "memberId", member.getMemberId(), false, null, null, "필수 입력값 입니다."));
        }
        if (!StringUtils.hasText(member.getMemberId())) {
            bindingResult.addError(new FieldError("member", "memberPw", member.getMemberPw(), false, null, null, "필수 입력값 입니다."));
        }
        if (!StringUtils.hasText(member.getMemberName())) {
            bindingResult.addError(new FieldError("member", "memberName", member.getMemberName(), false, null, null, "필수 입력값 입니다."));
        }
        if (bindingResult.hasErrors()) {
            return "member/signup";
        }
        
        //가입 중복 체크
        Member checkMember = memberService.findMember(member.getMemberId());
        if (checkMember.getMemberId() != null) {
            bindingResult.addError(new FieldError("member", "memberId", member.getMemberId(), false, null, null, "해당 아이디는 등록된 아이디입니다."));
            return "member/signup";
        }
        if (checkMember.getMemberName() != null) {
            bindingResult.addError(new FieldError("member", "memberName", member.getMemberId(), false, null, null, "해당 별명은 등록된 별명입니다."));
            return "member/signup";
        }

        memberService.save(member);
        return "redirect:/";
    }

    /**
     * 로그인 폼 화면
     * @param model Member 객체 담기
     */
    @GetMapping("auth/signin")
    public String signinForm(@ModelAttribute Member member, BindingResult bindingResult,
                            @RequestParam(value = "error", required = false)String error,
                             @RequestParam(value = "exception", required = false)String exception,
                             Model model) {
        if (error != null && exception != null) {
            bindingResult.addError(new ObjectError( "member",null,null, "해당 정보를 찾을 수 없습니다."));
            bindingResult.addError(new FieldError("member", "memberId", member.getMemberId(), false, null, null, null));
            bindingResult.addError(new FieldError("member", "memberPw", member.getMemberPw(), false, null, null, null));
        }
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        model.addAttribute("member", new Member());

        return "member/signin";
    }

    /**
     * 로그아웃
     * 세션,쿠키 초기화
     */
    @GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("memberId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        HttpSession session = request.getSession(false);
        session.invalidate(); //세션 무효화
        return "redirect:/";
    }

}
