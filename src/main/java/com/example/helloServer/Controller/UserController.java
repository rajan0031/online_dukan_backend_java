package com.example.helloServer.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UserController {

    @GetMapping("/home")
    public String serverTest() {
        return new String("hello server is running......");
    }

}
