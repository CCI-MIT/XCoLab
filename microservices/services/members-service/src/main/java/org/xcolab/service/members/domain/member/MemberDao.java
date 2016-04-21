package org.xcolab.service.members.domain.member;

import org.xcolab.model.tables.pojos.Member;

import java.util.List;

public interface MemberDao {

    Member getMember(long memberId);

    List<Member> listMembersSortByActivityCount(int startRecord, int limitRecord,
                                               String filter, boolean isAscOrder);

    List<Member> listMembersSortByActivityCountFilteredByCategory(int startRecord,
                                                                 int limitRecord,
                                                                 String filter,
                                                                 boolean isAscOrder,
                                                                 String roleName);

    List<Member> listMembersSortByMemberSince(int startRecord, int limitRecord, String filter,
                                             boolean isAscOrder);

    List<Member> listMembersSortByPoint(int startRecord, int limitRecord, String filter,
                                       boolean isAscOrder);

    List<Member> listMembersSortByPointFilteredByCategory(int startRecord, int limitRecord,
                                                         String filter, boolean isAscOrder,
                                                         String roleName);

    List<Member> listMembersSortByRoleName(int startRecord, int limitRecord, String filter,
                                          boolean isAscOrder);

    List<Member> listMembersSortByRoleNameFilteredByCategory(int startRecord, int limitRecord,
                                                            String filter, boolean isAscOrder,
                                                            String roleName);

    List<Member> listMembersSortByScreenName(int startRecord, int limitRecord, String filter,
                                            boolean isAscOrder);

    List<Member> listMembersSortByMemberSinceFilteredByCategory(int startRecord,
                                                               int limitRecord,
                                                               String filter,
                                                               boolean isAscOrder,
                                                               String roleName);

    List<Member> listMembersSortByScreenNameFilteredByCategory(int startRecord,
                                                              int limitRecord,
                                                              String filter,
                                                              boolean isAscOrder,
                                                              String roleName);

    Integer countMembersFilteredByCategory(String filter, String roleName);

    Integer countMembers(String filter);

    Integer getMemberMaterializedPoints(Long memberId);

    Integer getMemberActivityCount(Long memberId);

    Member findOneByScreenName(String screenName);
    Member findOneByEmail(String email);

    void updateMember(Member member);
    void createMember(String screenName, String password, String email, String firstName, String lastName,
            String shortBio, String country, Long facebookId, String openId, long liferayUserId);

    boolean isScreenNameTaken(String screenName);
    boolean isEmailUsed(String email);
}
