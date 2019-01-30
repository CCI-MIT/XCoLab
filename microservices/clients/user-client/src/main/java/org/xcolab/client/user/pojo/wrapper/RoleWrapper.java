package org.xcolab.client.user.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleWrapper implements Serializable {

	private static final long serialVersionUID = -416641117;

	public static final TypeProvider<RoleWrapper> TYPES =
			new TypeProvider<>(RoleWrapper.class,
					new ParameterizedTypeReference<List<RoleWrapper>>() {
					});

	private Long id;
	private String    name;
	private Timestamp createdAt;

	public RoleWrapper() {}

	public RoleWrapper(RoleWrapper value) {
		this.id = value.id;
		this.name = value.name;
		this.createdAt = value.createdAt;
	}

    public RoleWrapper(Long id, String name, Timestamp createdAt) {
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
        if (!(o instanceof RoleWrapper)) {
            return false;
        }
        RoleWrapper role = (RoleWrapper) o;
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
