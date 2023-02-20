package com.example.fifa_analysis;

import com.example.fifa_analysis.DTO.BuyDTO;
import com.example.fifa_analysis.DTO.BuyPlayerDTO;
import com.example.fifa_analysis.DTO.PlayerDTO;
import com.example.fifa_analysis.DTO.UserDTO;
import com.example.fifa_analysis.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor   //사용하는 서비스의 생성자를 자동으로 소환(final 변수)
public class HomeController {

    private final UserService userService;
    private UserDTO userDTO;
    private BuyDTO[] buyDTOS;

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
        buyDTOS=userService.requestBuyInfo(userDTO.getAccessId());
        model.addAttribute("buyDTOS",buyDTOS);
        List<BuyPlayerDTO> boughtList=new ArrayList<>();
        BuyPlayerDTO buyPlayerDTO=null;

        for(BuyDTO bd:buyDTOS){
            buyPlayerDTO=new BuyPlayerDTO();
            buyPlayerDTO.setPlayername(players.get(bd.getSpid()));
            buyPlayerDTO.setGrade(bd.getGrade());
            buyPlayerDTO.setValue(bd.getValue());
            buyPlayerDTO.setTradeDate(bd.getTradeDate());
            boughtList.add(buyPlayerDTO);
        }
        model.addAttribute("boughtList",boughtList);

        return "/fifa/tradeList";
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
