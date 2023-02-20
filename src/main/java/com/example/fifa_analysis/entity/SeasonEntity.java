package com.example.fifa_analysis.entity;

import com.example.fifa_analysis.DTO.SeasonDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="fifaseason")   //테이블 이름
@Getter
@Setter
public class SeasonEntity {
    @Id  //pk지정
    //@GeneratedValue(strategy = GenerationType.IDENTITY)  //auto-increment
    private int id;
    private String seasonname;
    private String season_img;

    public static SeasonEntity toSeasonEntity(SeasonDTO seasonDTO){
        SeasonEntity seasonEntity=new SeasonEntity();
        seasonEntity.setId(seasonEntity.getId());
        seasonEntity.setSeasonname(seasonEntity.getSeasonname());
        seasonEntity.setSeason_img(seasonEntity.getSeason_img());
        return seasonEntity;
    }
}
