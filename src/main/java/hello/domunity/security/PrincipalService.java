package hello.domunity.security;

import hello.domunity.member.domain.Member;
import hello.domunity.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    //스프링이 로그인 요청을 가로챌때 username password 변수를 가로챈다
    //password 부분 처리는 알아서 한다.
    //username이 DB에 있는지만 확인하면 된다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member principal = memberRepository.findByMemberId(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다." + username);
                });
        return new MemberDetailsService(principal); //시큐리티 세션에 유저정보가 저장된다.
    }
}
