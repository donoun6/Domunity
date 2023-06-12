package hello.domunity.member.dao;

import hello.domunity.board.repository.CommentRepository;
import hello.domunity.member.domain.Member;
import hello.domunity.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@SpringBootTest
@Slf4j
class MemberDaoTest {

    @Autowired
    MemberDao memberDao;

    @Autowired
    MemberService memberService;

    @Autowired
    CommentRepository commentRepository;

    @TestConfiguration
    static class TestConfig {
        @Bean
        DataSource dataSource() {
            return new DriverManagerDataSource("jdbc:mysql://localhost:3306/domunication?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul", "domunication", "domunication");
        }

        @Bean
        PlatformTransactionManager transactionManager() {
            return new DataSourceTransactionManager(dataSource());
        }

    }

    @Test
    void save() {
        Member member = new Member();
        member.setMemberId("donoun6");
        member.setMemberPw("good123123");
        member.setMemberName("testname");
        memberDao.save(member);
    }


    @Test
    void hash() {
        String encPassword = new BCryptPasswordEncoder().encode("hashTest");
        System.out.println("encPassword = " + encPassword);
    }
}