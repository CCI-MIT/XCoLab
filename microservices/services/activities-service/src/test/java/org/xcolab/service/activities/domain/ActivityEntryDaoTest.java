package org.xcolab.service.activities.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.client.activities.enums.ActivityCategory;
import org.xcolab.client.activities.enums.MemberActivityType;
import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.service.activities.domain.activityEntry.ActivityEntryDao;
import org.xcolab.service.activities.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(
    properties = {
        "cache.enabled=false",
        "eureka.client.enabled=false",
        "spring.datasource.url=jdbc:h2:mem:testdb;MODE=MYSQL"
    }
)
@ComponentScan("org.xcolab.service.activities")
public class ActivityEntryDaoTest {

    @Autowired
    ActivityEntryDao activityEntryDao;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void shouldCreateNewActivityEntry() throws Exception {
        ActivityEntry ae = new ActivityEntry();
        ae.setMemberId(2057710L);
        ae.setActivityType(ActivityCategory.MEMBER.name());
        ae.setActivityType(MemberActivityType.REGISTERED.name());
        ae.setCategoryId(2057710L);
        ae = activityEntryDao.create(ae);

        assertNotNull(activityEntryDao.get(ae.getActivityEntryId()));
    }

    @Test
    public void shouldGetActivitiesAfterDate() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dt = dateFormat.parse("2017-12-15 01:15:00");
        final List<ActivityEntry> result = activityEntryDao.getActivitiesAfter(dt);
        assertTrue(result.size() == 4);
    }

    @Test
    public void shouldThrowExceptionOnGetActivityEntryNotFound() throws Exception {

        thrown.expect(NotFoundException.class);
        activityEntryDao.get(-1L);
    }

    @Test
    public void shouldFindByGivenMemberId() throws Exception {

        List<ActivityEntry> list = activityEntryDao.findByGiven(PaginationHelper.EVERYTHING,
                2666739L,null);
        assertTrue(list.size() == 2);
    }
}
