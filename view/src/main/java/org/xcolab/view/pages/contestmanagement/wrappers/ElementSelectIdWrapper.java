package org.xcolab.view.pages.contestmanagement.wrappers;

import org.xcolab.commons.html.LabelValue;

import java.util.List;

public class ElementSelectIdWrapper {

    private List<LabelValue> selectionItems;
    private Long elementId;

    public ElementSelectIdWrapper(Long elementId, List<LabelValue> selectionItems) {
        this.elementId = elementId;
        this.selectionItems = selectionItems;
    }

    public Long getElementId() {
        return elementId;
    }

    public void setElementId(Long elementId) {
        this.elementId = elementId;
    }

    public List<LabelValue> getSelectionItems() {
        return selectionItems;
    }

    public void setSelectionItems(List<LabelValue> selectionItems) {
        this.selectionItems = selectionItems;
    }
}
