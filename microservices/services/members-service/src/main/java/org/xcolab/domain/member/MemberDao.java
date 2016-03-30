package org.xcolab.domain.member;


import org.xcolab.model.tables.pojos.User_;

import java.util.List;

public interface MemberDao {


     public List<User_> listMembersSortByActivityCount(int startRecord, int limitRecord, String filter,
                                                          boolean isAscOrder);

     public List<User_> listMembersSortByMemberSince(int startRecord, int limitRecord, String filter,
                                                        boolean isAscOrder);

     public List<User_> listMembersSortByPoint(int startRecord, int limitRecord, String filter, boolean isAscOrder);


     public List<User_> listMembersSortByRoleName(int startRecord, int limitRecord, String filter,
                                                     boolean isAscOrder);

     public List<User_> listMembersSortByScreenName(int startRecord, int limitRecord, String filter,
                                                        boolean isAscOrder);

     public List<User_> listMembersSortByMemberSinceFilteredByCategory(int startRecord, int limitRecord,
                                                                          String filter, boolean isAscOrder,
                                                                          String roleName);

     public List<User_> listMembersSortByScreenNameFilteredByCategory(int startRecord, int limitRecord,
                                                                         String filter, boolean isAscOrder,
                                                                         String roleName);

     public Integer countMembersFilteredByCategory(String filter, String roleName);

     public Integer countMembers(String filter);

     public Integer getMemberMaterializedPoints(Long memberId);

     public Integer getMemberActivityCount(Long memberId);
}
