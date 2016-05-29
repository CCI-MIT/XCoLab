package org.xcolab.portlets.search;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Query;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;
import org.xcolab.portlets.search.items.AbstractSearchItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchResultItem {
    private final Map<String, Field> fields;
    private final boolean isVisible;
    private SearchItemType itemType;
    private String content;
    private String title;
    private String url;
    private boolean odd;

    public SearchResultItem(Document doc, Query query, org.apache.lucene.search.Query luceneQuery, boolean odd)
            throws ParseException, IOException, com.liferay.portal.kernel.search.ParseException,
            InvalidTokenOffsetsException, SystemException {
        fields = doc.getFields();
        for (SearchItemType type : SearchItemType.values()) {
            if (type.isOfGivenType(doc)) {
                itemType = type;
                break;
            }
        }
        isVisible = itemType.getSearchItem().isVisible(doc);

        QueryScorer scorer = new QueryScorer(luceneQuery);
        Formatter formatter = new SimpleHTMLFormatter();

        Highlighter highlighter = new Highlighter(formatter, scorer);
        highlighter.getBestFragment(new StandardAnalyzer(Version.LUCENE_34), "firstName", doc.get("firstName"));
        if (itemType != null) {
            AbstractSearchItem searchItem = itemType.getSearchItem();
            content = searchItem.getContent(doc, highlighter);
            url = searchItem.getLinkUrl(doc);
            title = searchItem.getTitle(doc, highlighter);
        }
        this.odd = odd;
    }

    public List<Pair> getValues() {
        List<Pair> pairs = new ArrayList<>();
        for (String fieldName : fields.keySet()) {
            pairs.add(new Pair(fieldName, fields.get(fieldName).getValue()));
        }

        return pairs;
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

    public class Pair {
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
