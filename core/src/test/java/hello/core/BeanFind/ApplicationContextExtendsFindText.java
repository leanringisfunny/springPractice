package hello.core.BeanFind;

import hello.core.AppConfig;
import hello.core.Discount.DiscountPolicy;
import hello.core.Discount.FixDiscountPolicy;
import hello.core.Discount.RateDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindText {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(configTest.class);
    @Test
    @DisplayName("부모타입조회시 자식이 2이상 있으면 중복오류 발생")
    void findBeanByParentTypeDuplicate(){
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class,()->
                 ac.getBean(DiscountPolicy.class));
    }
    @Test
    @DisplayName("부모타입조회시 자식이 2이상 있으면 중복오류 발생->이름을 지정함으로써 해결가능")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모타입으로 모든 하위 빈들 조회하기")
    void findAllBeansByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key+"  value= "+beansOfType.get(key));
        }
        assertThat(beansOfType.size()).isEqualTo(2);
    }
    @Test
    @DisplayName("부모타입으로 모든 하위 빈들 -Object")
    void findAllBeansByObjectType(){
        Map<String,Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "  value= " + beansOfType.get(key));
        }
    }

    @Configuration
    static class configTest{
       @Bean
        public  DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }
        @Bean
       public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }

    }
}
