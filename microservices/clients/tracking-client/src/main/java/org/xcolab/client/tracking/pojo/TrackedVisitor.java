package org.xcolab.client.tracking.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class TrackedVisitor implements Serializable {

    public static final TypeProvider<TrackedVisitor> TYPES = new TypeProvider<>(TrackedVisitor.class,
            new ParameterizedTypeReference<List<TrackedVisitor>>() {});

    private static final long serialVersionUID = -366279332;

    private Long id_;
    private String uuid_;
    private Long userId;
    private Timestamp createDate;

    public TrackedVisitor() {
    }

    public TrackedVisitor(TrackedVisitor value) {
        this.id_ = value.id_;
        this.uuid_ = value.uuid_;
        this.userId = value.userId;
        this.createDate = value.createDate;
    }

    public TrackedVisitor(Long id_, String uuid_, Long userId, Timestamp createDate) {
        this.id_ = id_;
        this.uuid_ = uuid_;
        this.userId = userId;
        this.createDate = createDate;
    }

    public Long getId_() {
        return this.id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
    }

    public String getUuid_() {
        return this.uuid_;
    }

    public void setUuid_(String uuid_) {
        this.uuid_ = uuid_;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
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
        final TrackedVisitor other = (TrackedVisitor) obj;
        if (id_ == null) {
            if (other.id_ != null) {
                return false;
            }
        } else if (!id_.equals(other.id_)) {
            return false;
        }
        if (uuid_ == null) {
            if (other.uuid_ != null) {
                return false;
            }
        } else if (!uuid_.equals(other.uuid_)) {
            return false;
        }
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }
        if (createDate == null) {
            if (other.createDate != null) {
                return false;
            }
        } else if (!createDate.equals(other.createDate)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
        result = prime * result + ((uuid_ == null) ? 0 : uuid_.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "TrackedVisitor2User (" + id_ +
                ", " + uuid_ +
                ", " + userId +
                ", " + createDate +
                ")";
    }
}
