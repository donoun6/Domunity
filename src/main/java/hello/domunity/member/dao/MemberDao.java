package hello.domunity.member.dao;

import hello.domunity.member.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.Transactional;

@Repository
@Transactional
public class MemberDao {

    private final EntityManager em;
    private final JdbcTemplate template;

    public MemberDao(EntityManager em, DataSource dataSource) {
        this.em = em;
        this.template = new JdbcTemplate(dataSource);
    }

    /**
     * 회원 저장
     */
    public void save(Member member) {
        em.persist(member);
    }

//    private RowMapper<Member> memberRowMapper() {
//        return (rs, rowNum) -> {
//            Member member = new Member();
//            member.setMemberId(rs.getString("member_Id"));
//            member.setMemberPw(rs.getString("member_Pw"));
//            member.setMemberName(rs.getString("member_Name"));
//            return member;
//        };
//    }
}
