package org.xcolab.service.comment.domain.comment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.client.comment.pojo.IComment;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringJUnit4ClassRunner.class)
@OverrideAutoConfiguration(enabled = false)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("org.xcolab.service.comment")
@ActiveProfiles("test")
public class CommentDaoTest {

    @Autowired
    private CommentDao commentDao;

    @Test
    public void testCountByGiven__shouldReturnCorrectCount() throws Exception {
        final int count = commentDao.countByGiven(null, null);
        Assert.assertEquals("Total comment count incorrect", 2, count);
    }

    @Test
    public void testFindByGiven__shouldReturnCorrectNumber() throws Exception {
        final List<IComment> comments =
                commentDao.findByGiven(PaginationHelper.EVERYTHING, null, null, false);
        assertThat("Total comment count incorrect", comments, hasSize(2));
    }

    @Test
    public void testFindByGiven__includeDeleted__shouldReturnCorrectNumber() throws Exception {
        final List<IComment> comments =
                commentDao.findByGiven(PaginationHelper.EVERYTHING, null, null, true);
        assertThat("Total comment count incorrect", comments, hasSize(3));
    }

    @Test
    public void testFindByGiven__pagination__shouldReturnCorrectNumber() throws Exception {
        final List<IComment> comments =
                commentDao.findByGiven(new PaginationHelper(1, 1, null),
                        null, null, true);
        assertThat("Total comment count incorrect", comments, hasSize(1));
    }

}
