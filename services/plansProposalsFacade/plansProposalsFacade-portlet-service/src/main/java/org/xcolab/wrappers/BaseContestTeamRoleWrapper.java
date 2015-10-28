package org.xcolab.wrappers;

import com.liferay.portal.model.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by johannes on 10/27/15.
 * A wrapper class for contest team roles to be shared across portlets.
 * More specific wrappers should inherit from this one.
 */
public class BaseContestTeamRoleWrapper implements Serializable {

    private static final long serialVersionUID = 1L;
    private String roleName;
    private List<User> users;

    public BaseContestTeamRoleWrapper(String roleName, List<User> users) {
        super();
        this.roleName = roleName;
        this.users = users;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
