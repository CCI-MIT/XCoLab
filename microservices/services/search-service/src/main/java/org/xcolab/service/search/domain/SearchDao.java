package org.xcolab.service.search.domain;

import org.xcolab.model.tables.pojos.ProposalAttribute;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface SearchDao {
    List<ProposalAttribute> findProposalAttribute(PaginationHelper paginationHelper, String query);
}
