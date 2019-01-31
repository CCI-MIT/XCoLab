package org.xcolab.client.user.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleWrapper extends org.xcolab.client.user.pojo.tables.pojos.Role  {


	public RoleWrapper() {}

	public RoleWrapper(RoleWrapper value) {
		this.setId(value.getId());
		this.setName(value.getName());
		this.setCreatedAt(value.getCreatedAt());

	}

    public RoleWrapper(Long id, String name, Timestamp createdAt) {
	    this.setId(id);
		this.setName(name);
		this.setCreatedAt(createdAt);
	}


}
