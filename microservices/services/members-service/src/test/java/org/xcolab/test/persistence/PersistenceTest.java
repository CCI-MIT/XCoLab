package org.xcolab.test.persistence;

import org.jooq.DSLContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xcolab.MembersServiceApplication;
import org.xcolab.model.tables.pojos.User_;

import java.sql.SQLException;
import java.util.List;

import static org.xcolab.model.Tables.USER_;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(MembersServiceApplication.class)
public class PersistenceTest {

    @Autowired
    private DSLContext dslContext;

    @Test
    public void testGetContests() throws SQLException {
        final List<User_> users = dslContext.select()
                .from(USER_).fetchInto(User_.class);
        Assert.assertTrue(!users.isEmpty());
    }

}
