package org.xcolab.core.documententity;

import java.util.Calendar;

public interface EntityVersion<T extends DocumentEntity> {
	Calendar getCreated();
	Object getAuthor();
	String[] getTags();
	T getEntity();
	
	boolean isLatest() throws DocumentEntityException;
	boolean isFirst() throws DocumentEntityException;
	EntityVersion<T> getPrev() throws DocumentEntityException;
	EntityVersion<T> getNext() throws DocumentEntityException;
	
	
}
