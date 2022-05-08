package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//@Service @Repository @Controller는 @Component를 상속받은 Annotation이므로 호출 시 스프링 빈에 등록

//spring이 spring container에 spring bin을 등록 시 싱글톤으로 등록(각각의 클래스 종류들에 대해  객체는 하나가 되도록 한다)
//유일하게 하나만 등록 후 공유

//@Service//스프링이 멤버서비스 클래스를 스프링 컨테이너에 등록시켜줌 다른 컨트롤러에서 객체 생성시 생성자로 넣을 수 있도록 해줌
public class MemberService {
    private final  MemberRepository memberRepository;  //=new MemoryMemberRepository();

  //  @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    public Long join(Member member){
        //같은 이름이 있는 중복회원 허용 x
        //Optional<Member> result = memberRepository.findByName(member.getName());
        //result.ifPresent(m->throw new IllegalStateException("이미 존재하는 이름입니다."));
        ValidateDuplicateMember(member);


        memberRepository.save(member);
        return member.getId();
    }
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long MemberId){
        return memberRepository.findById(MemberId);
    }

    private void ValidateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        });
    }
}
