package org.xcolab.client.proposals.pojo.points;

import java.io.Serializable;

class AbstractPointType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long parentpointtypeid;
    private Double percentageofparent;
    private String distributionstrategy;
    private String receiverlimitationstrategy;
    private String name;
    private Long sort;

    public AbstractPointType() {}

    public AbstractPointType(AbstractPointType value) {
        this.id = value.id;
        this.parentpointtypeid = value.parentpointtypeid;
        this.percentageofparent = value.percentageofparent;
        this.distributionstrategy = value.distributionstrategy;
        this.receiverlimitationstrategy = value.receiverlimitationstrategy;
        this.name = value.name;
        this.sort = value.sort;
    }

    public AbstractPointType(
            Long id,
            Long parentpointtypeid,
            Double percentageofparent,
            String distributionstrategy,
            String receiverlimitationstrategy,
            String name,
            Long sort
    ) {
        this.id = id;
        this.parentpointtypeid = parentpointtypeid;
        this.percentageofparent = percentageofparent;
        this.distributionstrategy = distributionstrategy;
        this.receiverlimitationstrategy = receiverlimitationstrategy;
        this.name = name;
        this.sort = sort;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentPointTypeId() {
        return this.parentpointtypeid;
    }

    public void setParentPointTypeId(Long parentpointtypeid) {
        this.parentpointtypeid = parentpointtypeid;
    }

    public Double getPercentageOfParent() {
        return this.percentageofparent;
    }

    public void setPercentageOfParent(Double percentageofparent) {
        this.percentageofparent = percentageofparent;
    }

    public String getDistributionStrategy() {
        return this.distributionstrategy;
    }

    public void setDistributionStrategy(String distributionstrategy) {
        this.distributionstrategy = distributionstrategy;
    }

    public String getReceiverLimitationStrategy() {
        return this.receiverlimitationstrategy;
    }

    public void setReceiverLimitationStrategy(String receiverlimitationstrategy) {
        this.receiverlimitationstrategy = receiverlimitationstrategy;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSort() {
        return this.sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((parentpointtypeid == null) ? 0 : parentpointtypeid.hashCode());
        result =
                prime * result + ((percentageofparent == null) ? 0 : percentageofparent.hashCode());
        result = prime * result + ((distributionstrategy == null) ? 0
                : distributionstrategy.hashCode());
        result = prime * result + ((receiverlimitationstrategy == null) ? 0
                : receiverlimitationstrategy.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((sort == null) ? 0 : sort.hashCode());
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
        final AbstractPointType other = (AbstractPointType) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (parentpointtypeid == null) {
            if (other.parentpointtypeid != null) {
                return false;
            }
        } else if (!parentpointtypeid.equals(other.parentpointtypeid)) {
            return false;
        }
        if (percentageofparent == null) {
            if (other.percentageofparent != null) {
                return false;
            }
        } else if (!percentageofparent.equals(other.percentageofparent)) {
            return false;
        }
        if (distributionstrategy == null) {
            if (other.distributionstrategy != null) {
                return false;
            }
        } else if (!distributionstrategy.equals(other.distributionstrategy)) {
            return false;
        }
        if (receiverlimitationstrategy == null) {
            if (other.receiverlimitationstrategy != null) {
                return false;
            }
        } else if (!receiverlimitationstrategy.equals(other.receiverlimitationstrategy)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (sort == null) {
            if (other.sort != null) {
                return false;
            }
        } else if (!sort.equals(other.sort)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String sb =
                "PointType (" + id + ", " + parentpointtypeid + ", " + percentageofparent + ", "
                        + distributionstrategy + ", " + receiverlimitationstrategy + ", " + name
                        + ", " + sort + ")";

        return sb;
    }
}
