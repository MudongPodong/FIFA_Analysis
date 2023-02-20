package com.example.fifa_analysis.service;

import com.example.fifa_analysis.DTO.SeasonDTO;
import com.example.fifa_analysis.entity.SeasonEntity;
import com.example.fifa_analysis.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;

    public SeasonDTO findById(int id){
        Optional<SeasonEntity> optionalSeasonEntity=seasonRepository.findById(id);
        if(optionalSeasonEntity.isPresent()){
            return SeasonDTO.toSeasonDTO(optionalSeasonEntity.get());
        }else{
            return null;
        }
    }
}
