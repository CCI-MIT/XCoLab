package org.xcolab.service.activity.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.client.activity.pojo.IActivityEntry;
import org.xcolab.client.activity.pojo.tables.pojos.ActivityEntry;
import org.xcolab.service.activity.domain.activityEntry.ActivityEntryDao;
import org.xcolab.service.activity.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.activities.enums.MemberActivityType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@OverrideAutoConfiguration(enabled = false)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("org.xcolab.service.activity")
@ComponentScan("org.xcolab.client.contest")
@ActiveProfiles("test")
public class ActivityEntryDaoTest {

    @Autowired
    ActivityEntryDao activityEntryDao;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateNewActivityEntry() throws Exception {
        IActivityEntry ae = new ActivityEntry();
        ae.setUserId(2057710L);
        ae.setActivityType(ActivityCategory.MEMBER.name());
        ae.setActivityType(MemberActivityType.REGISTERED.name());
        ae.setCategoryId(2057710L);
        ae = activityEntryDao.create(ae);

        assertNotNull(activityEntryDao.get(ae.getId()));
    }

    @Test
    public void shouldGetActivitiesAfterDate() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dt = dateFormat.parse("2017-12-15 01:15:00");
        final List<IActivityEntry> result = activityEntryDao.getActivitiesAfter(dt);
        assertTrue(result.size() == 4);
    }

    @Test
    public void shouldThrowExceptionOnGetActivityEntryNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        activityEntryDao.get(-1L);
    }

    @Test
    public void shouldFindByGivenuserId() throws Exception {
        List<IActivityEntry> list = activityEntryDao.findByGiven(PaginationHelper.EVERYTHING, null,
                null, 2666739L, null);
        assertTrue(list.size() == 2);
    }
}
