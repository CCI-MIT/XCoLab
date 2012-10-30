package org.xcolab.core.documententity.impl;

import java.util.Calendar;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;
import javax.jcr.version.Version;

import org.xcolab.core.documententity.DocumentEntity;
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

	public Long getAuthor() {
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
	


}
