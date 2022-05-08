package hello.core.member;
import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MemberServiceTest {

    MemberService memberService;
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig=new AppConfig();
        MemberService memberService = appConfig.memberService();
    }
    @Test
    void join() {
    //givien
    Member member1 = new Member(1L, Grade.VIP, "memberA");
    //when
    memberService.join(member1);
    Member findmember= memberService.findMember(1L);
    //then

    Assertions.assertThat(member1).isEqualTo(findmember);
    }
}
