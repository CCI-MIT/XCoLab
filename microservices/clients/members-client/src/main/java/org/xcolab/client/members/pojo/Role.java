package org.xcolab.client.members.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Role implements Serializable {

	private static final long serialVersionUID = -416641117;

	public static final TypeProvider<Role> TYPES =
			new TypeProvider<>(Role.class,
					new ParameterizedTypeReference<List<Role>>() {
					});

	private Long id;
	private String    name;
	private Timestamp createdAt;

	public Role() {}

	public Role(Role value) {
		this.id = value.id;
		this.name = value.name;
		this.createdAt = value.createdAt;
	}

    public Role(Long id, String name, Timestamp createdAt) {
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(getName(), role.getName()) && Objects
                .equals(getCreatedAt(), role.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getName(), getCreatedAt());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("createdAt", createdAt)
                .toString();
    }
}
