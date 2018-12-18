package org.xcolab.view.pages.contestmanagement.wrappers;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.content.IContentClient;
import org.xcolab.client.content.exceptions.ContentNotFoundException;
import org.xcolab.client.content.pojo.IContentArticle;
import org.xcolab.client.content.pojo.IContentArticleVersion;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.commons.exceptions.ReferenceResolutionException;
import org.xcolab.view.pages.contestmanagement.beans.ContestResourcesBean;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class WikiPageWrapper {

    private final IContentClient contentClient;
    private final Contest contest;
    private final Long loggedInUserId;
    private final IContentArticle contentArticle;
    private final IContentArticleVersion contentArticleVersion;

    public WikiPageWrapper(IContentClient contentClient, Contest contest, Long loggedInUserId) {
        this.contentClient = contentClient;
        this.contest = contest;
        this.loggedInUserId = loggedInUserId;

        if (contest.getResourceArticleId() == null || contest.getResourceArticleId() <= 0) {
            throw ReferenceResolutionException
                    .toObject(IContentArticle.class, contest.getResourceArticleId())
                    .fromObject(Contest.class, contest.getId());
        }
        try {
            contentArticle = contentClient.getContentArticle(contest.getResourceArticleId());
            contentArticleVersion = contentClient.getContentArticleVersion(contentArticle.getMaxVersionId());
        } catch (ContentNotFoundException e) {
            throw ReferenceResolutionException
                    .toObject(IContentArticle.class, contest.getResourceArticleId())
                    .fromObject(Contest.class, contest.getId());
        }
    }

    public static void updateContestWiki(IContentClient contentClient, Contest contest) {
        try {
            if (contest.getResourceArticleId() != null) {
                final IContentArticleVersion resourceArticleVersion = contentClient
                        .getLatestContentArticleVersion(contest.getResourceArticleId());
                resourceArticleVersion.setTitle(contest.getTitle());
                contentClient.updateContentArticleVersion(resourceArticleVersion);
            }
        } catch (ContentNotFoundException ignored) {
        }
    }

    public ContestResourcesBean getContestResourcesBean() {
        final ContestType contestType = ContestTypeClient.getContestType(contest.getContestTypeId());
        ContestResourcesBean contestResourcesBean = new ContestResourcesBean(contestType);
        String resourcesContent = contentArticleVersion.getContent();
        contestResourcesBean.fillSectionsWithContent(resourcesContent);
        return contestResourcesBean;
    }

    public void updateWikiPage(ContestResourcesBean updatedContestResourcesBean)
            throws UnsupportedEncodingException, ParseException {
        updatedContestResourcesBean.fillOverviewSectionContent(contest);
        String updatedResourcesContent = updatedContestResourcesBean.getSectionsAsHtml();
        if (!contentArticleVersion.getContent().equals(updatedResourcesContent)) {
            contentArticleVersion.setTitle(contest.getTitle());
            contentArticleVersion.setContent(updatedResourcesContent);
            contentArticleVersion.setArticleId(contentArticle.getId());
            contentArticleVersion.setAuthorUserId(loggedInUserId);
            contentClient.updateContentArticleVersion(contentArticleVersion);
        }
    }
}
