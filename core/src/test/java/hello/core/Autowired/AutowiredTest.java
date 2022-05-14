package hello.core.Autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void autowiredOption(){
       ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }
    static class TestBean{
        @Autowired(required=false)
        public void NoSetBean1(Member member){
            System.out.println("member = " + member);
        }
        @Autowired
        public void NoSetBean2(@Nullable Member member){
            System.out.println("member = " + member);
        }
        @Autowired
        public void NoSetBean3(Optional< Member> member){
            System.out.println("member = " + member);
        }
    }
}
