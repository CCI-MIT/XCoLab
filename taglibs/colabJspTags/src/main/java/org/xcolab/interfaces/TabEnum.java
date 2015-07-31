package org.xcolab.interfaces;

import javax.portlet.PortletRequest;

public interface TabEnum {

    String getDisplayName();

    String getElementType();

    String getName();

    boolean getIsDefault();

    public boolean getCanView(TabPermissions permissions, TabContext context, PortletRequest request);

    public boolean getCanEdit(TabPermissions permissions, TabContext context, PortletRequest request);

    public boolean getCanAddComment(TabPermissions permissions, TabContext context, PortletRequest request);

    public int getActivityCount(TabContext context, PortletRequest request);

}