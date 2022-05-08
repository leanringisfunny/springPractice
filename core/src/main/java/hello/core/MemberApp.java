package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;


public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        Member member1 = new Member(1L, Grade.VIP,"memberA");

        memberService.join(member1);
        Member findmember =memberService.findMember(1L);
        System.out.println(" new member1 = " + member1.getName());
        System.out.println("findmember= " +findmember.getName());


    }
}
