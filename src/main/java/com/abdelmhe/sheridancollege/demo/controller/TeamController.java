package com.abdelmhe.sheridancollege.demo.controller;
import com.abdelmhe.sheridancollege.demo.dto.TeamResponse;
import com.abdelmhe.sheridancollege.demo.exception.RecordNotFoundException;
import com.abdelmhe.sheridancollege.demo.model.Team;
import com.abdelmhe.sheridancollege.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping("/")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @RequestMapping
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(path = {"/add", "/add/{id}"})
    public String addTeam(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        if(id.isPresent()){
            Team team = teamService.getTeamById(id.get());
            model.addAttribute("team", team);
        } else {
            model.addAttribute("team", new Team());
        }
        return "add-team";
    }

    @RequestMapping(path = {"/edit"})
    public String editTeam(Model model, @Param("keyword") String keyword) throws RecordNotFoundException {
        List<Team> teamList = teamService.getAllTeam(keyword);
        model.addAttribute("teams", teamList);
        model.addAttribute("keyword", keyword);
        return "edit-team";
    }

    @RequestMapping(path = {"/delete"})
    public String deleteTeam(Model model, @Param("keyword") String keyword) throws RecordNotFoundException {
        List<Team> teamList = teamService.getAllTeam(keyword);
        model.addAttribute("teams", teamList);
        model.addAttribute("keyword", keyword);
        return "delete-team";
    }

    @RequestMapping(path = "/display")
    public String displayTeams(Model model, @Param("sort") String sort) throws RecordNotFoundException{
        List<Team> teamList = teamService.getAllTeam(null);
        List<TeamResponse> teamResponses = new ArrayList<>();
        teamList.forEach((team) -> {
            int totalWin = 0;
            int totalDraw = 0;
            int totalLost = 0;
            if(team.getWon() > 0){
                totalWin = team.getWon() * 3;
            }
            if(team.getDrawn() > 0){
                totalDraw = team.getDrawn() * 1;
            }
            if(team.getLost() > 0){
                totalLost = team.getLost() * 0;
            }
            teamResponses.add(new TeamResponse(team.getTeamID(), team.getTeamFlag(), team.getTeamName(), team.getContinent(),
                    team.getPlayed(), team.getWon(), team.getDrawn(), team.getLost(), totalWin + totalDraw + totalLost));
        });

        if(sort != null){
            if(sort.equals("name")){
                teamResponses.sort((t1, t2) -> t1.getTeamName().compareTo(t2.getTeamName()));
            } else if(sort.equals("continent")){
                teamResponses.sort((t1, t2) -> t1.getContinent().compareTo(t2.getContinent()));
            } else if(sort.equals("pts")){
                teamResponses.sort((t1, t2) -> t1.getPst().compareTo(t2.getPst()));
            }
        }
        model.addAttribute("teams", teamResponses);
        return "display-team";
    }

    @RequestMapping(path = "/createTeam", method = RequestMethod.POST)
    public String createTeam(Team teamRequest, RedirectAttributes attributes) {
        Team team = teamService.createOrUpdateTeam(teamRequest);
        if(team != null){
            attributes.addFlashAttribute("success", "Saved successfully.");
        } else {
            attributes.addFlashAttribute("error", "Failed to save.");
        }
        return "redirect:/add/";
    }



    @RequestMapping(path = "/delete/{id}")
    public String deleteTeamById(Model model, @PathVariable("id") Long id, RedirectAttributes  attributes)
            throws RecordNotFoundException {
        teamService.deleteTeam(id);
        attributes.addFlashAttribute("success", "Deleted successfully.");
        return "redirect:/delete/";
    }

}


