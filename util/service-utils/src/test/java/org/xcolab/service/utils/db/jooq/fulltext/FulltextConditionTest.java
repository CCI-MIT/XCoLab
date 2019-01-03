package org.xcolab.service.utils.db.jooq.fulltext;

import org.jooq.Record1;
import org.jooq.SQLDialect;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DefaultDSLContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.xcolab.service.utils.db.jooq.test.generated.ContestTable.CONTEST;

public class FulltextConditionTest {

    private final DefaultDSLContext dslContext = new DefaultDSLContext(SQLDialect.MYSQL);

    @Test
    public void testSimpleCondition() {
        final FulltextCondition condition = new FulltextCondition("test", CONTEST.DESCRIPTION);
        final SelectConditionStep<Record1<String>> query =
                dslContext.select(CONTEST.DESCRIPTION).from(CONTEST).where(condition);
        assertEquals("condition not rendered correctly",
                "select `contest__contest`.`description`\n"
                        + "from `contest__contest`\n"
                        + "where (MATCH (description) AGAINST ('test'))",
                query.toString());
    }

    @Test
    public void testQueryWithSpecialCharacters__shouldEscapeCorrectly() {
        final FulltextCondition condition = new FulltextCondition("test' or 'a' = 'a", CONTEST.DESCRIPTION);
        final SelectConditionStep<Record1<String>> query =
                dslContext.select(CONTEST.DESCRIPTION).from(CONTEST).where(condition);
        assertEquals("sql injection possible",
                "select `contest__contest`.`description`\n"
                        + "from `contest__contest`\n"
                        + "where (MATCH (description) AGAINST ('test'' or ''a'' = ''a'))",
                query.toString());
    }
}
