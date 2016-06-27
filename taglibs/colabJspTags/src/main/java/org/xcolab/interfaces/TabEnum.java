package org.xcolab.interfaces;

import javax.portlet.PortletRequest;

public interface TabEnum {

    String getDisplayName();

    String getElementType();

    String getName();

    boolean getIsDefault();

    boolean getCanView(TabPermissions permissions, TabContext context, PortletRequest request);

    boolean getCanEdit(TabPermissions permissions, TabContext context, PortletRequest request);

    boolean getCanAddComment(TabPermissions permissions, TabContext context, PortletRequest request);

    int getActivityCount(TabContext context, PortletRequest request);

}