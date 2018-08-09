package org.xcolab.service.contest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.ContestTeamMember;
import org.xcolab.model.tables.pojos.ContestTeamMemberRole;
import org.xcolab.service.contest.domain.contestteammember.ContestTeamMemberDao;
import org.xcolab.service.contest.domain.contestteammemberrole.ContestTeamMemberRoleDao;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

@RestController
public class ContestTeamMembersController {
    @Autowired
    private ContestTeamMemberDao contestTeamMemberDao;

    @Autowired
    private ContestTeamMemberRoleDao contestTeamMemberRoleDao;

    @PostMapping(value = "/contestTeamMembers")
    public ContestTeamMember createContestTeamMember(@RequestBody ContestTeamMember contestTeamMember) {
        ContestTeamMember alreadyExists = contestTeamMemberDao.findOneBy(contestTeamMember.getUserId(),
                contestTeamMember.getContestId(),
                contestTeamMember.getRoleId());
        if (alreadyExists != null) {
            return alreadyExists;
        } else {
            return this.contestTeamMemberDao.create(contestTeamMember);
        }

    }

    @GetMapping(value = "/contestTeamMembers/{contestTeamuserId}")
    public ContestTeamMember getContestTeamMember(@PathVariable("contestTeamuserId") Long contestTeamuserId) throws NotFoundException {
        return contestTeamMemberDao.get(contestTeamuserId)
                .orElseThrow(NotFoundException::new);
    }

    @PutMapping(value = "/contestTeamMembers/{id}")
    public boolean updateContestTeamMember(@RequestBody ContestTeamMember contestTeamMember,
                                           @PathVariable("id") Long id) throws NotFoundException {

        if (id == null || id == 0 || contestTeamMemberDao.get(id) == null) {
            throw new NotFoundException("No ContestTeamMember with id " + id);
        } else {
            return contestTeamMemberDao.update(contestTeamMember);
        }
    }

    @RequestMapping(value = "/contestTeamMembers", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestTeamMember> getContestTeamMembers(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long contestId,
            @RequestParam(required = false) Long roleId
    ) {
        return contestTeamMemberDao.findByGiven(userId, contestId, roleId);
    }

    @RequestMapping(value = "/contestTeamMembers/getByContestYear", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestTeamMember> getByContestYear(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long contestYear
    ) {
        return contestTeamMemberDao.findByContestYear(categoryId,contestYear);
    }

    @DeleteMapping(value = "/contestTeamMembers/{id}")
    public boolean deleteContestTeamMember(@PathVariable long id)
            throws NotFoundException {
        if (contestTeamMemberDao.exists(id)) {
            return contestTeamMemberDao.delete(id);
        } else {
            throw new NotFoundException("No ContestTeamMember with id given");
        }
    }

    @GetMapping(value = "/contestTeamMemberRoles/{contestTeamMemberRoleId}")
    public ContestTeamMemberRole getContestTeamMemberRole(@PathVariable long contestTeamMemberRoleId)
            throws NotFoundException {
        return contestTeamMemberRoleDao.get(contestTeamMemberRoleId)
                .orElseThrow(NotFoundException::new);
    }
}
