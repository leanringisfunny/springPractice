package hello.core.Discount;



import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    DiscountPolicy rateDiscountPolicy=new RateDiscountPolicy();
    @Test
    @DisplayName("vip는 10% 할인받아야 합니다")
    public void vip_o(){
        Member member=new Member(1L, Grade.VIP,"holly");
        int discount= rateDiscountPolicy.discount(member,10000);
        Assertions.assertThat(discount).isEqualTo(1000);
    }
    @Test
    @DisplayName("BASIC은 할인이 없습니다.")
    public void vip_x(){
        Member member=new Member(2L, Grade.BASIC,"hollyshit");
        int discount= rateDiscountPolicy.discount(member,10000);
        Assertions.assertThat(discount).isEqualTo(0);
    }
}