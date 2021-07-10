package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //Spring data jpa는 인터페이스 이름만으로도 쿼리 대체 가능
    //findBy~ 등
    @Override
    Optional<Member> findByName(String name);
}
