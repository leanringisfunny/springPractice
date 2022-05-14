package hello.core;

import hello.core.Discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//스프링 빈 등록시 해당 애노테이션을 지정해 그것이 적용된 클래스를제외할 수 있음
//basePackages를 통해 탐색 위치를 지정할 수 있다. 등록 위치부터 하위에 등록된 파일들을 모두 가져온다. 여러개를 지정해 가지고 오고 싶은 컴포넌트만 가져올 수도 있다->등록 범위 지정 가능
@Configuration
@ComponentScan(
     basePackages ="hello.core" ,
    excludeFilters=@ComponentScan.Filter(type= FilterType.ANNOTATION,classes=Configuration.class)
)
public class AutoAppconfig {
/*
    @Autowired
    MemberRepository memberRepository;
    @Autowired DiscountPolicy discountPolicy;
    //자동 빈 외에 수동으로 직접 빈 등록시 Autowired사용해서 생성자 주입가능
    @Bean
    OrderService orderService2() {
        return new OrderServiceImpl(memberRepository,discountPolicy);
    }
    */
}
