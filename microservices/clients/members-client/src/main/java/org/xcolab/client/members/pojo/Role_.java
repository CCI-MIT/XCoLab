package org.xcolab.client.members.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Role_ implements Serializable {

	private static final long serialVersionUID = -416641117;

	public static final TypeProvider<Role_> TYPES =
			new TypeProvider<>(Role_.class,
					new ParameterizedTypeReference<List<Role_>>() {
					});

	private Long      roleid;
	private Long      companyid;
	private Long      classnameid;
	private Long      classpk;
	private String    name;
	private String    title;
	private String    description;
	private Integer   type_;
	private String    subtype;
	private String    uuid_;
	private Long      userid;
	private String    username;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public Role_() {}

	public Role_(Role_ value) {
		this.roleid = value.roleid;
		this.companyid = value.companyid;
		this.classnameid = value.classnameid;
		this.classpk = value.classpk;
		this.name = value.name;
		this.title = value.title;
		this.description = value.description;
		this.type_ = value.type_;
		this.subtype = value.subtype;
		this.uuid_ = value.uuid_;
		this.userid = value.userid;
		this.username = value.username;
		this.createdAt = value.createdAt;
		this.updatedAt = value.updatedAt;
	}

	public Role_(
		Long      roleid,
		Long      companyid,
		Long      classnameid,
		Long      classpk,
		String    name,
		String    title,
		String    description,
		Integer   type_,
		String    subtype,
		String    uuid_,
		Long      userid,
		String    username,
		Timestamp createdAt,
		Timestamp updatedAt
	) {
		this.roleid = roleid;
		this.companyid = companyid;
		this.classnameid = classnameid;
		this.classpk = classpk;
		this.name = name;
		this.title = title;
		this.description = description;
		this.type_ = type_;
		this.subtype = subtype;
		this.uuid_ = uuid_;
		this.userid = userid;
		this.username = username;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getRoleId() {
		return this.roleid;
	}

	public void setRoleId(Long roleid) {
		this.roleid = roleid;
	}

	public Long getCompanyId() {
		return this.companyid;
	}

	public void setCompanyId(Long companyid) {
		this.companyid = companyid;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getSubtype() {
		return this.subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getUuid_() {
		return this.uuid_;
	}

	public void setUuid_(String uuid_) {
		this.uuid_ = uuid_;
	}

	public Long getUserId() {
		return this.userid;
	}

	public void setUserId(Long userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return this.username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getupdatedAt() {
		return this.updatedAt;
	}

	public void setupdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
        String sb =
                "Role_ (" + roleid + ", " + companyid + ", " + classnameid + ", " + classpk + ", "
                        + name + ", " + title + ", " + description + ", " + type_ + ", " + subtype
                        + ", " + uuid_ + ", " + userid + ", " + username + ", " + createdAt + ", "
                        + updatedAt + ")";

        return sb;
	}
}
