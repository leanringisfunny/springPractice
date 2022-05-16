package hello.core.order;

import hello.core.Annotation.MainDiscountPolicy;
import hello.core.Discount.DiscountPolicy;
import hello.core.Discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor//->lombok이 final이 붙은 멤버에 대해 생성자를 만들어준다.
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final  DiscountPolicy discountpolicy;

    //컴포넌트 스캔시 자동 의존관계주입이 필요하다. 스프링 빈에 직접 등록시 의존관계를 명시하는 과정이 있었으나 컴포넌트 스캔을 이용하면서 그 과정을 거칠수
    //없기 떄문에 생성자에 붙이는 AutoWired애노테이션으로 의존관계를 주입해준다.component로  등록된 구현체중에 연결할 수 있는것을


    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountpolicy) {
        this.memberRepository = memberRepository;
        this.discountpolicy = discountpolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member=memberRepository.findById(memberId);
        int discountPrice= discountpolicy.discount(member,itemPrice);
        return new Order(memberId,itemName,itemPrice, discountPrice);

    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
