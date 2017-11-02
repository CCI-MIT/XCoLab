package org.xcolab.client.members.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Generated;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class PlatformTeam implements Serializable {

    private static final long serialVersionUID = 741848212;

    public static final TypeProvider<PlatformTeam> TYPES =
            new TypeProvider<>(PlatformTeam.class,
                    new ParameterizedTypeReference<List<PlatformTeam>>() {
                    });

    private Long   id_;
    private String name;

    public PlatformTeam() {}

    public PlatformTeam(PlatformTeam value) {
        this.id_ = value.id_;
        this.name = value.name;
    }

    public PlatformTeam(
            Long   id_,
            String name
    ) {
        this.id_ = id_;
        this.name = name;
    }

    public Long getId_() {
        return this.id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PlatformTeam other = (PlatformTeam) obj;
        if (id_ == null) {
            if (other.id_ != null)
                return false;
        }
        else if (!id_.equals(other.id_))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PlatformTeam (");

        sb.append(id_);
        sb.append(", ").append(name);

        sb.append(")");
        return sb.toString();
    }
}

