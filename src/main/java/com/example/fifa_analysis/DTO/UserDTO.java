package com.example.fifa_analysis.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private String accessId;
    private String nickname;
    private int level;

//    public UserInfoResponseDto(User entity) {
//        this.accessId = entity.getAccessId();
//        this.nickname = entity.getNickname();
//        this.level = entity.getLevel();
//    }
}
