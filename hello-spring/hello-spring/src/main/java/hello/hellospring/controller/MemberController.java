package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//controller annotation을 통해 memberController의 객체를 하나 생성하고 spring이 객체를 들고있음 ->spring 컨테이너에서 spring bin을 통해 관리됨
@Controller
public class MemberController {

        private final MemberService memberService;

        @Autowired
        //컨트롤러 어노테이션을 명시했을 때 객체를 생성할 시에 생성자를 호출하므로
        //스프링이 스프링 컨테이너(스프링빈)에 등록되어있는 (객체)멤버서비스를 연결시켜 생성자의 인자로 넘겨줌줌
        public MemberController (MemberService memberService){
            //dependency  injection 의존성주입
            this.memberService =memberService;
        }
        @GetMapping("/members/new")
        public String createForm(){
            return "members/createMemberForm";
        }
        @PostMapping("/members/new")
        public String createForm(MemberForm form){
            Member member = new Member();
            member.setName(form.getName());
            memberService.join(member);
            return "redirect:/";
        }

        @GetMapping("/members")
        public  String list(Model model){
            List<Member> members =memberService.findMembers();
            model.addAttribute("members",members);
            return "members/memberList";
        }

}

