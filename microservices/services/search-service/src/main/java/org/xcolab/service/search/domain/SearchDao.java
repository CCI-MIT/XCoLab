package org.xcolab.service.search.domain;


import org.xcolab.client.search.pojo.ISearchPojo;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface SearchDao {

    List<ISearchPojo> findProposalAttribute(PaginationHelper paginationHelper, String query);

    List<ISearchPojo> findMember(PaginationHelper paginationHelper, String query);

    List<ISearchPojo> findContest(PaginationHelper paginationHelper, String query);

    List<ISearchPojo> findComment(PaginationHelper paginationHelper, String query);

    List<ISearchPojo> findAllSite(PaginationHelper paginationHelper, String query);

    Integer findAllSiteCount(String query);

    Integer findProposalAttributeCount(String query);

    Integer findMemberCount(String query);

    Integer findCommentCount(String query);

    Integer findContestCount(String query);
}
