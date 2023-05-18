package hello.domunity.member.service;

import hello.domunity.member.dao.MemberDao;
import hello.domunity.member.domain.Member;
import hello.domunity.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MembewrServiceimpl implements MemberService{

    private final MemberDao memberDao;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;


    @Override
    public void save(Member member) {
        String rawPassword = member.getMemberPw(); //평문
        String encPassword = encoder.encode(rawPassword);//해시 암호화
        member.setMemberPw(encPassword);
        memberDao.save(member);
    }

    @Override
    public Member findById(String memberId) {
        return memberDao.findById(memberId);
    }

//    public Member login(Member member) {
//        return memberRepository.findByMemberIdAndMemberPw(member.getMemberId(), member.getMemberPw());
//    }

}
