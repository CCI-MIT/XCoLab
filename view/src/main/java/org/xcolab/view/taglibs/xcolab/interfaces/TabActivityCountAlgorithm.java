package org.xcolab.view.taglibs.xcolab.interfaces;

import javax.servlet.http.HttpServletRequest;

public interface TabActivityCountAlgorithm {

    int getActivityCount(TabContext context, HttpServletRequest request);

    TabActivityCountAlgorithm alwaysZero = (context, request) -> 0;

    TabActivityCountAlgorithm discussionCommentsCount = (context, request) -> 0;

    TabActivityCountAlgorithm commentsCount = (context, request) -> 0;

    TabActivityCountAlgorithm membersCount = (context, request) -> 0;

    // TODO: How to handle former static client functions in interface
    // TabActivityCountAlgorithm flagCount =
    //         (context, request) -> flaggingClient.countReports(null, null, null, null, null);
}
