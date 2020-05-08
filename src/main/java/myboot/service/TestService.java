package myboot.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestService {
    @GetMapping("/test")
    public String  test(){
        return "111";
    }
}
