package org.xcolab.core.contests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.EntityVersion;
import org.xcolab.core.documententity.impl.DocumentEntityServiceImpl;

import edu.emory.mathcs.backport.java.util.Arrays;

public class ContestServiceImplTest {
	
	private ContestService contestsSvc = null;
	private Random rand = new Random();
	
	@Before
	public void setUpSvc() {
	    System.out.println("Seting up svc");
		DocumentEntityServiceImpl svc = new DocumentEntityServiceImpl();
		svc.init();
		contestsSvc = new ContestServiceImpl(svc);
	}
	
	
	@Test
	public void testCreationAndBaseProperties() throws DocumentEntityException {
		final String name = "name" + rand.nextLong();
		final String shortName = "short name" + rand.nextLong();
		final String description = "description" + rand.nextLong();
		Contest contest = contestsSvc.createEntity();
		contest.setName(name);
		contest.setShortName(shortName);
		contest.setDescription(description);
		
		assertEquals(name, contest.getName());
		assertEquals(shortName, contest.getShortName());
		assertEquals(description, contest.getDescription());
	}
	

	@Test
	public void testCreationOfPhases() throws DocumentEntityException {
	    System.out.println("test creation of phases");
		final int phaseCount = 5;
		Contest contest = contestsSvc.createEntity();
		
		String[] phaseNames = new String[phaseCount]; 
		for (int i = 0; i < phaseCount; i++) {
			ContestPhase phase = contest.addPhase();
			phaseNames[i] = "phase " + i + " " + rand.nextLong();
			phase.setString("name", phaseNames[i]);
		}  
		
		String[] phaseNamesFromContest = new String[phaseCount];
		int i=0; 
		for (ContestPhase phase: contest.getPhases()) {
			phaseNamesFromContest[i++] = phase.getString("name");
		}
		
		
		assertArrayEquals(phaseNames, phaseNamesFromContest);
	}
	
	@Test
	public void testVersionHistory() throws DocumentEntityException {
		Contest contest = contestsSvc.createEntity();
		
		contest.setName("Contest name 1");
		contestsSvc.updateEntity(contest);
		
		contest.setName("Contest name 2");
		contest.setShortName("This is a short name");
		contestsSvc.updateEntity(contest);

		contest.setName("Contest name 2");
		contest.setShortName("This is a short name");
		contest.setDescription("This is a description");
		contestsSvc.updateEntity(contest);
		
		Contest contestFromRepo = contestsSvc.getEntity(contest.getId());
		
		List<EntityVersion<Contest>> versions = contestsSvc.getVersions(contestFromRepo);
		for (EntityVersion<Contest> v: versions) {
			System.out.println(Arrays.toString(v.getTags()));
		}
		
	}
	
}
