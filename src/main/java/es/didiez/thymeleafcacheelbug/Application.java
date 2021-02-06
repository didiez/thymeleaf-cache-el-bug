package es.didiez.thymeleafcacheelbug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @GetMapping({"/", "/index"})
    String index(){
        return "index";
    }
    
    @GetMapping("/safe")
    String safe(){
        return "safe";
    }

}
