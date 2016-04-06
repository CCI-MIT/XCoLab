package org.xcolab.domain.member;


import org.xcolab.model.tables.pojos.User_;

import java.util.List;

public interface MemberDao {

    List<User_> listMembersSortByActivityCount(int startRecord, int limitRecord,
                                               String filter, boolean isAscOrder);

    List<User_> listMembersSortByActivityCountFilteredByCategory(int startRecord,
                                                                 int limitRecord,
                                                                 String filter,
                                                                 boolean isAscOrder,
                                                                 String roleName);

    List<User_> listMembersSortByMemberSince(int startRecord, int limitRecord, String filter,
                                             boolean isAscOrder);

    List<User_> listMembersSortByPoint(int startRecord, int limitRecord, String filter,
                                       boolean isAscOrder);

    List<User_> listMembersSortByPointFilteredByCategory(int startRecord, int limitRecord,
                                                         String filter, boolean isAscOrder,
                                                         String roleName);

    List<User_> listMembersSortByRoleName(int startRecord, int limitRecord, String filter,
                                          boolean isAscOrder);

    List<User_> listMembersSortByRoleNameFilteredByCategory(int startRecord, int limitRecord,
                                                            String filter, boolean isAscOrder,
                                                            String roleName);

    List<User_> listMembersSortByScreenName(int startRecord, int limitRecord, String filter,
                                            boolean isAscOrder);

    List<User_> listMembersSortByMemberSinceFilteredByCategory(int startRecord,
                                                               int limitRecord,
                                                               String filter,
                                                               boolean isAscOrder,
                                                               String roleName);

    List<User_> listMembersSortByScreenNameFilteredByCategory(int startRecord,
                                                              int limitRecord,
                                                              String filter,
                                                              boolean isAscOrder,
                                                              String roleName);

    Integer countMembersFilteredByCategory(String filter, String roleName);

    Integer countMembers(String filter);

    Integer getMemberMaterializedPoints(Long memberId);

    Integer getMemberActivityCount(Long memberId);

    User_ getMember(Long userId);
}
