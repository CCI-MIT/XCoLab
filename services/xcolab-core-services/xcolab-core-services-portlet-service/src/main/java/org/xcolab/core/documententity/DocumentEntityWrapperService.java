package org.xcolab.core.documententity;

import java.util.List;


public interface DocumentEntityWrapperService<T extends DocumentEntityWrapper> {
	T getEntity(Object id) throws DocumentEntityException;
	List<T> getAllEntities() throws DocumentEntityException;
	T createEntity() throws DocumentEntityException;
	void updateEntity(T entity) throws DocumentEntityException;
	List<EntityVersion<T>> getVersions(T entity) throws DocumentEntityException; 
}
