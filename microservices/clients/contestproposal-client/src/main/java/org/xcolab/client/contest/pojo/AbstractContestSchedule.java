package org.xcolab.client.contest.pojo;

import java.io.Serializable;

public abstract class AbstractContestSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private String status;
    private Long basescheduleid;

    public AbstractContestSchedule() {}

    public AbstractContestSchedule(AbstractContestSchedule value) {
        this.id = value.id;
        this.name = value.name;
        this.description = value.description;
        this.status = value.status;
        this.basescheduleid = value.basescheduleid;
    }

    public AbstractContestSchedule(Long id, String name, String description,
            String status, Long basescheduleid) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.basescheduleid = basescheduleid;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getBaseScheduleId() {
        return this.basescheduleid;
    }

    public void setBaseScheduleId(Long basescheduleid) {
        this.basescheduleid = basescheduleid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((basescheduleid == null) ? 0 : basescheduleid.hashCode());
        return result;
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
        final AbstractContestSchedule other = (AbstractContestSchedule) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (status == null) {
            if (other.status != null) {
                return false;
            }
        } else if (!status.equals(other.status)) {
            return false;
        }
        if (basescheduleid == null) {
            if (other.basescheduleid != null) {
                return false;
            }
        } else if (!basescheduleid.equals(other.basescheduleid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "ContestSchedule (" + id +
                ", " + name +
                ", " + description +
                ", " + status +
                ", " + basescheduleid +
                ")";
    }
}
