package com.example.fifa_analysis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fifaAnalysis")
@RequiredArgsConstructor   //사용하는 서비스의 생성자를 자동으로 소환(final 변수)
public class HomeController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "hello!";
    }
}
