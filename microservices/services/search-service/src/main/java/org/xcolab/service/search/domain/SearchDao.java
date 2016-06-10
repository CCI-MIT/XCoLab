package org.xcolab.service.search.domain;


import org.xcolab.service.search.pojo.SearchPojo;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface SearchDao {
    List<SearchPojo> findProposalAttribute(PaginationHelper paginationHelper, String query);
    List<SearchPojo> findMember(PaginationHelper paginationHelper, String query);
}
