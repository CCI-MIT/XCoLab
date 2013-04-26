package org.xcolab.core.xcolabentity.impl;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;

public class JcrVersionUtils {
	public final static String MODIFIED_PROPERTIES_LIST_PROPERTY = "__version_modified_properties";
	public final static String VERSION_AUTHOR_PROPERTY = "__version_author";
	
	private final static String VERSIONABLE_NODE_TYPE = "mix:versionable";
	public static Node findVersionableParent(Node n) throws RepositoryException {
		Node versionableNode = n;
		
		while (!versionableNode.getPath().equals("/") && !versionableNode.isNodeType(VERSIONABLE_NODE_TYPE)) {
			versionableNode = versionableNode.getParent();
		}
		if (!versionableNode.isNodeType(VERSIONABLE_NODE_TYPE)) return null;
		return versionableNode;
	}
	
	public static void reportModificationOfProperty(Node n, String property) throws RepositoryException {
		Node versionableNode = findVersionableParent(n);
		
		if (! versionableNode.isCheckedOut()) {
			versionableNode.getSession().getWorkspace().getVersionManager().checkout(versionableNode.getPath());
			
			// clean list of modified properties as it should reflect current changes
			if (versionableNode.hasProperty(MODIFIED_PROPERTIES_LIST_PROPERTY)) {
				versionableNode.getProperty(MODIFIED_PROPERTIES_LIST_PROPERTY).remove();
			}
		}
		
		// get relative path to a property that has changed 
		String propertyPath = n == versionableNode ? property : n.getPath().substring(versionableNode.getPath().length());
		if (!versionableNode.hasProperty(MODIFIED_PROPERTIES_LIST_PROPERTY)) {
			versionableNode.setProperty(MODIFIED_PROPERTIES_LIST_PROPERTY, new String[] {propertyPath});
		}
		else {
			Property modifiedProperties = versionableNode.getProperty(MODIFIED_PROPERTIES_LIST_PROPERTY);
			Value[] values = null;
			
			if (! modifiedProperties.isMultiple()) 
				values = new Value[] { modifiedProperties.getValue() };
			
			else 
				values = modifiedProperties.getValues();
			
			String[] newVal = new String[values.length + 1];
			for (int i = 0; i < values.length; i++) {
				newVal[i] = values[i].getString();
			}
			newVal[values.length] = propertyPath;
			modifiedProperties.remove();
			

			versionableNode.setProperty(MODIFIED_PROPERTIES_LIST_PROPERTY, newVal);
		}
	}
	
	public static String[] getModifiedProperties(Node n) throws ValueFormatException, IllegalStateException, RepositoryException {
		if (! n.hasProperty(MODIFIED_PROPERTIES_LIST_PROPERTY)) return new String[] {};
		
		
		Value[] propertyValues = n.getProperty(MODIFIED_PROPERTIES_LIST_PROPERTY).getValues();
		String[] ret = new String[propertyValues.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = propertyValues[i].getString();
		}
		return ret;
	}
	
	public static void setVersionAuthor(Node node, Long userId) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
		node.setProperty(VERSION_AUTHOR_PROPERTY, userId);
	}
	
	public static Long getVersionAuthor(Node node) throws ValueFormatException, PathNotFoundException, RepositoryException {
		return node.getProperty(VERSION_AUTHOR_PROPERTY).getLong();
	}

}
