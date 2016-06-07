package org.xcolab.service.search.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ProposalAttribute;
import org.xcolab.service.search.domain.SearchDao;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchDao searchDao;

    @RequestMapping("/search")
    public List<ProposalAttribute> findProposalAttributes(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam String query) {
        final PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord,
                sort);
        return searchDao.findProposalAttribute(paginationHelper, query);
    }
}
