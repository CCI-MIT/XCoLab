package org.xcolab.core.documententity.impl;

import java.util.Calendar;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;
import javax.jcr.version.Version;

import org.xcolab.core.documententity.DocumentEntity;
import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.EntityVersion;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class EntityVersionImpl<T extends DocumentEntity> implements EntityVersion<T> {
	private final static Log _log = LogFactoryUtil.getLog(EntityVersionImpl.class);
	
	private final Version version;
	private final T entity;
	
	public EntityVersionImpl(Version version, T entity) {
		this.version = version;
		this.entity = entity;
	}

	public Calendar getCreated() {
		try {
			return version.getCreated();
		}
		catch (RepositoryException e) {
			_log.error("Can't get create date for version: " + version, e);
		}
		return null;
	} 

	public Object getAuthor() {
		try {
			return JcrVersionUtils.getVersionAuthor(version.getFrozenNode());
		} catch (ValueFormatException e) {
			_log.error("Can't get author for version: " + version, e);
		} catch (PathNotFoundException e) {
			_log.error("Can't get author for version: " + version, e);
		} catch (RepositoryException e) {
			_log.error("Can't get author for version: " + version, e);
		}
		return null;
	}

	public String[] getTags() {
		try {
			return JcrVersionUtils.getModifiedProperties(version.getFrozenNode());
		} catch (ValueFormatException e) {
			_log.error("Can't get tags for version: " + version, e);
		} catch (IllegalStateException e) {
			_log.error("Can't get tags for version: " + version, e);
		} catch (RepositoryException e) {
			_log.error("Can't get tags for version: " + version, e);
		}
		return null;
	}

	public T getEntity() {
		return entity;
	}

    @Override
    public boolean isLatest() throws DocumentEntityException {
        try {
            System.out.println("successors: " + version.getSuccessors().length);
            return version.getSuccessors().length == 0;
        }
        catch (RepositoryException e) {
            throw new DocumentEntityException(e);
        }
    }
    
    @Override
    public boolean isFirst() throws DocumentEntityException {
        try {
            System.out.println("predecessors: " + version.getPredecessors().length);
            return version.getPredecessors().length == 0;
        }
        catch (RepositoryException e) {
            throw new DocumentEntityException(e);
        }
    }

    @Override
    public EntityVersion<T> getPrev() throws DocumentEntityException {
        try {
            Version[] versions = version.getPredecessors();
            if (versions.length == 0) return null;
            
            return new EntityVersionImpl(versions[0], new DocumentEntityImpl(versions[0].getFrozenNode()));
        }
        catch (RepositoryException e) {
            throw new DocumentEntityException(e);
        }
    }

    @Override
    public EntityVersion<T> getNext() throws DocumentEntityException {
        try {
            Version[] versions = version.getSuccessors();
            if (versions.length == 0) return null;
            
            return new EntityVersionImpl(versions[0], new DocumentEntityImpl(versions[0].getFrozenNode()));
        }
        catch (RepositoryException e) {
            throw new DocumentEntityException(e);
        }
    }
	


}
