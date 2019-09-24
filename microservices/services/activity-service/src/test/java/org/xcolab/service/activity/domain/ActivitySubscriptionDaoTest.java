package org.xcolab.service.activity.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.client.activity.pojo.IActivitySubscription;
import org.xcolab.client.activity.pojo.tables.pojos.ActivitySubscription;
import org.xcolab.service.activity.domain.activitySubscription.ActivitySubscriptionDao;
import org.xcolab.util.activities.enums.ActivityCategory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@OverrideAutoConfiguration(enabled = false)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("org.xcolab.service.activity")
@ComponentScan("org.xcolab.client.contest")
@ActiveProfiles("test")
public class ActivitySubscriptionDaoTest {

    @Autowired
    private ActivitySubscriptionDao activitySubscriptionDao;

    @Test
    public void shouldCreateNewActivitySubscription() {
        IActivitySubscription as = new ActivitySubscription();
        as.setReceiverUserId(300L);
        as.setActivityCategory("PROPOSAL");
        as.setCategoryId(3000L);
        as = activitySubscriptionDao.create(as);
        assertNotNull(activitySubscriptionDao.get(as.getId()));
    }

    @Test
    public void shouldGetEmptyForSubscriptionPKNotFound() {
        assertFalse(activitySubscriptionDao.get(40L).isPresent());
    }

    @Test
    public void shouldGetSubscription() {
        assertTrue(activitySubscriptionDao.get(10L).isPresent());
    }

    @Test
    public void shouldGetActivitySubscribersByReceiver() {
        final List<IActivitySubscription> subscribers = activitySubscriptionDao
                .getActivitySubscribers(null, null, 101L);
        assertEquals(3, subscribers.size());
    }

    @Test
    public void shouldGetActivitySubscribersByCategoryAndId() {
        final List<IActivitySubscription> subscribers = activitySubscriptionDao
                .getActivitySubscribers(ActivityCategory.PROPOSAL, 1003L, null);
        assertEquals(2, subscribers.size());
    }

    @Test
    public void shouldCheckIfIsSubscriber() {
        assertTrue(activitySubscriptionDao.isSubscribed(ActivityCategory.CONTEST, 100L, 1000L));
        assertFalse(activitySubscriptionDao.isSubscribed(ActivityCategory.CONTEST, 100L, 4000L));
    }

    @Test
    public void shouldDeleteSubscription() {
        final Long subscriptionId = 20L;
        assertTrue("Precondition failed: subscription to be deleted does not exist",
                activitySubscriptionDao.get(subscriptionId).isPresent());

        assertTrue("No element was deleted",
                activitySubscriptionDao.delete(subscriptionId));
        assertFalse("Element still exists after deletion",
                activitySubscriptionDao.get(subscriptionId).isPresent());
    }

    @Test
    public void shouldUpdateSubscription() {
        final Long subscriptionId = 21L;
        final Long testCategoryId = 3001L;
        IActivitySubscription as = activitySubscriptionDao.get(subscriptionId).get();
        as.setCategoryId(testCategoryId);
        assertTrue("Element not changed", activitySubscriptionDao.update(as));

        final IActivitySubscription updatedSubscription =
                activitySubscriptionDao.get(subscriptionId).get();
        assertEquals("New value wrong", testCategoryId, updatedSubscription.getCategoryId());
    }
}
