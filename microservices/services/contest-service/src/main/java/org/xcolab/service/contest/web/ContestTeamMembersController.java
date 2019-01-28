package org.xcolab.service.contest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.IContestTeamMemberClient;
import org.xcolab.client.contest.pojo.IContestTeamMember;
import org.xcolab.client.contest.pojo.IContestTeamMemberRole;
import org.xcolab.service.contest.domain.contestteammember.ContestTeamMemberDao;
import org.xcolab.service.contest.domain.contestteammemberrole.ContestTeamMemberRoleDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

import java.util.List;

@RestController
public class ContestTeamMembersController implements IContestTeamMemberClient {

    private final ContestTeamMemberDao contestTeamMemberDao;
    private final ContestTeamMemberRoleDao contestTeamMemberRoleDao;

    @Autowired
    public ContestTeamMembersController(
            ContestTeamMemberDao contestTeamMemberDao,
            ContestTeamMemberRoleDao contestTeamMemberRoleDao) {
        this.contestTeamMemberDao = contestTeamMemberDao;
        this.contestTeamMemberRoleDao = contestTeamMemberRoleDao;
    }

    @Override
    @PostMapping("/contestTeamMembers")
    public IContestTeamMember createContestTeamMember(
            @RequestBody IContestTeamMember contestTeamMember) {
        IContestTeamMember alreadyExists = contestTeamMemberDao
                .findOneBy(contestTeamMember.getUserId(), contestTeamMember.getContestId(),
                        contestTeamMember.getRoleId());
        if (alreadyExists != null) {
            return alreadyExists;
        } else {
            return this.contestTeamMemberDao.create(contestTeamMember);
        }
    }

    @Override
    @GetMapping("/contestTeamMembers")
    public List<IContestTeamMember> getTeamMembers(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long contestId,
            @RequestParam(required = false) Long roleId) {
        return contestTeamMemberDao.findByGiven(userId, contestId, roleId);
    }

    @Override
    @GetMapping("/contestTeamMembers/getByContestYear")
    public List<IContestTeamMember> getTeamMembers(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long contestYear) {
        return contestTeamMemberDao.findByContestYear(categoryId, contestYear);
    }

    @Override
    @DeleteMapping("/contestTeamMembers/{contestTeamUserId}")
    public boolean deleteContestTeamMember(@PathVariable Long contestTeamUserId) {
        if (contestTeamMemberDao.exists(contestTeamUserId)) {
            return contestTeamMemberDao.delete(contestTeamUserId);
        } else {
            throw new RuntimeEntityNotFoundException(
                    "ContestTeamMember not found with id " + contestTeamUserId);
        }
    }

    @Override
    @GetMapping("/contestTeamMemberRoles/{contestTeamMemberRoleId}")
    public IContestTeamMemberRole getContestTeamMemberRole(
            @PathVariable Long contestTeamMemberRoleId) {
        try {
            return contestTeamMemberRoleDao.get(contestTeamMemberRoleId)
                    .orElseThrow(() -> new RuntimeEntityNotFoundException(
                            "ContestTeamMemberRole not found with id " + contestTeamMemberRoleId));
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException(
                    "ContestTeamMemberRole not found with id " + contestTeamMemberRoleId);
        }
    }
}
