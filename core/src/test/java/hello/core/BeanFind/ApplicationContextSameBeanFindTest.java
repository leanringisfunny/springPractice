package hello.core.BeanFind;

import hello.core.AppConfig;
import hello.core.Discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(sameBeanConfig.class);
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘이상 있으면 중복오류가 발생한다.")
    void findBeanByTypeDuplicate(){
        //ac.getBean(MemberRepository.class);
        assertThrows(NoUniqueBeanDefinitionException.class,()->
                ac.getBean(MemberRepository.class));
    }
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘이상 있으면 중복오류가 발생한다.->빈이름 지정으로 해결한다.")
    void findBeanByTypeDuplicatefixed(){
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입 모두 조회하기.")
    void findAllBeanBy(){

        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key   = " + key+"value  = "+beansOfType.get(key) );
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }


    //(static)내부 클래스를 사용한다는 것은 내부 클래스(스콥)을 클래스(파일) 안에서만 사용한다는 의미이다.
    @Configuration
    static class sameBeanConfig {
        @Bean
        MemberRepository memberRepository1(){
            return new MemoryMemberRepository();

        }
        @Bean
        MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
