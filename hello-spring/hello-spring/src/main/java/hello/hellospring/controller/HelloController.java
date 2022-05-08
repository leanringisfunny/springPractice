package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    //@GetMappting()으로 url연결
    @GetMapping("hello")
    public String hello (Model model){
        model.addAttribute("data","hello!!");
        return "hello"; //hello이름의 html파일을 리졸버가 찾음
    }

    //params도 같이 넘겨주기
    @GetMapping("hello-mvc")    //파람스의 키값은 name으로 설정, String 형의 name을 매개 변수로 받음
    public String helloMvc(@RequestParam(value="name",required=false)String name,Model model){
        //모델에 view에서 렌더링할 데이터를 담아서 랜더링할 html파일에 보내준다.
        model.addAttribute("name",name);  //hello-template에 name을 키값으로서 name값을 데이터로 전해줌
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody//네트워크에서 http통신 요청의 body부에 직접 return 값 정보를 보내주겠다는 의미
    public String helloString(@RequestParam("name")String name){
        return "hello "+name;//html에 return값이 그대로 나타남 (소스코드도 그대로)
    }


    @GetMapping("hello-api")
    @ResponseBody

    public Hello helloApi(@RequestParam("name")String name){
        Hello hello =new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello{
        private String name;//변수 이름은 제이슨 데이터 형식의 키 역할

        public String getName(){
            return name;
        }
        public void setName(String name){//매개변수는 제이슨 데이터 형식의 value역할
            this.name=name;
        }
    }
}
