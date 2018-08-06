package org.xcolab.service.search.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.service.search.domain.SearchDao;
import org.xcolab.service.search.enums.SearchType;
import org.xcolab.service.search.pojo.SearchPojo;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

@RestController
public class SearchController {

    private final SearchDao searchDao;

    @Autowired
    public SearchController(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    @RequestMapping("/search")
    public List<SearchPojo> doSearch(@RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String filter, @RequestParam String query) {

        final PaginationHelper paginationHelper =
                new PaginationHelper(startRecord, limitRecord, filter);
        if (filter != null) {
            if (filter.equals(SearchType.USER.getStringType())) {
                return searchDao.findMember(paginationHelper, query);
            }
            if (filter.equals(SearchType.PROPOSAL.getStringType())) {
                return searchDao.findProposalAttribute(paginationHelper, query);
            }
            if (filter.equals(SearchType.CONTEST.getStringType())) {
                return searchDao.findContest(paginationHelper, query);
            }
            if (filter.equals(SearchType.DISCUSSION.getStringType())) {
                return searchDao.findComment(paginationHelper, query);
            }

        }

        return searchDao.findAllSite(paginationHelper, query);

    }

    @RequestMapping("/search/count")
    public Integer doSearch(@RequestParam(required = false) String sort,
            @RequestParam String query) {
        if (sort != null) {
            if (sort.equals(SearchType.USER.getStringType())) {
                return searchDao.findMemberCount(query);
            }
            if (sort.equals(SearchType.PROPOSAL.getStringType())) {
                return searchDao.findProposalAttributeCount(query);
            }
            if (sort.equals(SearchType.CONTEST.getStringType())) {
                return searchDao.findContestCount(query);
            }
            if (sort.equals(SearchType.DISCUSSION.getStringType())) {
                return searchDao.findCommentCount(query);
            }

        }

        return searchDao.findAllSiteCount(query);
    }
}
