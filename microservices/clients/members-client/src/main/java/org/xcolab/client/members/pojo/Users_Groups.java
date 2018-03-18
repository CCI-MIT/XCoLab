package org.xcolab.client.members.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users_Groups implements Serializable {

    private static final long serialVersionUID = -775474978;

    public static final TypeProvider<Users_Groups> TYPES =
            new TypeProvider<>(Users_Groups.class,
                    new ParameterizedTypeReference<List<Users_Groups>>() {
                    });

    private Long userid;
    private Long groupid;

    public Users_Groups() {}

    public Users_Groups(Users_Groups value) {
        this.userid = value.userid;
        this.groupid = value.groupid;
    }

    public Users_Groups(
        Long userid,
        Long groupid
    ) {
        this.userid = userid;
        this.groupid = groupid;
    }

    public Long getUserId() {
        return this.userid;
    }

    public void setUserId(Long userid) {
        this.userid = userid;
    }

    public Long getGroupId() {
        return this.groupid;
    }

    public void setGroupId(Long groupid) {
        this.groupid = groupid;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Users_Groups other = (Users_Groups) obj;
        if (userid == null) {
            if (other.userid != null)
                return false;
        }
        else if (!userid.equals(other.userid))
            return false;
        if (groupid == null) {
            if (other.groupid != null)
                return false;
        }
        else if (!groupid.equals(other.groupid))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userid == null) ? 0 : userid.hashCode());
        result = prime * result + ((groupid == null) ? 0 : groupid.hashCode());
        return result;
    }

    @Override
    public String toString() {
        String sb = "Users_Groups (" + userid + ", " + groupid + ")";

        return sb;
    }
}
