package com.example.fifa_analysis.DTO;

import com.example.fifa_analysis.service.SeasonService;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BuyPlayerDTO {    //거래일, 시즌, 선수사진 포함 DTO
    private String tradeDate;
    private String saleSn;
    private String spid;
    private String grade;
    private String value;   //,로 천 단위 구분한 가격
    private long origin_value;  //천단위 구분없는 순수 가격(계산용)
    private String playername;
    private String season;   //시즌정보(무슨시즌인지 담겨있음)
    private String season_img;
    private String player_img;


    public static List<BuyPlayerDTO> tobuyPlayerDTOS(BuyDTO[] buyDTOS, HashMap<String,String> players,SeasonService seasonService){
        List<BuyPlayerDTO> list=new ArrayList<>();
        BuyPlayerDTO buyPlayerDTO=null;

        for(BuyDTO bd:buyDTOS){
            buyPlayerDTO=new BuyPlayerDTO();
            buyPlayerDTO.setPlayername(players.get(bd.getSpid()));
            SeasonDTO seasonDTO=seasonService.findById(Integer.parseInt(bd.getSpid().substring(0,3)));
            buyPlayerDTO.setSeason(seasonDTO.getSeasonname());  //나중에 추가된거(시즌정보)
            buyPlayerDTO.setSeason_img(seasonDTO.getSeason_img());  //시즌이미지 가져오기
            //https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/playersAction/p101000246.png
            buyPlayerDTO.setPlayer_img("https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/playersAction/p"+bd.getSpid()+".png");
            buyPlayerDTO.setGrade(bd.getGrade());
            buyPlayerDTO.setValue(bd.getValue().replaceAll("\\B(?=(\\d{3})+(?!\\d))", ","));
            buyPlayerDTO.setOrigin_value(Long.parseLong(bd.getValue()));
            buyPlayerDTO.setTradeDate(bd.getTradeDate());
            list.add(buyPlayerDTO);
        }
        return list;
    }

}
