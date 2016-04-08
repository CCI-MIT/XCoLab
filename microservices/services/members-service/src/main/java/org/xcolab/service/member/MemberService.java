package org.xcolab.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xcolab.domain.member.MemberDao;
import org.xcolab.model.tables.pojos.User_;

import java.util.List;

@Service
public class MemberService {

    private final MemberDao memberDao;

    @Autowired
    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public List<User_> listMembersSortByScreenName(int firstUser, int lastUser,
                                                   String screenNameFilter, boolean isAscOrder) {
        return this.memberDao.listMembersSortByScreenName(firstUser, lastUser, screenNameFilter,
                isAscOrder);
    }

    public List<User_> listMembersSortByPoint(int firstUser, int lastUser, String screenNameFilter,
                                              boolean isAscOrder) {
        return this.memberDao.listMembersSortByPoint(firstUser, lastUser,
                screenNameFilter, isAscOrder);
    }

    public List<User_> listMembersSortByPointFilteredByCategory(int startRecord,
                                                                int limitRecord,
                                                                String filter,
                                                                boolean isAscOrder,
                                                                String roleName) {
        return this.memberDao.listMembersSortByPointFilteredByCategory(startRecord,
                limitRecord, filter, isAscOrder, roleName);
    }

    public List<User_> listMembersSortByActivityCount(int firstUser, int lastUser,
                                                      String screenNameFilter, boolean isAscOrder) {
        return this.memberDao.listMembersSortByActivityCount(firstUser, lastUser,
                screenNameFilter, isAscOrder);
    }

    public List<User_> listMembersSortByActivityCountFilteredByCategory(int firstUser, int lastUser,
                                                                        String screenNameFilter,
                                                                        boolean isAscOrder,
                                                                        String roleName) {
        return this.memberDao.listMembersSortByActivityCountFilteredByCategory(firstUser, lastUser,
                screenNameFilter, isAscOrder, roleName);
    }

    public List<User_> listMembersSortByMemberSince(int firstUser, int lastUser,
                                                    String screenNameFilter, boolean isAscOrder) {
        return this.memberDao.listMembersSortByMemberSince(firstUser, lastUser,
                screenNameFilter, isAscOrder);
    }

    public List<User_> listMembersSortByRoleName(int firstUser, int lastUser,
                                                 String screenNameFilter, boolean isAscOrder) {
        return this.memberDao.listMembersSortByRoleName(firstUser, lastUser,
                screenNameFilter, isAscOrder);
    }

    public List<User_> listMembersSortByRoleNameFilteredByCategory(int firstUser, int lastUser, String screenNameFilter,
                                                                   boolean isAscOrder, String roleName) {
        return this.memberDao.listMembersSortByRoleNameFilteredByCategory(firstUser,
                lastUser, screenNameFilter, isAscOrder, roleName);
    }


    public List<User_> listMembersSortByScreenNameFilteredByCategory(int firstUser, int lastUser,
                                                                     String screenNameFilter, boolean isAscOrder, String roleName) {
        return this.memberDao.listMembersSortByScreenNameFilteredByCategory(firstUser, lastUser,
                screenNameFilter, isAscOrder, roleName);
    }

    public List<User_> listMembersSortByMemberSinceFilteredByCategory(int firstUser, int lastUser,
                                                                      String screenNameFilter, boolean isAscOrder, String roleName) {
        return this.memberDao.listMembersSortByMemberSinceFilteredByCategory(firstUser, lastUser,
                screenNameFilter, isAscOrder, roleName);
    }

    public Integer countMembers(String filter) {
        return this.memberDao.countMembers(filter);
    }

    public Integer countMembersFilteredByCategory(String filter, String roleName) {
        return this.memberDao.countMembersFilteredByCategory(filter, roleName);
    }

    public Integer getMemberMaterializedPoints(Long memberId) {
        return this.memberDao.getMemberMaterializedPoints(memberId);
    }

    public Integer getMemberActivityCount(Long memberId) {
        return this.memberDao.getMemberActivityCount(memberId);
    }

    public User_ getMember(Long userId) {
        return this.memberDao.getMember(userId);
    }

    public void updateMember(User_ user) {
        this.memberDao.updateMember(user);
    }

}