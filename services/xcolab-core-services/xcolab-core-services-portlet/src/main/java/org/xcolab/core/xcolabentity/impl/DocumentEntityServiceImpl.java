package org.xcolab.core.xcolabentity.impl;


import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

import javax.annotation.PostConstruct;
import javax.jcr.ItemExistsException;
import javax.jcr.ItemNotFoundException;
import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.RepositoryFactory;
import javax.jcr.Session;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.Version;
import javax.jcr.version.VersionException;
import javax.jcr.version.VersionHistory;
import javax.jcr.version.VersionIterator;

import org.xcolab.core.xcolabententity.EntityVersion;
import org.xcolab.core.xcolabententity.XColabEntity;
import org.xcolab.core.xcolabententity.XColabEntityException;
import org.xcolab.core.xcolabententity.XColabEntityService;
import org.xcolab.core.xcolabententity.XColabEntityWrapper;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class DocumentEntityServiceImpl implements XColabEntityService {
	private final static String JCR_REPOSITORY_CONFIG_LOCATION = "/jcr-configuration.xml";

	private Repository repo;
	private final static Log _log = LogFactoryUtil.getLog(DocumentEntityServiceImpl.class);

	public static final String XCOLAB_BASE_TYPE = "xcolab:baseType";
	private String jcrRepositoryConfig = JCR_REPOSITORY_CONFIG_LOCATION;
	
	@PostConstruct
	public void init() {
 
		URL resource = this.getClass().getResource(JCR_REPOSITORY_CONFIG_LOCATION);
		
		if (resource == null) {
			throw new RuntimeException("Can't find " + JCR_REPOSITORY_CONFIG_LOCATION + " in portlet classpath");
		}
		
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("org.modeshape.jcr.URL", resource);
		
		for (RepositoryFactory repoFactory: ServiceLoader.load(RepositoryFactory.class)) {
			repo = null;
			try {
				repo = repoFactory.getRepository(properties);
			} catch (RepositoryException e) {
				_log.error("Can't instantiate JCR repository", e);
			}
			if (repo != null) {
				break;
			}
		}		
		
		if (repo == null) {
			throw new RuntimeException("Can't instantiate JCR repository");
		}
	}

	public XColabEntity findDocumentEntity(Object id) throws XColabEntityException {
		Node node;
		try {
			Session session = getSession(); 
			node = session.getNodeByIdentifier(id.toString());
		} catch (ItemNotFoundException e) {
			throw new XColabEntityException("Can't retrieve node", e);
		} catch (RepositoryException e) {
			throw new XColabEntityException("Can't retrieve node", e);
		}
		return new XColabEntityImpl(node);
	}

	public List<XColabEntity> findDocumentEntities(String context) throws XColabEntityException {
		Session session = getSession();
		List<XColabEntity> ret = new ArrayList<XColabEntity>();
		try {
			Node containerNode = getContainerNode(session, context);
			NodeIterator iterator = containerNode.getNodes("nodes");
			while (iterator.hasNext()) {
				ret.add(new XColabEntityImpl((Node) iterator.next()));
			}
		
			return ret;

		} catch (PathNotFoundException e) {
			throw new XColabEntityException("Can't find nodes in context [" + context + "]", e);
		} catch (RepositoryException e) {
			throw new XColabEntityException("Error when trying to fetch nodes from [" + context + "]", e);
		}
	}
	
	public XColabEntity createEntity(String context) throws XColabEntityException {
		Session session = getSession();
		try {
			Node containerNode = getContainerNode(session, context);
			Node node = containerNode.addNode("nodes", XCOLAB_BASE_TYPE);
			session.save();
			
			return new XColabEntityImpl(node);
		} catch (PathNotFoundException e) {
			throw new XColabEntityException("Can't find nodes in context [" + context + "]", e);
		} catch (RepositoryException e) {
			throw new XColabEntityException("Error when trying to fetch nodes from [" + context + "]", e);
		}
	}
	

	public void update(XColabEntity entity) throws XColabEntityException {
		if (! (XColabEntityImpl.class.isAssignableFrom(entity.getClass()))) {
			throw new XColabEntityException("Usupported type for an entity, it should be " + XColabEntityImpl.class.getName());
		}
		XColabEntityImpl realEntity = (XColabEntityImpl) entity;
		try {
			Node n = realEntity.getNode();
			
			Session session = n.getSession();
			
			session.save();
			if (n.isNodeType("mix:versionable") && n.isCheckedOut()) {
				session.getWorkspace().getVersionManager().checkin(n.getPath());
			}
		} catch (PathNotFoundException e) {
			throw new XColabEntityException("Can't update node " + realEntity.getNode(), e);
		} catch (RepositoryException e) {
			throw new XColabEntityException("Can't update node " + realEntity.getNode(), e);
		}
	}
	
	private Node getContainerNode(Session session, String context) throws ItemExistsException, PathNotFoundException, 
	VersionException, ConstraintViolationException, LockException, RepositoryException {
		Node containerNode = null;
		if (!session.nodeExists(context)) {
			containerNode = session.getRootNode().addNode(context);
			session.save();
		}
		else {
			containerNode = session.getNode(context);
		}
		return containerNode;
	}
	
	private Session getSession() throws XColabEntityException {
		try {
			return repo.login();
		} catch (LoginException e) {
			_log.error("Can't login to JCR repository", e);
			throw new XColabEntityException("Can't login to JCR repository", e);
		} catch (RepositoryException e) {
			_log.error("Can't login to JCR repository", e);
			throw new XColabEntityException("Can't login to JCR repository", e);
		}
	}
	

	public <IT extends XColabEntityWrapper> IT findDocumentEntity(Object id, Class<IT> type) throws XColabEntityException {
		return wrapEntity(findDocumentEntity(id), type);
	}

	public <LT extends XColabEntityWrapper, IT extends LT> List<LT> findDocumentEntities(
			String context, Class<LT> listType, Class<IT> itemType) throws XColabEntityException {
		List<LT> ret = new ArrayList<LT>();
		for (XColabEntity entity: findDocumentEntities(context)) {
			ret.add(wrapEntity(entity, itemType));
		}
		return ret;
	}

	public <IT extends XColabEntityWrapper> IT createEntity(String context,
			Class<IT> itemType) throws XColabEntityException {
		return wrapEntity(createEntity(context), itemType);
	}

	public <IT extends XColabEntityWrapper> void update(IT wrapped)
			throws XColabEntityException {
		update(wrapped.getWrapped());
	}
	
	public List<EntityVersion<XColabEntity>> getVersions(XColabEntity entity) throws XColabEntityException {
		if (! (HasNode.class.isAssignableFrom(entity.getClass()))) {
			throw new XColabEntityException("Unknown entity type: " + entity.getClass().getName());
		}
		try {
			List<EntityVersion<XColabEntity>> versions = new ArrayList<EntityVersion<XColabEntity>>();
			VersionHistory history = 
					getSession().getWorkspace().getVersionManager().getVersionHistory(((HasNode) entity).getNode().getPath());
		
			for (VersionIterator iterator = history.getAllVersions(); iterator.hasNext()  ; ) {
				Version version = iterator.nextVersion();
				versions.add(new EntityVersionImpl<XColabEntity>(version, new XColabEntityImpl(version.getFrozenNode())));
			}
			return versions;
		}
		catch (RepositoryException e) {
			throw new XColabEntityException("Can't get entity version history " + entity, e);
		}
	}
	
	public <LT extends XColabEntityWrapper, IT extends LT> List<EntityVersion<LT>> getVersions(XColabEntity entity, Class<LT> listType, Class<IT> itemType) throws XColabEntityException {
	    if (entity instanceof XColabEntityWrapper) {
	        entity = ((XColabEntityWrapper) entity).getWrapped();
	    }
		if (! (HasNode.class.isAssignableFrom(entity.getClass()))) {
			throw new XColabEntityException("Unknown entity type: " + entity.getClass().getName());
		}
		try {
			List<EntityVersion<LT>> versions = new ArrayList<EntityVersion<LT>>();
			VersionHistory history = 
					getSession().getWorkspace().getVersionManager().getVersionHistory(((HasNode) entity).getNode().getPath());
		
			for (VersionIterator iterator = history.getAllVersions(); iterator.hasNext()  ; ) {
				Version version = iterator.nextVersion();
				versions.add(new EntityVersionImpl<LT>(version, wrapEntity(new XColabEntityImpl(version.getFrozenNode()), itemType)));
			}
			return versions;
		}
		catch (RepositoryException e) {
			throw new XColabEntityException("Can't get entity version history " + entity, e);
		}
	}

	private <T extends XColabEntityWrapper> T wrapEntity(XColabEntity entity, Class<T> type) throws XColabEntityException {
		try {
			return type.getConstructor(XColabEntity.class).newInstance(entity);
		} catch (InstantiationException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new XColabEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		} catch (IllegalAccessException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new XColabEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		} catch (IllegalArgumentException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new XColabEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		} catch (InvocationTargetException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new XColabEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		} catch (NoSuchMethodException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new XColabEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		} catch (SecurityException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new XColabEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		}
	}

    public String getJcrRepositoryConfig() {
        return jcrRepositoryConfig;
    }

    public void setJcrRepositoryConfig(String jcrRepositoryConfig) {
        this.jcrRepositoryConfig = jcrRepositoryConfig;
    }

}
