package th.mfu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    String hello() {
        return "Hello World!";
    }

    @GetMapping("/greet/{name}")
    String greet(@PathVariable String name){
        return "Hello "+name;
    }

   
}
