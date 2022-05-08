package hello.hellospring.Repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.JsonPathAssertions;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;


public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository= new MemoryMemberRepository();
    @AfterEach
     public void afterEach(){
        repository.clearStore();
    }



    @Test
     void save(){
        Member member = new Member();

        member.setName("spring!!!");
        repository.save(member);
        Member result= repository.findById(member.getId()).get();
        System.out.println("result="+(result==member));
        assertThat(member).isEqualTo(member);
    }

    @Test
    void findByName(){
        Member member1 =new Member();
        member1.setName("spring!!");
        repository.save(member1);

        Member member2 =new Member();
        member1.setName("spring!!!");
        repository.save(member2);
         Member result= repository.findByName("spring!!!").get();
         assertThat(result).isEqualTo(member1);
    }
    @Test
        void  findAll(){
        Member member1 =new Member();
        member1.setName("spring!!");
        repository.save(member1);

        Member member2 =new Member();
        member1.setName("spring!!!");
        repository.save(member2);

        assertThat(repository.findAll().size()).isEqualTo(2);
    }


}
