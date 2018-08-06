package org.xcolab.service.activities.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.model.tables.pojos.ActivitySubscription;
import org.xcolab.service.activities.domain.activitySubscription.ActivitySubscriptionDao;
import org.xcolab.util.activities.enums.ActivityCategory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("org.xcolab.service.activities")
@ActiveProfiles("test")
public class ActivitySubscriptionDaoTest {

    @Autowired
    private ActivitySubscriptionDao activitySubscriptionDao;

    @Test
    public void shouldCreateNewActivitySubscription() {
        ActivitySubscription as = new ActivitySubscription();
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
        final List<ActivitySubscription> subscribers = activitySubscriptionDao
                .getActivitySubscribers(null, null, 101L);
        assertEquals(3, subscribers.size());
    }

    @Test
    public void shouldGetActivitySubscribersByCategoryAndId() {
        final List<ActivitySubscription> subscribers = activitySubscriptionDao
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
        ActivitySubscription as = activitySubscriptionDao.get(subscriptionId).get();
        as.setCategoryId(testCategoryId);
        assertTrue("Element not changed", activitySubscriptionDao.update(as));

        final ActivitySubscription updatedSubscription =
                activitySubscriptionDao.get(subscriptionId).get();
        assertEquals("New value wrong", testCategoryId, updatedSubscription.getCategoryId());
    }
}
