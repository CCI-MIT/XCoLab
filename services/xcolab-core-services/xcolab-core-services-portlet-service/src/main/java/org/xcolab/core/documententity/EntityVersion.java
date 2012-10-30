package org.xcolab.core.documententity;

import java.util.Calendar;

public interface EntityVersion<T extends DocumentEntity> {
	Calendar getCreated();
	Long getAuthor();
	String[] getTags();
	T getEntity();
	
	
}
