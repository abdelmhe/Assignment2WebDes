package com.abdelmhe.sheridancollege.demo.dto;

import lombok.Data;

@Data
public class TeamResponse {

    private Long teamID;

    private String teamFlag;

    private String teamName;

    private String continent;

    private Integer played;

    private Integer won;

    private Integer drawn;

    private Integer lost;

    private Integer pst;

    public TeamResponse(Long teamID, String teamFlag, String teamName, String continent, Integer played, Integer won, Integer drawn, Integer lost, Integer pst) {
        this.teamID = teamID;
        this.teamFlag = teamFlag;
        this.teamName = teamName;
        this.continent = continent;
        this.played = played;
        this.won = won;
        this.drawn = drawn;
        this.lost = lost;
        this.pst = pst;
    }

    public Long getTeamID() {
        return teamID;
    }

    public void setTeamID(Long teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Integer getPlayed() {
        return played;
    }

    public void setPlayed(Integer played) {
        this.played = played;
    }

    public Integer getWon() {
        return won;
    }

    public void setWon(Integer won) {
        this.won = won;
    }

    public Integer getDrawn() {
        return drawn;
    }

    public void setDrawn(Integer drawn) {
        this.drawn = drawn;
    }

    public Integer getLost() {
        return lost;
    }

    public void setLost(Integer lost) {
        this.lost = lost;
    }

    public Integer getPst() {
        return pst;
    }

    public void setPst(Integer pst) {
        this.pst = pst;
    }

    public String getTeamFlag() {
        return teamFlag;
    }

    public void setTeamFlag(String teamFlag) {
        this.teamFlag = teamFlag;
    }
}
