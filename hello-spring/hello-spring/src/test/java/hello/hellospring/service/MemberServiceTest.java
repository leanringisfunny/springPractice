package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;// 스테틱 임포트로 넘김(Assertions생략)

class MemberServiceTest {
    MemberService memberService; //= new MemberService();
    MemoryMemberRepository memberRepository;//=new MemoryMemberRepository();

    @BeforeEach
    void bforeEach(){
        memberRepository =new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);
    }

    @AfterEach
      void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {//join검증
        //given
        Member member =new Member();

        //when
        member.setName("쿵쾅");
        memberService.join(member);
        //then

        Long memberId= member.getId();
        Member result= memberService.findOne( memberId ).get();
        assertThat(result.getName()).isEqualTo(member.getName());


    }
    @Test
    void 중복_회원_예외(){
        //given
        Member member1 =new Member();
        member1.setName("쿵쾅");
        Member member2 =new Member();
        member2.setName("쿵쾅");

        //when
        memberService.join(member1);
        //then
        IllegalStateException e=assertThrows(IllegalStateException.class,()->memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
        /*
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
        }
         */

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}