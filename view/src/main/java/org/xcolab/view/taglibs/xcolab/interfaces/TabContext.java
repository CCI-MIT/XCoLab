package org.xcolab.view.taglibs.xcolab.interfaces;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public interface TabContext extends Serializable {

    ContestWrapper getContest(HttpServletRequest request);

    TabPermissions getPermissions(HttpServletRequest request);
}
