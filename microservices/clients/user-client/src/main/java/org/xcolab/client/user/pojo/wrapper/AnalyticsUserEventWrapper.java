package org.xcolab.client.user.pojo.wrapper;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.user.pojo.IAnalyticsUserEvent;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class AnalyticsUserEventWrapper extends org.xcolab.client.user.pojo.tables.pojos.AnalyticsUserEvent {

    private static final long serialVersionUID = 899448763;

    public static final TypeProvider<AnalyticsUserEventWrapper> TYPES = new TypeProvider<>(
            AnalyticsUserEventWrapper.class, new ParameterizedTypeReference<List<AnalyticsUserEventWrapper>>() {});

    private Long userId;
    private String idString;
    private String category;
    private String action;
    private String label;
    private Integer value;
    private Timestamp createdAt;

    public AnalyticsUserEventWrapper() {
    }

    public AnalyticsUserEventWrapper(AnalyticsUserEventWrapper value) {
        this.userId = value.userId;
        this.idString = value.idString;
        this.category = value.category;
        this.action = value.action;
        this.label = value.label;
        this.value = value.value;
        this.createdAt = value.createdAt;
    }

    public AnalyticsUserEventWrapper(Long userId, String idString, String category, String action,
            String label, Integer value, Timestamp createdAt) {
        this.userId = userId;
        this.idString = idString;
        this.category = category;
        this.action = action;
        this.label = label;
        this.value = value;
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIdString() {
        return this.idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
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
        final AnalyticsUserEventWrapper other = (AnalyticsUserEventWrapper) obj;
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }
        if (idString == null) {
            if (other.idString != null) {
                return false;
            }
        } else if (!idString.equals(other.idString)) {
            return false;
        }
        if (category == null) {
            if (other.category != null) {
                return false;
            }
        } else if (!category.equals(other.category)) {
            return false;
        }
        if (action == null) {
            if (other.action != null) {
                return false;
            }
        } else if (!action.equals(other.action)) {
            return false;
        }
        if (label == null) {
            if (other.label != null) {
                return false;
            }
        } else if (!label.equals(other.label)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        if (createdAt == null) {
            if (other.createdAt != null) {
                return false;
            }
        } else if (!createdAt.equals(other.createdAt)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((idString == null) ? 0 : idString.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((action == null) ? 0 : action.hashCode());
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "AnalyticsUserEvent (" + userId +
                ", " + idString +
                ", " + category +
                ", " + action +
                ", " + label +
                ", " + value +
                ", " + createdAt +
                ")";
    }
}
