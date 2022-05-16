package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singtonClientUsePrototyprBean()
    {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean1.logic();
        Assertions.assertThat(count2).isEqualTo(1);

    }
    @Scope("singleton")
    //@RequiredArgsConstructor
    //싱글톤 호출시 계속 새로운 프로ㅗ토타입빈이 주입된도록한다

    static class ClientBean{
        //private final PrototypeBean prototypeBean; //생성시점에 이미 주입되어있음

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        /*@Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }*/
        @Autowired
        public ClientBean( Provider prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        int logic(){
            //prototypeBean.addCount();
            //return prototypeBean.getCount();
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return   prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count=0;
        public void addCount(){
            count++;
        }

        public int getCount() {
            return count;
        }
        @PostConstruct
        void init(){
            System.out.println("prototypeBean = " + this);
        }
        @PreDestroy
        void close(){
            System.out.println(" prototype:close "  );
        }


    }

}
