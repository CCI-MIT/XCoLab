package org.xcolab.view.taglibs.xcolab.interfaces;

import org.xcolab.client.members.permissions.SystemRole;

import java.io.Serializable;

public interface TabPermissions extends Serializable {

    boolean getCanAdmin();

    boolean getCanRole(SystemRole role);

    boolean getCanDelete();

    boolean getCanCreate();
}
