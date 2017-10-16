package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.PlatformTeam;
import org.xcolab.service.members.domain.platformteam.PlatformTeamDao;

import java.util.List;

@RestController
@RequestMapping("/platformteams")
public class PlatformTeamsController {
    private final PlatformTeamDao platformTeamDao;

    @Autowired
    public PlatformTeamsController(PlatformTeamDao platformTeamDao) {
        this.platformTeamDao = platformTeamDao;
    }

    @GetMapping
    public List<PlatformTeam> listPlatformTeams() {
        return platformTeamDao.getPlatformTeams();
    }
}
