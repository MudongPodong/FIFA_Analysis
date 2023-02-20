package com.example.fifa_analysis.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BuyPlayerDTO {    //거래일, 시즌 포함 DTO
    private String tradeDate;
    private String saleSn;
    private String spid;
    private String grade;
    private String value;
    private String playername;
    private String season;   //나중에 추가된거(무슨시즌인지 담겨있음)
    private String season_img;
    private String player_img;

}
