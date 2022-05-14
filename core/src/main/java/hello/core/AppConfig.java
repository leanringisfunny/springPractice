package hello.core;

import hello.core.Discount.DiscountPolicy;
import hello.core.Discount.FixDiscountPolicy;
import hello.core.Discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration  //스프링으로 구성 하도록 도와주는 어노테이션
public class AppConfig {

    //memberService->new MemoryMemberRepository();
    //orderService->new MmemoryMemberRepository();
    //2번 호출한다면 싱글톤이 깨질까?


    //AppConfig에서 객체의 생성과 연결을 담당한다.
    @Bean//스프링 컨테이너에 등록
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());

    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    @Bean                                           //생성자 주입: 생성자를 통해 객체가 생성될때 appConfig에서 생성한 인스턴스를 주입
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(),discountPolicy());

    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
       // return new RateDiscountPolicy();
    }

}
