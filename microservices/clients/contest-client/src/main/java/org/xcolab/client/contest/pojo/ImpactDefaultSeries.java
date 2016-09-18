/**
 * This class is generated by jOOQ
 */
package org.xcolab.client.contest.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.core.ParameterizedTypeReference;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImpactDefaultSeries implements Serializable {

    private static final long serialVersionUID = 424241675;

    public static final TypeProvider<ImpactDefaultSeries> TYPES =
            new TypeProvider<>(ImpactDefaultSeries.class,
                    new ParameterizedTypeReference<List<ImpactDefaultSeries>>() {
                    });

    private Long    seriesid;
    private String  name;
    private String  description;
    private Long    focusareaid;
    private Boolean visible;
    private Boolean editable;

    public ImpactDefaultSeries() {}

    public ImpactDefaultSeries(ImpactDefaultSeries value) {
        this.seriesid = value.seriesid;
        this.name = value.name;
        this.description = value.description;
        this.focusareaid = value.focusareaid;
        this.visible = value.visible;
        this.editable = value.editable;
    }

    public ImpactDefaultSeries(
        Long    seriesid,
        String  name,
        String  description,
        Long    focusareaid,
        Boolean visible,
        Boolean editable
    ) {
        this.seriesid = seriesid;
        this.name = name;
        this.description = description;
        this.focusareaid = focusareaid;
        this.visible = visible;
        this.editable = editable;
    }

    public Long getSeriesId() {
        return this.seriesid;
    }

    public void setSeriesId(Long seriesid) {
        this.seriesid = seriesid;
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

    public Long getFocusAreaId() {
        return this.focusareaid;
    }

    public void setFocusAreaId(Long focusareaid) {
        this.focusareaid = focusareaid;
    }

    public Boolean getVisible() {
        return this.visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getEditable() {
        return this.editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ImpactDefaultSeries other = (ImpactDefaultSeries) obj;
        if (seriesid == null) {
            if (other.seriesid != null)
                return false;
        }
        else if (!seriesid.equals(other.seriesid))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        }
        else if (!description.equals(other.description))
            return false;
        if (focusareaid == null) {
            if (other.focusareaid != null)
                return false;
        }
        else if (!focusareaid.equals(other.focusareaid))
            return false;
        if (visible == null) {
            if (other.visible != null)
                return false;
        }
        else if (!visible.equals(other.visible))
            return false;
        if (editable == null) {
            if (other.editable != null)
                return false;
        }
        else if (!editable.equals(other.editable))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((seriesid == null) ? 0 : seriesid.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((focusareaid == null) ? 0 : focusareaid.hashCode());
        result = prime * result + ((visible == null) ? 0 : visible.hashCode());
        result = prime * result + ((editable == null) ? 0 : editable.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ImpactDefaultSeries (");

        sb.append(seriesid);
        sb.append(", ").append(name);
        sb.append(", ").append(description);
        sb.append(", ").append(focusareaid);
        sb.append(", ").append(visible);
        sb.append(", ").append(editable);

        sb.append(")");
        return sb.toString();
    }
}
