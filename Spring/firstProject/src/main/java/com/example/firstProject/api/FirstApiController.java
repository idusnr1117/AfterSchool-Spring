package com.example.firstProject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RestAPI 컨트롤러! JSON 데이터를 반환!
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello()
    {
        return "hello world!";
    }

}
