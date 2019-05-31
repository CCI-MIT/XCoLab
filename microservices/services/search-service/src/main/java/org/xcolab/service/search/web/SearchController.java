package org.xcolab.service.search.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.search.ISearchClient;
import org.xcolab.client.search.pojo.ISearchPojo;
import org.xcolab.service.search.domain.SearchDao;
import org.xcolab.service.search.enums.SearchType;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

@RestController
public class SearchController implements ISearchClient {

    private final SearchDao searchDao;

    @Autowired
    public SearchController(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    @Override
    @GetMapping("/search")
    public List<ISearchPojo> search(@RequestParam(required = false) Integer startRecord,
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

    @Override
    @GetMapping("/search/count")
    public Integer searchCount(@RequestParam(required = false) String sort,
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
