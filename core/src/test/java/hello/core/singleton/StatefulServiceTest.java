package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
    @Test
    @DisplayName("")
    void statefulServiceSingleton(){
    ApplicationContext ac = new AnnotationConfigApplicationContext(Testconfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        //Thread1 :A사용자가 10000원 주문을 했다.
        int userAPrice = statefulService1.order("userA", 10000);
        //Thread2 :B사용자가 25000원 주문을 했다.
        int userBPrice = statefulService2.order("userB", 25000);
        //int price =statefulService1.getPrice();
        //System.out.println("price = " + userAPrice);

    }

    static class Testconfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }

    }
}