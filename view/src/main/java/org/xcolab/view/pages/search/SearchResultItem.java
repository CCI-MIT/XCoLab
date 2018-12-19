package org.xcolab.view.pages.search;

import org.xcolab.client.search.pojo.ISearchPojo;
import org.xcolab.view.pages.search.items.AbstractSearchItem;

import java.util.ArrayList;
import java.util.List;

public class SearchResultItem {

    private final boolean isVisible;
    private SearchItemType itemType;
    private String content;
    private String title;
    private String url;
    private boolean odd;

    public SearchResultItem(ISearchPojo searchPojo, String query, boolean odd) {

        for (SearchItemType type : SearchItemType.values()) {
            if (type.isOfGivenType(searchPojo)) {
                itemType = type;
                break;
            }
        }
        isVisible = true;

        if (itemType != null) {
            AbstractSearchItem searchItem = itemType.getSearchItem();
            searchItem.init(searchPojo, query);
            content = searchItem.getContent();
            url = searchItem.getLinkUrl();
            title = searchItem.getTitle();
        }
        this.odd = odd;
    }


    public List<Pair> getValues() {
        return new ArrayList<>();
    }

    public String getContent() {
        return content;
    }

    public SearchItemType getItemType() {
        return itemType;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public boolean isOdd() {
        return odd;
    }

    public void setOdd(boolean odd) {
        this.odd = odd;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public static class Pair {

        private String key;
        private String value;

        public Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
