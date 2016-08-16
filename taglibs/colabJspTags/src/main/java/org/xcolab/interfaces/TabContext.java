package org.xcolab.interfaces;


import com.liferay.portal.model.User;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.wrappers.BaseContestWrapper;

import java.io.Serializable;

import javax.portlet.PortletRequest;

public interface TabContext extends Serializable {

    Contest getContest(PortletRequest request);

    BaseContestWrapper getContestWrapped(PortletRequest request);

    TabPermissions getPermissions(PortletRequest request);

    User getUser(PortletRequest request);

    void invalidateContext(PortletRequest request);

}