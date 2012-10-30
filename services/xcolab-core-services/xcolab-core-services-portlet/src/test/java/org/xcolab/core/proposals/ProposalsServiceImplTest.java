package org.xcolab.core.proposals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.impl.DocumentEntityServiceImpl;

public class ProposalsServiceImplTest {

	ProposalsService proposalsSvc = null;
	
	@Before
	public void setUpSvc() {
		DocumentEntityServiceImpl svc = new DocumentEntityServiceImpl();
		svc.init();
		proposalsSvc = new ProposalsServiceImpl(svc);
	}
	
	

	@Test
	public void test() throws DocumentEntityException {
		
		Proposal p = proposalsSvc.createEntity();
		
		p.setString("name", "very long name");
		proposalsSvc.updateEntity(p);
		
		System.out.println(p);
		
	}
}
