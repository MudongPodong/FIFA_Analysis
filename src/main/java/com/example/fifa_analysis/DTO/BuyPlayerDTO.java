package com.example.fifa_analysis.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BuyPlayerDTO {
    private String tradeDate;
    private String saleSn;
    private String spid;
    private String grade;
    private String value;
    private String playername;
}
