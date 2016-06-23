package org.xcolab.service.search.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ProposalAttribute;
import org.xcolab.service.search.domain.SearchDao;
import org.xcolab.service.search.enums.SearchType;
import org.xcolab.service.search.pojo.SearchPojo;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchDao searchDao;

    @RequestMapping("/search")
    public List<SearchPojo> doSearch(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam String query) {

        final PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord,
                sort);
        if(sort != null){
            if( sort.equals(SearchType.MEMBER.getStringType())){
                return searchDao.findMember(paginationHelper, query);
            }
            if( sort.equals(SearchType.PROPOSAL.getStringType())){
                return searchDao.findProposalAttribute(paginationHelper, query);
            }
            if( sort.equals(SearchType.CONTEST.getStringType())){
                return searchDao.findContest(paginationHelper, query);
            }
            if( sort.equals(SearchType.DISCUSSION.getStringType())){
                return searchDao.findComment(paginationHelper, query);
            }

        }

        return searchDao.findAllSite(paginationHelper, query);

    }

    @RequestMapping("/search/count")
    public Integer doSearch(
            @RequestParam(required = false) String sort,
            @RequestParam String query) {
        if(sort != null){
            if( sort.equals(SearchType.MEMBER.getStringType())){
                return searchDao.findMemberCount(query);
            }
            if( sort.equals(SearchType.PROPOSAL.getStringType())){
                return searchDao.findProposalAttributeCount(query);
            }
            if( sort.equals(SearchType.CONTEST.getStringType())){
                return searchDao.findContestCount(query);
            }
            if( sort.equals(SearchType.DISCUSSION.getStringType())){
                return searchDao.findCommentCount(query);
            }

        }

        return searchDao.findAllSiteCount( query);
    }
}
