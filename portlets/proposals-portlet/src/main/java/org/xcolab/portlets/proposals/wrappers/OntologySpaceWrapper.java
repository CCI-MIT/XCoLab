package org.xcolab.portlets.proposals.wrappers;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.model.OntologySpace;

public class OntologySpaceWrapper {
	
	private final OntologySpace wrapped;
	private final List<OntologyTermWrapper> terms = new ArrayList<>();
	private final List<OntologyTermWrapper> rootTerms = new ArrayList<>();
	
	public OntologySpaceWrapper(OntologySpace wrapped) {
		super();
		this.wrapped = wrapped;
	}
	
	public OntologySpace getWrapped() {
		return wrapped;
	}
	
	public void addTerm(OntologyTermWrapper term) {
		terms.add(term);
		if (! term.hasParent()) {
			rootTerms.add(term);
		}
	}
	public List<OntologyTermWrapper> getTerms() {
		return terms;
	}
	
	public String getName() {
		return wrapped.getName();
	}
	
	public String getDescription() {
		return wrapped.getDescription();
	}
	
	public long getId() {
		return wrapped.getId();
	}
	
	public List<OntologyTermWrapper> getRootTerms() {
		return rootTerms;
	}
	

}
