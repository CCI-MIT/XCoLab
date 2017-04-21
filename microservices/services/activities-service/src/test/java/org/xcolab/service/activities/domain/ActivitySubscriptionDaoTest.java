package org.xcolab.service.activities.domain;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.service.activities.domain.activitySubscription.ActivitySubscriptionDao;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(
    properties = {
        "cache.active=false",
        "eureka.client.enabled=false",
        "spring.datasource.url=jdbc:h2:mem:testdb;MODE=MYSQL"
    }
)
@ComponentScan("org.xcolab.service.activities")
public class ActivitySubscriptionDaoTest {

    @Autowired
    ActivitySubscriptionDao activitySubscriptionDao;

}
