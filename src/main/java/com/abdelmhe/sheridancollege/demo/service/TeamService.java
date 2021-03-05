package com.abdelmhe.sheridancollege.demo.service;

import com.abdelmhe.sheridancollege.demo.dao.DatabaseAccess;
import com.abdelmhe.sheridancollege.demo.exception.RecordNotFoundException;
import com.abdelmhe.sheridancollege.demo.model.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TeamService {

    @Autowired
    private DatabaseAccess access;

    public List<Team> getAllTeam(String keyword){
        if (keyword != null){
            return access.search(keyword);
        }
        return access.findAll();
    }


    public Team getTeamById(Long id) throws RecordNotFoundException {
        Optional<Team> team = access.findById(id);
        if(team.isPresent()){
            return team.get();
        } else {
            throw new RecordNotFoundException("No team record exist for given id");
        }
    }

    public Team createOrUpdateTeam(Team team){
        System.out.println(team.toString());
        if(team.getTeamID() == null){
            return access.save(team);
        } else {
            Optional<Team> teams = access.findById(team.getTeamID());
            if (teams.isPresent()){
                Team newTeam = teams.get();
                BeanUtils.copyProperties(team, newTeam);
                newTeam = access.save(newTeam);
                return newTeam;
            } else {
                return access.save(team);
            }
        }
    }

    public void deleteTeam(Long id) throws RecordNotFoundException{
        Optional<Team> team = access.findById(id);
        if(team.isPresent()){
            access.deleteById(id);
        } else {
            throw new RecordNotFoundException("No team record exist for given id");
        }
    }

}

