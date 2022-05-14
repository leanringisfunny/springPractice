package hello.core.BeanFind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicatinContextInfoTest {
   AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig .class);
   @Test
   @DisplayName("스프링 빈 출력하기")
    void findAllBean(){
       String[] beanDefinitionNames = ac.getBeanDefinitionNames();
       for (String beanDefinitionName : beanDefinitionNames) {
           Object bean = ac.getBean(beanDefinitionName);
           System.out.println("beanDefinitionName = " + beanDefinitionName+ "   bean ="+ bean);
       }
   }
    @Test
    @DisplayName("스프링 빈애플리케이션 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition =ac.getBeanDefinition(beanDefinitionName);
                    //BeanDefinition.ROLE_APPLICATION:개발자가 직접 등록한 빈 (객체)
                        //BeanDefinition.ROLE_INFRASTRUCTURE 스프링이 스프링 컨테이너 내부에서 사용하는 빈
            if(beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("beanDefinitionName = " + beanDefinitionName+ "   bean ="+ bean);
            }
        }
    }
}
