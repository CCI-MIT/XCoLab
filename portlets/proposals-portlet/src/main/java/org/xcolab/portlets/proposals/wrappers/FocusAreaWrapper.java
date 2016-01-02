package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.FocusArea;

import java.util.ArrayList;
import java.util.List;

public class FocusAreaWrapper {
	private FocusArea wrapped;
	private List<OntologyTermWrapper> ontologyTerms = new ArrayList<>();
	private List<Long> ontologyTermsIds = new ArrayList<>();
	
	public FocusAreaWrapper(FocusArea wrapped) {
		this.wrapped = wrapped;
	}
	
	public FocusArea getWrapped() {
		return wrapped;
	}
	public void setWrapped(FocusArea wrapped) {
		this.wrapped = wrapped;
	}
	public List<OntologyTermWrapper> getOntologyTerms() {
		return ontologyTerms;
	}
	public void addOntologyTerm(OntologyTermWrapper term) {
		if (term != null) {
			ontologyTerms.add(term);
			ontologyTermsIds.add(term.getId());
		}
	}
	
	public long getId() {
		return wrapped.getId();
	}
	
	public String getTermsIdsStr() {
		return ontologyTermsIds.toString();
	}
	

}
