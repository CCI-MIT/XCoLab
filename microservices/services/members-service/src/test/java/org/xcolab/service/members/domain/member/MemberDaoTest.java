package org.xcolab.service.members.domain.member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.model.tables.pojos.Member;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

import static org.junit.Assert.assertEquals;

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
@ComponentScan("org.xcolab.service.members")
public class MemberDaoTest {

    @Autowired
    private MemberDao memberDao;

    @Test
    public void testFindByGiven__shouldReturnRightCount() {
        final List<Member> members =
                memberDao.findByGiven(PaginationHelper.EVERYTHING, null, null,
                        null, null, null, null, null, null, null);
        assertEquals("Wrong number of members", 2, members.size());
    }

    @Test
    public void testFindByNameGiven() {
        final List<Member> members =
                memberDao.findByGiven(PaginationHelper.EVERYTHING, "admin", null,
                        null, null, null, null, null, null, null);
        assertEquals("Wrong number of members with name:admin", 1, members.size());
    }

    @Test
    public void testFindByNameGiven_with_roles() {
        final List<Member> members =
                memberDao.findByGiven(PaginationHelper.EVERYTHING, null, null,
                        "Member", null, null, null, null, null, null);
        assertEquals("Wrong number of members with role:Member", 2, members.size());
    }

}


