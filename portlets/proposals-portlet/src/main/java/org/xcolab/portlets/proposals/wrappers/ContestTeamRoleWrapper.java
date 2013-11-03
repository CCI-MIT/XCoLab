package org.xcolab.portlets.proposals.wrappers;

import java.io.Serializable;
import java.util.List;

import com.liferay.portal.model.User;

public class ContestTeamRoleWrapper implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roleName;
    private List<User> users;
    
    
    public ContestTeamRoleWrapper(String roleName, List<User> users) {
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
