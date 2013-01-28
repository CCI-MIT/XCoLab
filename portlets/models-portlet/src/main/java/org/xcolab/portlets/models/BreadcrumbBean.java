package org.xcolab.portlets.models;

import java.util.ArrayList;
import java.util.List;

public class BreadcrumbBean {
    public class BreadcrumbItem {
        private String name;
        private String link;

        public BreadcrumbItem(String name, String link) {
            this.name = name;
            this.link = link;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

    }

    private List<BreadcrumbItem> items = new ArrayList<BreadcrumbItem>();

    public void addItem(String name, String link) {
        items.add(new BreadcrumbItem(name, link));
    }

    public void clear() {
        items.clear();
    }

    public List<BreadcrumbItem> getItems() {
        return items;
    }

}