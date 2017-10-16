package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.Member;
import org.xcolab.model.tables.pojos.PlatformTeam;
import org.xcolab.service.members.domain.platformteam.PlatformTeamDao;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/platformteams")
public class PlatformTeamsController {
    private final PlatformTeamDao platformTeamDao;

    @Autowired
    public PlatformTeamsController(PlatformTeamDao platformTeamDao) {
        this.platformTeamDao = platformTeamDao;
    }

    @GetMapping
    public List<PlatformTeam> listPlatformTeams(@RequestParam(required = false) Long userId) {
        if (userId == null) {
            return platformTeamDao.getPlatformTeams();
        } else {
            return platformTeamDao.getUserTeams(userId);
        }
    }

    @PostMapping
    public PlatformTeam createPlatformTeam(@RequestBody PlatformTeam platformTeam) {
        return platformTeamDao.createPlatformTeam(platformTeam.getName());
    }

    @GetMapping("{teamId}")
    public ResponseEntity<PlatformTeam> getPlatformTeam(@PathVariable Long teamId) {
        Optional<PlatformTeam> team = platformTeamDao.getPlatformTeam(teamId);
        return team.map(platformTeam -> new ResponseEntity<>(platformTeam, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{teamId}")
    public boolean deletePlatformTeam(@PathVariable Long teamId)
    {
        return platformTeamDao.delete(teamId) > 0;
    }

    @PutMapping("{teamId}")
    public PlatformTeam updatePlatformTeam(@RequestBody PlatformTeam platformTeam, @PathVariable
            Long teamId) {
        assert (platformTeam.getId_().equals(teamId));
        return platformTeamDao.updatePlatformTeam(platformTeam);
    }

    @GetMapping("{teamId}/members")
    public List<Member> listTeamMembers(@PathVariable Long teamId) {
        return platformTeamDao.getTeamMembers(teamId);
    }

    @PutMapping("{teamId}/members/{userId}")
    public boolean addMember(@PathVariable Long teamId, @PathVariable Long userId) {
        return platformTeamDao.addMember(teamId, userId) > 0;
    }

    @DeleteMapping("{teamId}/members/{userId}")
    public boolean removeMember(@PathVariable Long teamId, @PathVariable Long userId) {
        return platformTeamDao.removeMember(teamId, userId) > 0;
    }
}
