package hello.domunity.security;

import hello.domunity.member.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
 * 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
 */
public class MemberDetailsService implements UserDetails {
    private Member member;
    public MemberDetailsService(Member member) {
        this.member = member;
    }

    @Override
    public String getPassword() {
        return member.getMemberPw();
    }

    @Override
    public String getUsername() {
        return member.getMemberId();
    }

    //계정이 만료되지 않았는지 리턴(true 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있는지 리턴(true 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 만료되지 않았는지 리턴 (true 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정활성화(사용가능) 인지 리턴 (true 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    //계정 권한
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
