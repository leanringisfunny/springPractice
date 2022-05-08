package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    //jpa는 EntityManeger로 항상 동작(데이터 소스를 내부적으로 들고 있어서 db와 내부적으로 data 다 처리)
    private final EntityManager em;


    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
         em.persist(member);
         return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member= em.find(Member.class,id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
