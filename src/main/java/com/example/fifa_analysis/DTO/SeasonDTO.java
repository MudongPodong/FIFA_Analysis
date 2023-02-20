package com.example.fifa_analysis.DTO;

import com.example.fifa_analysis.entity.SeasonEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SeasonDTO {
    private int id;
    private String seasonname;
    private String season_img;

    public static SeasonDTO toSeasonDTO(SeasonEntity seasonEntity){
        SeasonDTO seasonDTO=new SeasonDTO();
        seasonDTO.setId(seasonEntity.getId());
        seasonDTO.setSeasonname(seasonEntity.getSeasonname());
        seasonDTO.setSeason_img(seasonEntity.getSeason_img());
        return seasonDTO;
    }
}
