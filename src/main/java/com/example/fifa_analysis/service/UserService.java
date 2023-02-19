package com.example.fifa_analysis.service;

import com.example.fifa_analysis.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserService {
    private final String user_nickname_url = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname={nickname}";
    private final String api_key="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjEzNTkyNjY5OTMiLCJhdXRoX2lkIjoiMiIsImV4cCI6MTY4ODA0MzE1MywiaWF0IjoxNjcyNDkxMTUzLCJuYmYiOjE2NzI0OTExNTMsInNlcnZpY2VfaWQiOiI0MzAwMTE0ODEiLCJ0b2tlbl90eXBlIjoiQWNjZXNzVG9rZW4ifQ.2F-blYI7sLKjAsjeMRnslEv8TLniFS4UytTxV1Vi1Aw";
    public UserDTO requestUserInfo(){
        final String UserInfoUrl = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname={nickname}";
        final HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization",api_key);
        final HttpEntity<String> entity=new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate=new RestTemplate();

        return restTemplate.exchange(user_nickname_url, HttpMethod.GET,entity,UserDTO.class,"무동포동").getBody();
    }
}
