package org.xcolab.client.filtering.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FilteredEntry implements Serializable {

    private static final long serialVersionUID = -1261366948;

    public static final TypeProvider<FilteredEntry> TYPES =
            new TypeProvider<>(FilteredEntry.class,
                    new ParameterizedTypeReference<List<FilteredEntry>>() {
                    });

    private Long      filteredentryid;
    private Long      source;
    private Long      sourceid;
    private Long      authorid;
    private String    fulltext;
    private Integer   status;
    private Timestamp createdat;
    private Timestamp answeredat;
    private String    responsefulltext;
    private String    uuid;

    public FilteredEntry() {}

    public FilteredEntry(FilteredEntry value) {
        this.filteredentryid = value.filteredentryid;
        this.source = value.source;
        this.sourceid = value.sourceid;
        this.authorid = value.authorid;
        this.fulltext = value.fulltext;
        this.status = value.status;
        this.createdat = value.createdat;
        this.answeredat = value.answeredat;
        this.responsefulltext = value.responsefulltext;
        this.uuid = value.uuid;
    }

    public FilteredEntry(
        Long      filteredentryid,
        Long      source,
        Long      sourceid,
        Long      authorid,
        String    fulltext,
        Integer   status,
        Timestamp createdat,
        Timestamp answeredat,
        String    responsefulltext,
        String    uuid
    ) {
        this.filteredentryid = filteredentryid;
        this.source = source;
        this.sourceid = sourceid;
        this.authorid = authorid;
        this.fulltext = fulltext;
        this.status = status;
        this.createdat = createdat;
        this.answeredat = answeredat;
        this.responsefulltext = responsefulltext;
        this.uuid = uuid;
    }

    public Long getFilteredEntryId() {
        return this.filteredentryid;
    }

    public void setFilteredEntryId(Long filteredentryid) {
        this.filteredentryid = filteredentryid;
    }

    public Long getSource() {
        return this.source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getSourceId() {
        return this.sourceid;
    }

    public void setSourceId(Long sourceid) {
        this.sourceid = sourceid;
    }

    public Long getAuthorId() {
        return this.authorid;
    }

    public void setAuthorId(Long authorid) {
        this.authorid = authorid;
    }

    public String getFullText() {
        return this.fulltext;
    }

    public void setFullText(String fulltext) {
        this.fulltext = fulltext;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return this.createdat;
    }

    public void setCreatedAt(Timestamp createdat) {
        this.createdat = createdat;
    }

    public Timestamp getAnsweredAt() {
        return this.answeredat;
    }

    public void setAnsweredAt(Timestamp answeredat) {
        this.answeredat = answeredat;
    }

    public String getResponseFullText() {
        return this.responsefulltext;
    }

    public void setResponseFullText(String responsefulltext) {
        this.responsefulltext = responsefulltext;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FilteredEntry (");

        sb.append(filteredentryid);
        sb.append(", ").append(source);
        sb.append(", ").append(sourceid);
        sb.append(", ").append(authorid);
        sb.append(", ").append(fulltext);
        sb.append(", ").append(status);
        sb.append(", ").append(createdat);
        sb.append(", ").append(answeredat);
        sb.append(", ").append(responsefulltext);
        sb.append(", ").append(uuid);

        sb.append(")");
        return sb.toString();
    }
}
