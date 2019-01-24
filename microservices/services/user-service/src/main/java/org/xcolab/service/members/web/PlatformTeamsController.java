package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
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

import org.xcolab.client.user.pojo.IPlatformTeam;
import org.xcolab.client.user.pojo.IUser;
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
    public List<IPlatformTeam> listPlatformTeams(@RequestParam(required = false) Long userId) {
        if (userId == null) {
            return platformTeamDao.getPlatformTeams();
        } else {
            return platformTeamDao.getUserTeams(userId);
        }
    }

    @PostMapping
    public IPlatformTeam createPlatformTeam(@RequestBody IPlatformTeam platformTeam) {
        return platformTeamDao.createPlatformTeam(platformTeam.getName());
    }

    @GetMapping("{teamId}")
    public ResponseEntity<IPlatformTeam> getPlatformTeam(@PathVariable Long teamId) {
        Optional<IPlatformTeam> team = platformTeamDao.getPlatformTeam(teamId);
        return team.map(platformTeam -> new ResponseEntity<>(platformTeam, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{teamId}")
    public boolean deletePlatformTeam(@PathVariable Long teamId)
    {
        return platformTeamDao.delete(teamId) > 0;
    }

    @PutMapping("{teamId}")
    public IPlatformTeam updatePlatformTeam(@RequestBody IPlatformTeam platformTeam, @PathVariable
            Long teamId) {
        assert (platformTeam.getId().equals(teamId));
        return platformTeamDao.updateOrInsertPlatformTeam(platformTeam);
    }

    @GetMapping("{teamId}/members")
    public List<IUser> listTeamUsers(@PathVariable Long teamId) {
        return platformTeamDao.getTeamUsers(teamId);
    }

    @PutMapping("{teamId}/members/{userId}")
    public boolean addUser(@PathVariable Long teamId, @PathVariable Long userId) {
        return platformTeamDao.addUser(teamId, userId) > 0;
    }

    @DeleteMapping("{teamId}/members/{userId}")
    public boolean removeUser(@PathVariable Long teamId, @PathVariable Long userId) {
        return platformTeamDao.removeUser(teamId, userId) > 0;
    }
}
