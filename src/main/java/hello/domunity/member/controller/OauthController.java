package hello.domunity.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.domunity.member.domain.Member;
import hello.domunity.member.dto.KakaoProfile;
import hello.domunity.member.dto.OAuthToken;
import hello.domunity.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class OauthController {

    @Value("${cos.key}")
    private String cosKey;

    private final MemberService memberService;

    private final AuthenticationManager authenticationManager;
    /**
     * 카카오 로그인 처리
     * OAuth 인증 방식 처리
     */
    @RequestMapping("auth/kako/callback")
    public String kakaoCallback(String code) {

        //POST 방식으로 Kye = value 데이터를 요청
        //HttpURLConnection
        RestTemplate rt = new RestTemplate();

        //HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "194b59a12406399860a9b9b862d59bd2");
        params.add("redirect_uri", "http://localhost:8080/auth/kako/callback");
        params.add("code", code);

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        //Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        //Gson, Json Simple, ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;
        try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //토큰 요청에 대한 응답
//        return response.getBody();

        //HttpURLConnection
        RestTemplate rt2 = new RestTemplate();

        //HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);

        //Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        //Gson, Json Simple, ObjectMapper
        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Long id = kakaoProfile.getId();
        UUID garbagePassword = UUID.randomUUID();
        String nickname = kakaoProfile.getKakao_account().getEmail().split("@")[0];

        //카카오 엑세스 토큰으로 받은 회원 정보를 Member 객체에 재 설정
        Member kakaoMember = new Member();
        kakaoMember.setMemberId(String.valueOf(id));
        kakaoMember.setMemberPw(cosKey);
        kakaoMember.setMemberName(nickname);

        //가입자 혹은 비가입자 체크 처리
        Member originMember = memberService.findMember(kakaoMember.getMemberId());
        //비가입자 회원가입 처리
        if (originMember.getMemberId() == null) {
            memberService.save(kakaoMember);
        }

        //Spring Security 로그인 처리
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoMember.getMemberId(), cosKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }
}

