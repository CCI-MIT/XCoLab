package org.xcolab.interfaces;

import org.xcolab.enums.MemberRole;

public interface TabPermissions {

    boolean getCanAdmin();

    boolean getCanStaff();

    boolean getCanRole(MemberRole role);

    boolean getCanDelete();

    boolean getCanCreate();

    boolean getIsOwner();

}