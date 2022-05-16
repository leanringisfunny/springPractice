package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class BeanLifecycle {
    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient networkClient = ac.getBean(NetworkClient.class);
        ac.close();//스프링 컨테이너 폐기
    }

    static class LifeCycleConfig{
        @Bean//(initMethod = "init",destroyMethod="close")
        NetworkClient newtworkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }

    }
}
