package com.abdelmhe.sheridancollege.demo.dto;
import lombok.Data;

@Data
public class TeamRequest {

    private String teamName;

    private String continent;

    private String played;

    private String won;

    private String drawn;

    private String lost;

}