package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);//자동으로 컴포넌트 스캔이 일어나 컴포넌트 등록이 일어남
        System.out.println(" find prototypeBean1->조회 직전에 프로토타입 빈 생성");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);// 스프링 컨테이너에서 빈을 조회할때 생성이된다.,이때 초기화메서드도 실행
        System.out.println(" find prototypeBean2->조회 직전에 프로토타입 빈 생성");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);


        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        //클라이언트 단위에서 빈을 닫는다.
        prototypeBean2.destroy();
        prototypeBean1.destroy();
        ac.close();//-->컨테이너가 종료되도 빈은 클로즈되지 않는다.
    }


    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println(" proto:postContruct");
        }
        @PreDestroy//->호출이 안된다. (스프링 컨테이너가 아닌 클라이언트에서 관리하기때문)
        public void destroy(){
            System.out.println(" proto:preDestroy " );
        }
    }
}
