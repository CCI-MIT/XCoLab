package org.xcolab.view.taglibs.xcolab.interfaces;

import org.xcolab.client.contest.pojo.Contest;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public interface TabContext extends Serializable {

    Contest getContest(HttpServletRequest request);
    TabPermissions getPermissions(HttpServletRequest request);
}