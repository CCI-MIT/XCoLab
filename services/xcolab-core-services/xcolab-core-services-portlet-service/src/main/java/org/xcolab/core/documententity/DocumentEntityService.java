package org.xcolab.core.documententity;

import java.util.List;

public interface DocumentEntityService {
	DocumentEntity findDocumentEntity(Object id) throws DocumentEntityException;
	<IT extends DocumentEntityWrapper> IT findDocumentEntity(Object id, Class<IT> itemType) throws DocumentEntityException;
	
	List<DocumentEntity> findDocumentEntities(String context) throws DocumentEntityException;
	<LT extends DocumentEntityWrapper, IT extends LT> List<LT> findDocumentEntities(String context, Class<LT> listType, Class<IT> itemType) throws DocumentEntityException;
	
	DocumentEntity createEntity(String context) throws DocumentEntityException;
	<IT extends DocumentEntityWrapper> IT createEntity(String context, Class<IT> itemType) throws DocumentEntityException;  
	
	void update(DocumentEntity wrapped) throws DocumentEntityException;
	<IT extends DocumentEntityWrapper> void update(IT wrapped) throws DocumentEntityException;
	
	<T extends DocumentEntity> List<EntityVersion<T>> getVersions(T entity) throws DocumentEntityException;
	<LT extends DocumentEntityWrapper, IT extends LT> List<EntityVersion<LT>> getVersions(DocumentEntity entity, Class<LT> listType, Class<IT> itemType) throws DocumentEntityException;
}
