package hello.spring.practice.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    @ResponseBody
    public String hello(@RequestParam("name") String name, int age) {
        HelloData helloData = new HelloData();
        helloData.setName(name);
        helloData.setAge(age);
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        return "name = " + name + " : " + age;
    }

    @GetMapping("json-data")
    public String hello() {
        return "jsonpost";
    }

  //  @PostMapping("/json-data")
    @ResponseBody
    public HelloData hello2(HelloData data) {
        final HelloData helloData = new HelloData();
        helloData.setName(data.getName());
        helloData.setAge(data.getAge());
        return helloData;
    }

    @PostMapping("/json-data")
    @ResponseBody
    public String hello3(HelloData data) {
        return "helloData = " + data.getName() + ", " + data.getAge();
    }

    static class HelloData {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
