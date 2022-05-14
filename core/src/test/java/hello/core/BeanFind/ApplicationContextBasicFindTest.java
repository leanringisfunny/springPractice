package hello.core.BeanFind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig .class);
    @Test
    @DisplayName("빈 이름으로 조회하기")
    void findBeanByName(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            MemberService memberService = ac.getBean("memberService", MemberService.class);
            System.out.println("memberService = " + memberService);
            System.out.println("memberService.getClass() = " + memberService.getClass());
            Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);


        }
    }

    @Test
    @DisplayName("빈 타입으로 조회하기")
    void findBeanByType(){

            MemberService memberService = ac.getBean( MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("빈 이름으로 조회 실패")
    void findBeanByNameX(){
       // MemberService xxxxxx = ac.getBean("xxxxxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class,()->
                ac.getBean("xxxxxx", MemberService.class));
    }
}
