package org.xcolab.services.modshape;

import javax.jcr.RepositoryException;

import org.junit.Test;

public class ModshapeServiceImplTest {

	@Test
	public void testAbc() throws RepositoryException {
		ModshapeServiceImpl msi = new ModshapeServiceImpl();
		msi.createObject();
		
	}

}
