package org.xcolab.view.seo.sitemaps.xml;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(value = XmlAccessType.NONE)
@XmlRootElement(name = "url")
public class XmlUrl {

    @XmlElement(required = true)
    private String loc;

    @XmlElement
    private String lastmod;

    @XmlElement
    private String changefreq;

    @XmlElement
    private String priority;

    public XmlUrl() {

    }

    public XmlUrl(String location, LocalDateTime lastModified,
            ChangeFrequency changeFrequency, Priority priority) {
        this.loc = location;
        if (lastModified != null) {
            this.lastmod = lastModified.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            this.lastmod = null;
        }
        if (changeFrequency != null) {
            this.changefreq = changeFrequency.getValue();
        } else {
            this.changefreq = null;
        }
        if (priority != null) {
            this.priority = priority.getValue();
        } else {
            this.priority = null;
        }

    }

    public String getLoc() {
        return loc;
    }

    public String getPriority() {
        return priority;
    }

    public String getChangefreq() {
        return changefreq;
    }

    public String getLastmod() {
        return lastmod;
    }

    public enum ChangeFrequency {
        ALWAYS,
        HOURLY,
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY,
        NEVER
        ;

        public String getValue() {
            return name().toLowerCase();
        }
    }

    public enum Priority {
        HIGHEST("1.0"),
        HIGH("0.75"),
        MEDIUM("0.5"),
        LOW("0.25");

        private final String value;

        Priority(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static class Builder {

        private final String location;
        private Priority priority = Priority.MEDIUM;
        private LocalDateTime lastModified;
        private ChangeFrequency changeFrequency;

        private Builder(String location) {
            this.location = location;
        }

        public static Builder forLocation(String location) {
            return new Builder(location);
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder lastModified(LocalDateTime lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        public Builder changeFrequency(ChangeFrequency changeFrequency) {
            this.changeFrequency = changeFrequency;
            return this;
        }

        public XmlUrl build() {
            return new XmlUrl(location, lastModified, changeFrequency, priority);
        }
    }
}
