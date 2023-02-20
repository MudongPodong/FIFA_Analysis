package com.example.fifa_analysis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor   //사용하는 서비스의 생성자를 자동으로 소환(final 변수)
public class ResponseController {

}
