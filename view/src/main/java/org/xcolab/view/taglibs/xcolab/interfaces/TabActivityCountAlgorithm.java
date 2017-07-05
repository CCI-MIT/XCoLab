package org.xcolab.view.taglibs.xcolab.interfaces;

import org.xcolab.client.flagging.FlaggingClient;

import javax.servlet.http.HttpServletRequest;

public interface TabActivityCountAlgorithm {
    
    int getActivityCount(TabContext context, HttpServletRequest request);

    TabActivityCountAlgorithm alwaysZero = (context, request) -> 0;

    TabActivityCountAlgorithm discussionCommentsCount = (context, request) -> 0;

    TabActivityCountAlgorithm commentsCount = (context, request) -> 0;

    TabActivityCountAlgorithm membersCount = (context, request) -> 0;

    TabActivityCountAlgorithm flagCount =
            (context, request) -> FlaggingClient.countReports(null, null, null, null, null);
}
