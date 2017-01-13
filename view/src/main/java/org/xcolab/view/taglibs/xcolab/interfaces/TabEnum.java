package org.xcolab.view.taglibs.xcolab.interfaces;

import javax.servlet.http.HttpServletRequest;

public interface TabEnum {

    String getDisplayName();

    String getElementType();

    String getName();

    boolean getIsDefault();

    boolean getCanView(TabPermissions permissions, TabContext context, HttpServletRequest request);

    boolean getCanEdit(TabPermissions permissions, TabContext context, HttpServletRequest request);

    boolean getCanAddComment(TabPermissions permissions, TabContext context, HttpServletRequest request);

    int getActivityCount(TabContext context, HttpServletRequest request);
}