package org.xcolab.client.sharedcolab.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class SharedMember implements Serializable {

    private static final long serialVersionUID = -2127758896;

    public static final TypeProvider<SharedMember> TYPES =
            new TypeProvider<>(SharedMember.class,
                    new ParameterizedTypeReference<List<SharedMember>>() {
                    });

    private Long      sharedmemberid;
    private String    screenname;
    private String    emailaddress;
    private Timestamp createdate;
    private String    colaborigin;

    public SharedMember() {}

    public SharedMember(SharedMember value) {
        this.sharedmemberid = value.sharedmemberid;
        this.screenname = value.screenname;
        this.emailaddress = value.emailaddress;
        this.createdate = value.createdate;
        this.colaborigin = value.colaborigin;
    }

    public Long getSharedMemberId() {
        return this.sharedmemberid;
    }

    public void setSharedMemberId(Long sharedmemberid) {
        this.sharedmemberid = sharedmemberid;
    }

    public String getScreenName() {
        return this.screenname;
    }

    public void setScreenName(String screenname) {
        this.screenname = screenname;
    }

    public String getEmailAddress() {
        return this.emailaddress;
    }

    public void setEmailAddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public Timestamp getCreateDate() {
        return this.createdate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public String getColabOrigin() {
        return this.colaborigin;
    }

    public void setColabOrigin(String colaborigin) {
        this.colaborigin = colaborigin;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SharedMember other = (SharedMember) obj;
        if (sharedmemberid == null) {
            if (other.sharedmemberid != null) {
                return false;
            }
        }
        else if (!sharedmemberid.equals(other.sharedmemberid)) {
            return false;
        }
        if (screenname == null) {
            if (other.screenname != null) {
                return false;
            }
        }
        else if (!screenname.equals(other.screenname)) {
            return false;
        }
        if (emailaddress == null) {
            if (other.emailaddress != null) {
                return false;
            }
        }
        else if (!emailaddress.equals(other.emailaddress)) {
            return false;
        }
        if (createdate == null) {
            if (other.createdate != null) {
                return false;
            }
        }
        else if (!createdate.equals(other.createdate)) {
            return false;
        }
        if (colaborigin == null) {
            if (other.colaborigin != null) {
                return false;
            }
        }
        else if (!colaborigin.equals(other.colaborigin)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sharedmemberid == null) ? 0 : sharedmemberid.hashCode());
        result = prime * result + ((screenname == null) ? 0 : screenname.hashCode());
        result = prime * result + ((emailaddress == null) ? 0 : emailaddress.hashCode());
        result = prime * result + ((createdate == null) ? 0 : createdate.hashCode());
        result = prime * result + ((colaborigin == null) ? 0 : colaborigin.hashCode());
        return result;
    }

    @Override
    public String toString() {

        return "SharedMember (" + sharedmemberid + ", " + screenname + ", " + emailaddress + ", "
                + createdate + ", " + colaborigin + ")";
    }
}

