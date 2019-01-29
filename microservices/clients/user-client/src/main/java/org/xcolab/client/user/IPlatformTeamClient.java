package org.xcolab.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.user.exceptions.PlatformTeamNotFoundException;
import org.xcolab.client.user.pojo.PlatformTeam;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

import java.util.List;

@FeignClient("xcolab-user-service")
@RequestMapping("/platformteams")
public interface IPlatformTeamClient {

    @GetMapping
    List<PlatformTeam> listPlatformTeams(@RequestParam(required = false) Long userId);

    @PostMapping
    PlatformTeam createPlatformTeam(@RequestBody PlatformTeam platformTeam);

    @GetMapping("{teamId}")
    PlatformTeam getPlatformTeam(@PathVariable Long teamId) throws PlatformTeamNotFoundException;

    @DeleteMapping("{teamId}")
    boolean deletePlatformTeam(@PathVariable Long teamId);

    @PutMapping("{teamId}")
    PlatformTeam updatePlatformTeam(@RequestBody PlatformTeam platformTeam, @PathVariable
            Long teamId);

    @GetMapping("{teamId}/members")
    List<UserWrapper> listTeamUsers(@PathVariable Long teamId);

    @PutMapping("{teamId}/members/{userId}")
    boolean addUser(@PathVariable Long teamId, @PathVariable Long userId);

    @DeleteMapping("{teamId}/members/{userId}")
    boolean removeUser(@PathVariable Long teamId, @PathVariable Long userId);

    default List<PlatformTeam> listAllPlatformTeams() {
        return listPlatformTeams(null);
    }

    default PlatformTeam createPlatformTeam(String name) {
        PlatformTeam team = new PlatformTeam();
        team.setName(name);
        return createPlatformTeam(team);
    }

}
