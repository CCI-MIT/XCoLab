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

    @GetMapping(value = "/contestTeamMembers/{contestTeamMemberId}")
    public ContestTeamMember getContestTeamMember(@PathVariable("contestTeamMemberId") Long contestTeamMemberId) throws NotFoundException {
        return contestTeamMemberDao.get(contestTeamMemberId)
                .orElseThrow(NotFoundException::new);
    }

    @PutMapping(value = "/contestTeamMembers/{id_}")
    public boolean updateContestTeamMember(@RequestBody ContestTeamMember contestTeamMember,
                                           @PathVariable("id_") Long id_) throws NotFoundException {

        if (id_ == null || id_ == 0 || contestTeamMemberDao.get(id_) == null) {
            throw new NotFoundException("No ContestTeamMember with id " + id_);
        } else {
            return contestTeamMemberDao.update(contestTeamMember);
        }
    }

    @RequestMapping(value = "/contestTeamMembers", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestTeamMember> getContestTeamMembers(
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) Long contestId,
            @RequestParam(required = false) Long roleId
    ) {
        return contestTeamMemberDao.findByGiven(memberId, contestId, roleId);
    }

    @RequestMapping(value = "/contestTeamMembers/getByContestYear", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestTeamMember> getByContestYear(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long contestYear
    ) {
        return contestTeamMemberDao.findContestYear(categoryId,contestYear);
    }

    @DeleteMapping(value = "/contestTeamMembers/{id_}")
    public boolean deleteContestTeamMember(@PathVariable long id_)
            throws NotFoundException {
        if (contestTeamMemberDao.exists(id_)) {
            return contestTeamMemberDao.delete(id_);
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
