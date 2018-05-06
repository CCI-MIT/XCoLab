package org.xcolab.service.search.domain;


import org.xcolab.service.search.pojo.SearchPojo;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface SearchDao {

    List<SearchPojo> findProposalAttribute(PaginationHelper paginationHelper, String query);

    List<SearchPojo> findMember(PaginationHelper paginationHelper, String query);

    List<SearchPojo> findContest(PaginationHelper paginationHelper, String query);

    List<SearchPojo> findComment(PaginationHelper paginationHelper, String query);

    List<SearchPojo> findAllSite(PaginationHelper paginationHelper, String query);

    Integer findAllSiteCount(String query);

    Integer findProposalAttributeCount(String query);

    Integer findMemberCount(String query);

    Integer findCommentCount(String query);

    Integer findContestCount(String query);
}
