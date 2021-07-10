package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    //회원 가입

    public Long join(Member member){
        //같은 이름이 있는 중복 회원 x
//        Optional<Member> result = memberRepository.findByName(member.getName());
////        //(이름이) 이미 존재하는 경우 (Optional인 경우)
////        result.ifPresent(member1 -> {
////            try {
////                throw new IllegalAccessException("이미 존재하는 회원입니다.");
////            } catch (IllegalAccessException e) {
////                e.printStackTrace();
////            }
////        });
//        long start=System.currentTimeMillis();
        try{
            validateDuplicateMember(member); //증복회원 검증

            memberRepository.save(member);
            return member.getId();
        }finally{
//            long finish=System.currentTimeMillis();
//            long timeMs=finish-start;
//            System.out.println("jpin="+timeMs+"ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                . ifPresent(member1 -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
