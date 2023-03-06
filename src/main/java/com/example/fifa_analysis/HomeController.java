package com.example.fifa_analysis;

import com.example.fifa_analysis.DTO.*;
import com.example.fifa_analysis.service.SeasonService;
import com.example.fifa_analysis.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor   //사용하는 서비스의 생성자를 자동으로 소환(final 변수)
public class HomeController {

    private final UserService userService;
    private final SeasonService seasonService;
    private UserDTO userDTO;
    private BuyDTO[] buyDTOS;
    private BuyDTO[] sellDTOS;

    private HashMap<String,String> players;
    @GetMapping("/home")
    public String helloWorld() {
        List<String> list=new ArrayList<>();
        list.add("mudong");
        list.add("무중");
        list.add("무중1234");
        String api_key="";

      userDTO=userService.requestUserInfo();
        System.out.println("아이디:"+userDTO.getAccessId());
        System.out.println("닉네임:"+userDTO.getNickname());
        System.out.println("레벨:"+userDTO.getLevel());

        return "/fifa/main";
    }

    @GetMapping("/tradeList")
    public String tradeList(Model model){
        players=new HashMap<>();              /* 모든선수 정보가져오기 -----> 추후에 DB에 저장해야할듯(DB에 저장하면 이 코드 필요없음 & 페이지로딩 엄청 짧아짐)*/
        PlayerDTO[] playerDTOS=userService.requestPlayerInfo();
        for(PlayerDTO player:playerDTOS){
            players.put(player.getId(),player.getName());
        }

        buyDTOS=userService.requestBuyInfo(userDTO.getAccessId(),"buy");
        sellDTOS= userService.requestBuyInfo(userDTO.getAccessId(),"sell");
        List<BuyPlayerDTO> boughtList=new ArrayList<>();
        List<BuyPlayerDTO> soldList=new ArrayList<>();
        long boughtPrice=0;
        long soldPrice=0;
        long margin=0;

        boughtList=BuyPlayerDTO.tobuyPlayerDTOS(buyDTOS,players,seasonService);
        soldList=BuyPlayerDTO.tobuyPlayerDTOS(sellDTOS,players,seasonService);

        for(BuyPlayerDTO buyPlayerDTO:boughtList) boughtPrice+=buyPlayerDTO.getOrigin_value();
        for(BuyPlayerDTO buyPlayerDTO:soldList) soldPrice+=buyPlayerDTO.getOrigin_value();
        margin=soldPrice-boughtPrice; //차익

        model.addAttribute("boughtList",boughtList);
        model.addAttribute("boughtPrice",Long.toString(boughtPrice).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ","));
        model.addAttribute("soldList",soldList);
        model.addAttribute("soldPrice",Long.toString(soldPrice).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ","));
        model.addAttribute("margin",Long.toString(margin).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ","));

        return "/fifa/tradeList";
    }

    @GetMapping("/match_analysis")
    public String matchAnalysis(Model model){
        MatchDetailedDTO matchDetailedDTO=null;

        String[] matchRecord= userService.requestMatchRecord(userDTO.getAccessId());
        for(String matchid:matchRecord){  //여기서 바로 내가 다루고싶은 데이터 가져오기
            matchDetailedDTO=userService.requestMatchDetailed(matchid);
            if(matchDetailedDTO.getMatchInfoDTO().getShoot().getGoalTotal()!=0){
                System.out.println("이경기에서 골넣은수는 "+matchDetailedDTO.getMatchInfoDTO().getShoot().getGoalTotal()+"이다 ");
            }
        }

        model.addAttribute("matchRecord",matchRecord);

        return "/fifa/match_analysis";
    }

        @GetMapping("/collect")
        public String requestCollect(){
            players=new HashMap<>();
            PlayerDTO[] playerDTOS=userService.requestPlayerInfo();
            for(PlayerDTO player:playerDTOS){
                players.put(player.getId(),player.getName());
            }
            return "/fifa/main";
      }
}
