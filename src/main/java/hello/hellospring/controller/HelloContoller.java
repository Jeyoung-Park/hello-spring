package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloContoller {

    // 1. 정적 컨텐츠
    //호스트 주소 뒤의 주소
    @GetMapping("hello")
    //컨트롤러에서 리턴 값으로 문자를 반환하면 viewResolver가 화면을 찾아서 처리
    //resources:templates/ 폴더명.html 과 매칭
    public String hello(Model model){
        //해당 html 파일에 key-value 값으로 매칭
        model.addAttribute("data222", "hello222!");
        return "hello2"; //html 파일명
    }

    //2. mvc와 템플릿 엔진
    @GetMapping("hello-mvc")
    //RequestParam : 외부에서 파라미터를 받아올 떄 쓰는 어노테이션션
   public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    //3. api
    @GetMapping("hello-string")
    //http 프로토콜 응답 바디 부분에 리턴 값을 넣어줌
    @ResponseBody
    public String helloString(@RequestParam(value = "name", required = false) String name){
        return "hello "+name;
    }

    //api 방식으로 데이터를 넘겨줌(객체를 넘겨줌)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello=new Hello();
        hello.setName(name);
        return hello;
    }


    static class Hello{
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

    }
}
