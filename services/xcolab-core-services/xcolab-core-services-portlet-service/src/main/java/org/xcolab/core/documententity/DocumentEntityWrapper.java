package org.xcolab.core.documententity;

public interface DocumentEntityWrapper extends DocumentEntity {
	DocumentEntity getWrapped();

	<T extends DocumentEntityWrapper> T getReference(String property, Class<T> type) throws DocumentEntityException;
	<LT extends DocumentEntityWrapper, IT extends LT> LT[] getReferences(String property, Class<LT> listType, Class<IT> itemType) throws DocumentEntityException;

    public <LT extends DocumentEntityWrapper, IT extends LT> LT[] getChildren(String property, Class<LT> listType, Class<IT> itemType ) 
            throws DocumentEntityException;
}
