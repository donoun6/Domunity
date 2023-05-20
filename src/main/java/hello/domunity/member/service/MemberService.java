package hello.domunity.member.service;

import hello.domunity.member.domain.Member;

public interface MemberService {

    void save(Member member);

    Member findById(String memberId);

    Member findMember(String memberId);

}
