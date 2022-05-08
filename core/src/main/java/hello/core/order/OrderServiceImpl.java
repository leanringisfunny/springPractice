package hello.core.order;

import hello.core.Discount.DiscountPolicy;
import hello.core.Discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberrepository;
    private final DiscountPolicy discountpolicy;

    public OrderServiceImpl(MemberRepository memberrepository, DiscountPolicy discountpolicy) {
        this.memberrepository = memberrepository;
        this.discountpolicy = discountpolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member=memberrepository.findById(memberId);
        int discountPrice= discountpolicy.discount(member,itemPrice);
        return new Order(memberId,itemName,itemPrice, discountPrice);

    }
}
