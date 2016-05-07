package org.xcolab.service.contents.service;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Wikipage;

import java.util.List;

import static org.xcolab.model.Tables.WIKI_PAGE;

@Repository
public class WikipageMigrationHelper {

    @Autowired
    private DSLContext dslContext;

    public List<Wikipage> getCreolePages() {
        final org.xcolab.model.tables.Wikipage a = WIKI_PAGE.as("a");
        final org.xcolab.model.tables.Wikipage b = WIKI_PAGE.as("b");
        return dslContext.select(a.USER_NAME, a.CREATE_DATE, a.TITLE, a.CONTENT, a.USER_ID, a.MODIFIED_DATE)
                .from(a)
                .leftOuterJoin(b).on(a.RESOURCE_PRIM_KEY.eq(b.RESOURCE_PRIM_KEY)
                        .and(a.VERSION.lt(b.VERSION)))
                .where(b.RESOURCE_PRIM_KEY.isNull()
                        .and(a.FORMAT.eq("CREOLE")))
                .orderBy(a.CREATE_DATE)
                .fetchInto(Wikipage.class);
    }

    public List<Wikipage> getAllPages() {
        final org.xcolab.model.tables.Wikipage a = WIKI_PAGE.as("a");
        final org.xcolab.model.tables.Wikipage b = WIKI_PAGE.as("b");
        return dslContext.select(a.USER_NAME, a.CREATE_DATE, a.TITLE, a.CONTENT, a.USER_ID, a.MODIFIED_DATE)
                .from(a)
                .leftOuterJoin(b).on(a.RESOURCE_PRIM_KEY.eq(b.RESOURCE_PRIM_KEY)
                        .and(a.VERSION.lt(b.VERSION)))
                .where(b.RESOURCE_PRIM_KEY.isNull())
                .orderBy(a.CREATE_DATE)
                .fetchInto(Wikipage.class);
    }
}
