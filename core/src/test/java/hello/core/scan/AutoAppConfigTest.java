package hello.core.scan;

import hello.core.AutoAppconfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {
    @Test
    void basicScan(){
        //컼포넌트 스캔을 이용하기 위해 AppConfig가 아닌 AutoAppConfig를 등록해야한다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppconfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
