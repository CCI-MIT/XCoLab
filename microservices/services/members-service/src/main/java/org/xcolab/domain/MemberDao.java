package org.xcolab.domain;


import org.xcolab.model.tables.pojos.User_;
import java.util.List;

public interface MemberDao {


     public List<User_> listAllMembersSortByActivityCount(int startRecord, int limitRecord, String filter,
                                                          boolean isAscOrder);

     public List<User_> listAllMembersSortByMemberSince(int startRecord, int limitRecord, String filter,
                                                        boolean isAscOrder);

     public List<User_> listAllMembersSortByPoint(int startRecord, int limitRecord, String filter, boolean isAscOrder);


     public List<User_> listAllMembersSortByRoleName(int startRecord, int limitRecord, String filter,
                                                     boolean isAscOrder);

     public List<User_> listAllMembersSortByScreenName(int startRecord, int limitRecord, String filter,
                                                        boolean isAscOrder);


}
