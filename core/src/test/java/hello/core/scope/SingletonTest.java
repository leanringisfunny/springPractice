package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {
    @Test
    void  singletonBeanFind(){
        //스프링 컨테이너생성 시점에 초기화가 실행이된다.(생성과 함께 ) 물론 싱글통이기 떄문에 init은 기에 한번만 실행이된다,
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);//자동으로 컴포넌트 스캔이 일어나 컴포넌트 등록이 일어남
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        Assertions.assertThat(singletonBean1).isEqualTo(singletonBean2);
        ac.close();
    }
    @Scope("singleton")
    static class SingletonBean{
        @PostConstruct
        public void init(){
            System.out.println("singleton: postContruct");
        }
        @PreDestroy
        public void destroy(){
            System.out.println(" singleton:preDestroy " );
        }
    }
}
