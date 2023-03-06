package com.example.fifa_analysis.service;

import com.example.fifa_analysis.DTO.BuyDTO;
import com.example.fifa_analysis.DTO.MatchDetailedDTO;
import com.example.fifa_analysis.DTO.PlayerDTO;
import com.example.fifa_analysis.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserService {
    private final String user_nickname_url = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname={nickname}";
    private final String api_key="your key";
    public UserDTO requestUserInfo(){
        final String UserInfoUrl = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname={nickname}";
        final HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization",api_key);
        final HttpEntity<String> entity=new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate=new RestTemplate();

        return restTemplate.exchange(user_nickname_url, HttpMethod.GET,entity,UserDTO.class,"무동포동").getBody();
    }

    public BuyDTO[] requestBuyInfo(String userId,String type){
        final String BuyInfoUrl="https://api.nexon.co.kr/fifaonline4/v1.0/users/{accessid}/markets?tradetype={tradetype}&offset={offset}&limit={limit}";
        final HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization",api_key);
        final HttpEntity<String> entity=new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate=new RestTemplate();

        BuyDTO[] buyDTOS=null;
        int offset=0,limit=20;

        return restTemplate.exchange(BuyInfoUrl,HttpMethod.GET,entity,BuyDTO[].class,userId,type,offset,limit).getBody();
    }

    public PlayerDTO[] requestPlayerInfo(){
        final String PlayerInfoUrl="https://static.api.nexon.co.kr/fifaonline4/latest/spid.json";
        final HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization",api_key);
        final HttpEntity<String> entity=new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate=new RestTemplate();

        return restTemplate.exchange(PlayerInfoUrl,HttpMethod.GET,entity,PlayerDTO[].class).getBody();
    }

    public String[] requestMatchRecord(String userId){   //매치의 해시값 가져오기
        final String MatchInfoUrl="https://api.nexon.co.kr/fifaonline4/v1.0/users/{accessid}/matches?matchtype={matchtype}&offset={offset}&limit={limit}";
        final HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization",api_key);
        final HttpEntity<String> entity=new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate=new RestTemplate();

        int offset=0,limit=2;
        return restTemplate.exchange(MatchInfoUrl,HttpMethod.GET,entity,String[].class,userId,50,offset,limit).getBody();
    }

    public MatchDetailedDTO requestMatchDetailed(String matchid){
        System.out.println("matchid:"+matchid);
        final String MatchDetailedInfoUrl="https://api.nexon.co.kr/fifaonline4/v1.0/matches/{matchid}";
        final HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization",api_key);
        //httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        final HttpEntity<String> entity=new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate=new RestTemplate();

        return restTemplate.exchange(MatchDetailedInfoUrl,HttpMethod.GET,entity,MatchDetailedDTO.class,matchid).getBody();
    }
}
