package org.xcolab.client.proposals.pojo.group;

import java.io.Serializable;

public class AbstractGroup_ implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long    groupid;
    private Long    companyid;
    private Long    creatoruserid;
    private Long    classnameid;
    private Long    classpk;
    private Long    parentgroupid;
    private Long    livegroupid;
    private String  name;
    private String  description;
    private Integer type_;
    private String  typesettings;
    private String  friendlyurl;
    private Boolean active_;
    private Boolean site;
    private String  uuid_;
    private String  treepath;
    private Boolean manualmembership;
    private Integer membershiprestriction;
    private Integer remotestaginggroupcount;

    public AbstractGroup_() {}

    public AbstractGroup_(AbstractGroup_ value) {
        this.groupid = value.groupid;
        this.companyid = value.companyid;
        this.creatoruserid = value.creatoruserid;
        this.classnameid = value.classnameid;
        this.classpk = value.classpk;
        this.parentgroupid = value.parentgroupid;
        this.livegroupid = value.livegroupid;
        this.name = value.name;
        this.description = value.description;
        this.type_ = value.type_;
        this.typesettings = value.typesettings;
        this.friendlyurl = value.friendlyurl;
        this.active_ = value.active_;
        this.site = value.site;
        this.uuid_ = value.uuid_;
        this.treepath = value.treepath;
        this.manualmembership = value.manualmembership;
        this.membershiprestriction = value.membershiprestriction;
        this.remotestaginggroupcount = value.remotestaginggroupcount;
    }

    public AbstractGroup_(
            Long    groupid,
            Long    companyid,
            Long    creatoruserid,
            Long    classnameid,
            Long    classpk,
            Long    parentgroupid,
            Long    livegroupid,
            String  name,
            String  description,
            Integer type_,
            String  typesettings,
            String  friendlyurl,
            Boolean active_,
            Boolean site,
            String  uuid_,
            String  treepath,
            Boolean manualmembership,
            Integer membershiprestriction,
            Integer remotestaginggroupcount
    ) {
        this.groupid = groupid;
        this.companyid = companyid;
        this.creatoruserid = creatoruserid;
        this.classnameid = classnameid;
        this.classpk = classpk;
        this.parentgroupid = parentgroupid;
        this.livegroupid = livegroupid;
        this.name = name;
        this.description = description;
        this.type_ = type_;
        this.typesettings = typesettings;
        this.friendlyurl = friendlyurl;
        this.active_ = active_;
        this.site = site;
        this.uuid_ = uuid_;
        this.treepath = treepath;
        this.manualmembership = manualmembership;
        this.membershiprestriction = membershiprestriction;
        this.remotestaginggroupcount = remotestaginggroupcount;
    }

    public Long getGroupId() {
        return this.groupid;
    }

    public void setGroupId(Long groupid) {
        this.groupid = groupid;
    }

    public Long getCompanyId() {
        return this.companyid;
    }

    public void setCompanyId(Long companyid) {
        this.companyid = companyid;
    }

    public Long getCreatorUserId() {
        return this.creatoruserid;
    }

    public void setCreatorUserId(Long creatoruserid) {
        this.creatoruserid = creatoruserid;
    }

    public Long getClassNameId() {
        return this.classnameid;
    }

    public void setClassNameId(Long classnameid) {
        this.classnameid = classnameid;
    }

    public Long getClassPK() {
        return this.classpk;
    }

    public void setClassPK(Long classpk) {
        this.classpk = classpk;
    }

    public Long getParentGroupId() {
        return this.parentgroupid;
    }

    public void setParentGroupId(Long parentgroupid) {
        this.parentgroupid = parentgroupid;
    }

    public Long getLiveGroupId() {
        return this.livegroupid;
    }

    public void setLiveGroupId(Long livegroupid) {
        this.livegroupid = livegroupid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType_() {
        return this.type_;
    }

    public void setType_(Integer type_) {
        this.type_ = type_;
    }

    public String getTypeSettings() {
        return this.typesettings;
    }

    public void setTypeSettings(String typesettings) {
        this.typesettings = typesettings;
    }

    public String getFriendlyURL() {
        return this.friendlyurl;
    }

    public void setFriendlyURL(String friendlyurl) {
        this.friendlyurl = friendlyurl;
    }

    public Boolean getActive_() {
        return this.active_;
    }

    public void setActive_(Boolean active_) {
        this.active_ = active_;
    }

    public Boolean getSite() {
        return this.site;
    }

    public void setSite(Boolean site) {
        this.site = site;
    }

    public String getUuid_() {
        return this.uuid_;
    }

    public void setUuid_(String uuid_) {
        this.uuid_ = uuid_;
    }

    public String getTreePath() {
        return this.treepath;
    }

    public void setTreePath(String treepath) {
        this.treepath = treepath;
    }

    public Boolean getManualMembership() {
        return this.manualmembership;
    }

    public void setManualMembership(Boolean manualmembership) {
        this.manualmembership = manualmembership;
    }

    public Integer getMembershipRestriction() {
        return this.membershiprestriction;
    }

    public void setMembershipRestriction(Integer membershiprestriction) {
        this.membershiprestriction = membershiprestriction;
    }

    public Integer getRemoteStagingGroupCount() {
        return this.remotestaginggroupcount;
    }

    public void setRemoteStagingGroupCount(Integer remotestaginggroupcount) {
        this.remotestaginggroupcount = remotestaginggroupcount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final AbstractGroup_ other = (AbstractGroup_) obj;
        if (groupid == null) {
            if (other.groupid != null)
                return false;
        }
        else if (!groupid.equals(other.groupid))
            return false;
        if (companyid == null) {
            if (other.companyid != null)
                return false;
        }
        else if (!companyid.equals(other.companyid))
            return false;
        if (creatoruserid == null) {
            if (other.creatoruserid != null)
                return false;
        }
        else if (!creatoruserid.equals(other.creatoruserid))
            return false;
        if (classnameid == null) {
            if (other.classnameid != null)
                return false;
        }
        else if (!classnameid.equals(other.classnameid))
            return false;
        if (classpk == null) {
            if (other.classpk != null)
                return false;
        }
        else if (!classpk.equals(other.classpk))
            return false;
        if (parentgroupid == null) {
            if (other.parentgroupid != null)
                return false;
        }
        else if (!parentgroupid.equals(other.parentgroupid))
            return false;
        if (livegroupid == null) {
            if (other.livegroupid != null)
                return false;
        }
        else if (!livegroupid.equals(other.livegroupid))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        }
        else if (!description.equals(other.description))
            return false;
        if (type_ == null) {
            if (other.type_ != null)
                return false;
        }
        else if (!type_.equals(other.type_))
            return false;
        if (typesettings == null) {
            if (other.typesettings != null)
                return false;
        }
        else if (!typesettings.equals(other.typesettings))
            return false;
        if (friendlyurl == null) {
            if (other.friendlyurl != null)
                return false;
        }
        else if (!friendlyurl.equals(other.friendlyurl))
            return false;
        if (active_ == null) {
            if (other.active_ != null)
                return false;
        }
        else if (!active_.equals(other.active_))
            return false;
        if (site == null) {
            if (other.site != null)
                return false;
        }
        else if (!site.equals(other.site))
            return false;
        if (uuid_ == null) {
            if (other.uuid_ != null)
                return false;
        }
        else if (!uuid_.equals(other.uuid_))
            return false;
        if (treepath == null) {
            if (other.treepath != null)
                return false;
        }
        else if (!treepath.equals(other.treepath))
            return false;
        if (manualmembership == null) {
            if (other.manualmembership != null)
                return false;
        }
        else if (!manualmembership.equals(other.manualmembership))
            return false;
        if (membershiprestriction == null) {
            if (other.membershiprestriction != null)
                return false;
        }
        else if (!membershiprestriction.equals(other.membershiprestriction))
            return false;
        if (remotestaginggroupcount == null) {
            if (other.remotestaginggroupcount != null)
                return false;
        }
        else if (!remotestaginggroupcount.equals(other.remotestaginggroupcount))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((groupid == null) ? 0 : groupid.hashCode());
        result = prime * result + ((companyid == null) ? 0 : companyid.hashCode());
        result = prime * result + ((creatoruserid == null) ? 0 : creatoruserid.hashCode());
        result = prime * result + ((classnameid == null) ? 0 : classnameid.hashCode());
        result = prime * result + ((classpk == null) ? 0 : classpk.hashCode());
        result = prime * result + ((parentgroupid == null) ? 0 : parentgroupid.hashCode());
        result = prime * result + ((livegroupid == null) ? 0 : livegroupid.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((type_ == null) ? 0 : type_.hashCode());
        result = prime * result + ((typesettings == null) ? 0 : typesettings.hashCode());
        result = prime * result + ((friendlyurl == null) ? 0 : friendlyurl.hashCode());
        result = prime * result + ((active_ == null) ? 0 : active_.hashCode());
        result = prime * result + ((site == null) ? 0 : site.hashCode());
        result = prime * result + ((uuid_ == null) ? 0 : uuid_.hashCode());
        result = prime * result + ((treepath == null) ? 0 : treepath.hashCode());
        result = prime * result + ((manualmembership == null) ? 0 : manualmembership.hashCode());
        result = prime * result + ((membershiprestriction == null) ? 0 : membershiprestriction.hashCode());
        result = prime * result + ((remotestaginggroupcount == null) ? 0 : remotestaginggroupcount.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Group_ (");

        sb.append(groupid);
        sb.append(", ").append(companyid);
        sb.append(", ").append(creatoruserid);
        sb.append(", ").append(classnameid);
        sb.append(", ").append(classpk);
        sb.append(", ").append(parentgroupid);
        sb.append(", ").append(livegroupid);
        sb.append(", ").append(name);
        sb.append(", ").append(description);
        sb.append(", ").append(type_);
        sb.append(", ").append(typesettings);
        sb.append(", ").append(friendlyurl);
        sb.append(", ").append(active_);
        sb.append(", ").append(site);
        sb.append(", ").append(uuid_);
        sb.append(", ").append(treepath);
        sb.append(", ").append(manualmembership);
        sb.append(", ").append(membershiprestriction);
        sb.append(", ").append(remotestaginggroupcount);

        sb.append(")");
        return sb.toString();
    }
}
