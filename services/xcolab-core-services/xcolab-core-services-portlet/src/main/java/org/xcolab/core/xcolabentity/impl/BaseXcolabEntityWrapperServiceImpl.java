package org.xcolab.core.xcolabentity.impl;

import java.util.List;

import org.xcolab.core.xcolabententity.EntityVersion;
import org.xcolab.core.xcolabententity.XColabEntityException;
import org.xcolab.core.xcolabententity.XColabEntityService;
import org.xcolab.core.xcolabententity.XColabEntityWrapper;
import org.xcolab.core.xcolabententity.XColabEntityWrapperService;

public abstract class BaseXcolabEntityWrapperServiceImpl<T extends XColabEntityWrapper, IT extends T> 
implements XColabEntityWrapperService<T> {

	protected XColabEntityService documentEntityService;
	
	public BaseXcolabEntityWrapperServiceImpl(XColabEntityService documentEntityService) {
		this.documentEntityService = documentEntityService;
	}

	public T findEntity(Object id) throws XColabEntityException {
		 return documentEntityService.findDocumentEntity(id, getEntityImplType());
	}

	public List<T> findAllEntities() throws XColabEntityException {
		return documentEntityService.findDocumentEntities(getContextName(), getEntityType(), getEntityImplType());
	}

	public T createEntity() throws XColabEntityException {
		return documentEntityService.createEntity(getContextName(), getEntityImplType());
	}

	public void updateEntity(T entity) throws XColabEntityException {
		documentEntityService.update(entity);
	}

	public List<EntityVersion<T>> getVersions(T entity) throws XColabEntityException {
		return documentEntityService.getVersions(entity, getEntityType(), getEntityImplType());
	}

	protected abstract String getContextName();
	protected abstract Class<T> getEntityType();
	protected abstract Class<IT> getEntityImplType();
	
	
}
