package org.xcolab.core.documententity.impl;

import java.util.List;

import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.DocumentEntityService;
import org.xcolab.core.documententity.DocumentEntityWrapper;
import org.xcolab.core.documententity.DocumentEntityWrapperService;
import org.xcolab.core.documententity.EntityVersion;

public abstract class BaseDocumentEntityWrapperServiceImpl<T extends DocumentEntityWrapper, IT extends T> 
implements DocumentEntityWrapperService<T> {

	protected DocumentEntityService documentEntityService;
	
	public BaseDocumentEntityWrapperServiceImpl(DocumentEntityService documentEntityService) {
		this.documentEntityService = documentEntityService;
	}

	public T getEntity(Object id) throws DocumentEntityException {
		 return documentEntityService.findDocumentEntity(id, getEntityImplType());
	}

	public List<T> getAllEntities() throws DocumentEntityException {
		return documentEntityService.findDocumentEntities(getContextName(), getEntityType(), getEntityImplType());
	}

	public T createEntity() throws DocumentEntityException {
		return documentEntityService.createEntity(getContextName(), getEntityImplType());
	}

	public void updateEntity(T entity) throws DocumentEntityException {
		documentEntityService.update(entity);
	}

	public List<EntityVersion<T>> getVersions(T entity) throws DocumentEntityException {
		return documentEntityService.getVersions(entity, getEntityType(), getEntityImplType());
	}

	protected abstract String getContextName();
	protected abstract Class<T> getEntityType();
	protected abstract Class<IT> getEntityImplType();
	
	
}
