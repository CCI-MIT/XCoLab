package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.user.IPlatformTeamClient;
import org.xcolab.client.user.exceptions.PlatformTeamNotFoundException;
import org.xcolab.client.user.pojo.wrapper.PlatformTeamWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.service.members.domain.platformteam.PlatformTeamDao;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/platformteams")
public class PlatformTeamsController implements IPlatformTeamClient {
    private final PlatformTeamDao platformTeamDao;

    @Autowired
    public PlatformTeamsController(PlatformTeamDao platformTeamDao) {
        this.platformTeamDao = platformTeamDao;
    }

    @GetMapping
    public List<PlatformTeamWrapper> listPlatformTeams(@RequestParam(required = false) Long userId) {
        if (userId == null) {
            return platformTeamDao.getPlatformTeams();
        } else {
            return platformTeamDao.getUserTeams(userId);
        }
    }

    @PostMapping
    public PlatformTeamWrapper createPlatformTeam(@RequestBody
            PlatformTeamWrapper platformTeamWrapper) {
        return platformTeamDao.createPlatformTeam(platformTeamWrapper.getName());
    }

    @GetMapping("{teamId}")
    public PlatformTeamWrapper getPlatformTeam(@PathVariable Long teamId) throws PlatformTeamNotFoundException {
        Optional<PlatformTeamWrapper> team = platformTeamDao.getPlatformTeam(teamId);
        return team
                .orElseThrow(PlatformTeamNotFoundException::new);
    }

    @DeleteMapping("{teamId}")
    public boolean deletePlatformTeam(@PathVariable Long teamId)
    {
        return platformTeamDao.delete(teamId) > 0;
    }

    @PutMapping("{teamId}")
    public PlatformTeamWrapper updatePlatformTeam(@RequestBody
            PlatformTeamWrapper platformTeamWrapper, @PathVariable
            Long teamId) {
        assert (platformTeamWrapper.getId().equals(teamId));
        return platformTeamDao.updateOrInsertPlatformTeam(platformTeamWrapper);
    }

    @GetMapping("{teamId}/members")
    public List<UserWrapper> listTeamUsers(@PathVariable Long teamId) {
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
