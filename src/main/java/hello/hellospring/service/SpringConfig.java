package hello.hellospring.service;


import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;


@Configuration
public class SpringConfig {
    //DataSource는 데이터베이스 커넥션을 획득할 떄 사용하는 객체

//    private final DataSource dataSource;
//    private final EntityManager em;
    private final MemberRepository memberRepository;

    //스프링 데이터가 jpa가 springDataMemberRepository를 스프링 빈으로 자동 등록
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
//    @Bean
//    public MemberRepository memberRepository() {
//// return new MemoryMemberRepository();
//// return new JdbcMemberRepository(dataSource);
//// return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
