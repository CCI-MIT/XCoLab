package org.xcolab.view.taglibs.xcolab.interfaces;

import org.xcolab.entity.utils.enums.MemberRole;

import java.io.Serializable;

public interface TabPermissions extends Serializable {

    boolean getCanAdmin();

    boolean getCanRole(MemberRole role);

    boolean getCanDelete();

    boolean getCanCreate();
}