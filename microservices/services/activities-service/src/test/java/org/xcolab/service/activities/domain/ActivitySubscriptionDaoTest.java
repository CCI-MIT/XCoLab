package org.xcolab.service.activities.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.model.tables.pojos.ActivitySubscription;
import org.xcolab.service.activities.domain.activitySubscription.ActivitySubscriptionDao;

import static org.junit.Assert.assertFalse;
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

public class ActivitySubscriptionDaoTest {

    @Autowired
    private ActivitySubscriptionDao activitySubscriptionDao;

    @Test
    public void shouldCreateNewActivitySubscription() throws Exception {

        ActivitySubscription as = new ActivitySubscription();
        as = activitySubscriptionDao.create(as);
        assertNotNull(activitySubscriptionDao.get(as.getPk()));
    }

    @Test
    public void shouldGetEmptyForSubscriptionPKNotFound() throws Exception {

        assertFalse(activitySubscriptionDao.get(-1l).isPresent());
    }

    @Test
    public void shouldGetSubscription() throws Exception {

        assertTrue(activitySubscriptionDao.get(1463904l).isPresent());
    }


    @Test
    public void shouldGetActivitySubscribersByReceiver() throws Exception {
        assertTrue(activitySubscriptionDao.getActivitySubscribers(null,null,2664871l).size()==1);

    }

    @Test
    public void shouldGetActivitySubscribersByClassNameClassPK() throws Exception {
        assertTrue(activitySubscriptionDao.getActivitySubscribers(39202l,1366054l,null).size()==1);
    }


    @Test
    public void shouldCheckIfIsSubscriber() throws Exception {
        assertTrue(activitySubscriptionDao.isSubscribed(2664865l,1368503l,1333850l,0,null));
        assertFalse(activitySubscriptionDao.isSubscribed(2694865l,1368503l,1333850l,0,null));
    }
    @Test
    public void shouldDeleteSubscription() throws Exception {
        Long subscId = 1463902l;
        assertTrue(activitySubscriptionDao.get(subscId).isPresent());
        assertTrue(activitySubscriptionDao.delete(subscId));
        assertFalse(activitySubscriptionDao.get(subscId).isPresent());
    }

    @Test
    public void shouldUpdateSubscription() throws Exception {
        ActivitySubscription as = activitySubscriptionDao.get(1463904l).get();
        String extraData = "-";
        as.setExtraData(extraData);
        assertTrue(activitySubscriptionDao.update(as));
        assertTrue(activitySubscriptionDao.get(1463904l).get().getExtraData().equals(extraData));
    }


}
