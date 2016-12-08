package org.xcolab.interfaces;

import org.xcolab.client.contest.pojo.Contest;

import java.io.Serializable;

import javax.portlet.PortletRequest;

public interface TabContext extends Serializable {

    Contest getContest(PortletRequest request);
    TabPermissions getPermissions(PortletRequest request);
}