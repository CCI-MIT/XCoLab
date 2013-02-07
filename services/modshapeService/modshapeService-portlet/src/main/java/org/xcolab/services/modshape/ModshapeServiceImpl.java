package org.xcolab.services.modshape;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.RepositoryFactory;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.version.Version;
import javax.jcr.version.VersionHistory;
import javax.jcr.version.VersionIterator;
import javax.jcr.version.VersionManager;

import org.modeshape.repository.ModeShapeEngine;

public class ModshapeServiceImpl {
	
	public void createObject() throws RepositoryException {
		Map<String, Object> properties = new HashMap<String, Object>();
		
		URL resource = this.getClass().getResource("/modeShape-configuration.xml");
		
		System.out.println(resource);
		
		properties.put("org.modeshape.jcr.URL", resource);
		for (RepositoryFactory repoFactory: ServiceLoader.load(RepositoryFactory.class)) {
			Repository repo = repoFactory.getRepository(properties);
			if (repo != null) {
				Session jcrSession = repo.login();
				VersionManager vManager = jcrSession.getWorkspace().getVersionManager();
				
				Node proposal = jcrSession.getRootNode().addNode("/proposal", "xcolab:proposal");
				
				vManager.checkout(proposal.getPath());
				proposal.setProperty("xcolab:name", "To jest nazwa");
				
				jcrSession.save();
				vManager.checkin(proposal.getPath());
				
				vManager.checkout(proposal.getPath());

				proposal.setProperty("xcolab:name", "Nowa To jest nazwa");

				jcrSession.save();
				vManager.checkin(proposal.getPath());
				
				vManager.checkout(proposal.getPath());
				
				proposal.setProperty("to jest property name", "Prosiaczek");
				Node n = proposal.addNode("kalina");
				n.setProperty("name", "Kalina jak dąb");
				System.out.println("Kalina node: " + n);
				jcrSession.save();
				vManager.checkin(proposal.getPath());
				
				//vManager.checkin(proposal.getPath());
				 
				vManager.checkout(proposal.getPath());
				proposal.setProperty("xcolab:name", "Inna jeszcze nowa nazwa To jest nazwa");
				proposal.getNode("kalina").setProperty("name", "Kalina jak dwa dęby!");
				
				jcrSession.save();
				vManager.checkin(proposal.getPath());
				//Version version = vManager.checkin(proposal.getPath());
				//version.get
				
				
				//VersionManager vmanager = 
				//System.out.println(jcrSession.getWorkspace().getVersionManager().getVersionHistory("/proposal"));
				VersionHistory history = jcrSession.getWorkspace().getVersionManager().getVersionHistory("/proposal");
				Version version = null;
				for (VersionIterator iterator = history.getAllVersions(); iterator.hasNext()  ; ) {
					version = iterator.nextVersion();
					//System.out.println(version);
					System.out.println(version.getCreated().getTime());
					System.out.println(version.getFrozenNode());
					if (version.getFrozenNode().hasProperty("xcolab:name")) {
						System.out.println(version.getFrozenNode().getProperty("xcolab:name").getValue().getString());
					}
					//version.setProperty("Krzysio", "krzysztof");
					
					
				}
				jcrSession.save();
				
				history = jcrSession.getWorkspace().getVersionManager().getVersionHistory("/proposal");
				for (VersionIterator iterator = history.getAllVersions(); iterator.hasNext()  ; ) {
					Version v = iterator.nextVersion();
					System.out.println("ma kaline? : " + v.getFrozenNode().hasNode("kalina") + "\t" + v.getFrozenNode().getPath());
					
					if (v.getFrozenNode().hasNode("kalina")) {
						System.out.println("\n\n* " + v.getFrozenNode().getNode("kalina"));
					}
					NodeIterator nIterator = v.getFrozenNode().getNodes();
					while (nIterator.hasNext()) {
						Node child = nIterator.nextNode();
						System.out.println("\t\t\t child - " + child);
					}
					
					//System.out.println(iterator.nextVersion());
					
				}
				//System.out.println(jcrSession.getNode(proposal.getPath()));
				
				vManager.restore(version, true); 
				//System.out.println(jcrSession.getNode(proposal.getPath()));
				

						
				//System.out.println(node.getVersionHistory());
				//System.out.println(proposal);
				
				
			}
			
			else {
				System.out.println("no repo");
			}
		}
	}

}
