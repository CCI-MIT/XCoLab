package org.xcolab.client.modeling.pojo;

import java.io.Serializable;

public abstract class AbstractModelOutputChartOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long modelid;
    private String modeloutputlabel;
    private Integer modeloutputchartorder;
    private String modelindexrangepolicy;
    private String modelindexrangemessage;
    private String modelindexerrorpolicy;
    private String modelindexerrormessage;
    private Boolean modelchartisvisible;

    public AbstractModelOutputChartOrder() {
    }

    public AbstractModelOutputChartOrder(AbstractModelOutputChartOrder value) {
        this.id = value.id;
        this.modelid = value.modelid;
        this.modeloutputlabel = value.modeloutputlabel;
        this.modeloutputchartorder = value.modeloutputchartorder;
        this.modelindexrangepolicy = value.modelindexrangepolicy;
        this.modelindexrangemessage = value.modelindexrangemessage;
        this.modelindexerrorpolicy = value.modelindexerrorpolicy;
        this.modelindexerrormessage = value.modelindexerrormessage;
        this.modelchartisvisible = value.modelchartisvisible;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModelId() {
        return this.modelid;
    }

    public void setModelId(Long modelid) {
        this.modelid = modelid;
    }

    public String getModelOutputLabel() {
        return this.modeloutputlabel;
    }

    public void setModelOutputLabel(String modeloutputlabel) {
        this.modeloutputlabel = modeloutputlabel;
    }

    public Integer getModelOutputChartOrder() {
        return this.modeloutputchartorder;
    }

    public void setModelOutputChartOrder(Integer modeloutputchartorder) {
        this.modeloutputchartorder = modeloutputchartorder;
    }

    public String getModelIndexRangePolicy() {
        return this.modelindexrangepolicy;
    }

    public void setModelIndexRangePolicy(String modelindexrangepolicy) {
        this.modelindexrangepolicy = modelindexrangepolicy;
    }

    public String getModelIndexRangeMessage() {
        return this.modelindexrangemessage;
    }

    public void setModelIndexRangeMessage(String modelindexrangemessage) {
        this.modelindexrangemessage = modelindexrangemessage;
    }

    public String getModelIndexErrorPolicy() {
        return this.modelindexerrorpolicy;
    }

    public void setModelIndexErrorPolicy(String modelindexerrorpolicy) {
        this.modelindexerrorpolicy = modelindexerrorpolicy;
    }

    public String getModelIndexErrorMessage() {
        return this.modelindexerrormessage;
    }

    public void setModelIndexErrorMessage(String modelindexerrormessage) {
        this.modelindexerrormessage = modelindexerrormessage;
    }

    public Boolean getModelChartIsVisible() {
        return this.modelchartisvisible;
    }

    public void setModelChartIsVisible(Boolean modelchartisvisible) {
        this.modelchartisvisible = modelchartisvisible;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0
                : id.hashCode());
        result = prime * result + ((modelid == null) ? 0 : modelid.hashCode());
        result = prime * result + ((modeloutputlabel == null) ? 0 : modeloutputlabel.hashCode());
        result = prime * result + ((modeloutputchartorder == null) ? 0
                : modeloutputchartorder.hashCode());
        result = prime * result + ((modelindexrangepolicy == null) ? 0
                : modelindexrangepolicy.hashCode());
        result = prime * result + ((modelindexrangemessage == null) ? 0
                : modelindexrangemessage.hashCode());
        result = prime * result + ((modelindexerrorpolicy == null) ? 0
                : modelindexerrorpolicy.hashCode());
        result = prime * result + ((modelindexerrormessage == null) ? 0
                : modelindexerrormessage.hashCode());
        result = prime * result + ((modelchartisvisible == null) ? 0
                : modelchartisvisible.hashCode());
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
        final AbstractModelOutputChartOrder other = (AbstractModelOutputChartOrder) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (modelid == null) {
            if (other.modelid != null) {
                return false;
            }
        } else if (!modelid.equals(other.modelid)) {
            return false;
        }
        if (modeloutputlabel == null) {
            if (other.modeloutputlabel != null) {
                return false;
            }
        } else if (!modeloutputlabel.equals(other.modeloutputlabel)) {
            return false;
        }
        if (modeloutputchartorder == null) {
            if (other.modeloutputchartorder != null) {
                return false;
            }
        } else if (!modeloutputchartorder.equals(other.modeloutputchartorder)) {
            return false;
        }
        if (modelindexrangepolicy == null) {
            if (other.modelindexrangepolicy != null) {
                return false;
            }
        } else if (!modelindexrangepolicy.equals(other.modelindexrangepolicy)) {
            return false;
        }
        if (modelindexrangemessage == null) {
            if (other.modelindexrangemessage != null) {
                return false;
            }
        } else if (!modelindexrangemessage.equals(other.modelindexrangemessage)) {
            return false;
        }
        if (modelindexerrorpolicy == null) {
            if (other.modelindexerrorpolicy != null) {
                return false;
            }
        } else if (!modelindexerrorpolicy.equals(other.modelindexerrorpolicy)) {
            return false;
        }
        if (modelindexerrormessage == null) {
            if (other.modelindexerrormessage != null) {
                return false;
            }
        } else if (!modelindexerrormessage.equals(other.modelindexerrormessage)) {
            return false;
        }
        if (modelchartisvisible == null) {
            if (other.modelchartisvisible != null) {
                return false;
            }
        } else if (!modelchartisvisible.equals(other.modelchartisvisible)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String sb = "ModelOutputChartOrder (" + id + ", " + modelid + ", "
                + modeloutputlabel + ", " + modeloutputchartorder + ", " + modelindexrangepolicy
                + ", " + modelindexrangemessage + ", " + modelindexerrorpolicy + ", "
                + modelindexerrormessage + ", " + modelchartisvisible + ")";

        return sb;
    }
}
