package com.example.fifa_analysis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="member_table")   //테이블 이름
@Getter
@Setter
public class UserEntity {
    @Id  //pk지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto-increment
    private Long id;

    @Column(unique=true)
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

}
