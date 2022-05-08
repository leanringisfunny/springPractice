package hello.core.Discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    int discountFixAmount=1000; //할인 금액 1000원
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade()== Grade.VIP){
            return discountFixAmount;
        }
        else{
            return 0;
        }
    }


}
