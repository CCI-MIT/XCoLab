package org.xcolab.portlets.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Query;

public class SearchResultItem {
    private SearchItemType itemType;
    private final Map<String, Field> fields;
    private String content;
    private String title;
    private String url;
    private boolean odd;

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
    
    public SearchResultItem(Document doc, Query query, org.apache.lucene.search.Query luceneQuery, boolean odd) 
            throws ParseException, IOException, com.liferay.portal.kernel.search.ParseException, InvalidTokenOffsetsException {
        fields = doc.getFields();
        for (SearchItemType type: SearchItemType.values()) {
            if (type.isOfGivenType(doc)) {
                itemType = type;
                break;
            }
        }
        
        QueryScorer scorer = new QueryScorer(luceneQuery);
        Formatter formatter = new SimpleHTMLFormatter();
        
        Highlighter highlighter = new Highlighter(formatter, scorer);
        highlighter.getBestFragment(new StandardAnalyzer(Version.LUCENE_34), "firstName", doc.get("firstName"));
        if (itemType != null) {
            content = itemType.getContent(doc, highlighter);
            url = itemType.getUrl(doc);
            title = itemType.getTitle(doc, highlighter);
        }
        this.odd = odd;
    }
    
    public List<Pair> getValues() {
        List<Pair> pairs = new ArrayList<>();
        for (String fieldName: fields.keySet()) {
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

    public void setOdd(boolean odd) {
        this.odd = odd;
    }

    public boolean isOdd() {
        return odd;
    }    

}
