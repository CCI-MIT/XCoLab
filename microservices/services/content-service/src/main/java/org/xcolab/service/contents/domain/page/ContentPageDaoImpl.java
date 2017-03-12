package org.xcolab.service.contents.domain.page;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.ContentPage;
import org.xcolab.model.tables.records.ContentPageRecord;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.CONTENT_PAGE;

@Repository
public class ContentPageDaoImpl implements ContentPageDao {

    private final DSLContext dslContext;

    @Autowired
    public ContentPageDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<ContentPage> get(long pageId) {
        final Record record = dslContext.select()
                .from(CONTENT_PAGE)
                .where(CONTENT_PAGE.PAGE_ID.eq(pageId))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ContentPage.class));
    }

    @Override
    public List<ContentPage> list(String title) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTENT_PAGE)
                .getQuery();

        if (title != null) {
            query.addConditions(CONTENT_PAGE.TITLE.eq(title));
        }

        return query.fetch().into(ContentPage.class);
    }

    @Override
    public boolean update(ContentPage page) {
        return dslContext.update(CONTENT_PAGE)
                .set(CONTENT_PAGE.TITLE, page.getTitle())
                .set(CONTENT_PAGE.MENU_ARTICLE_ID, page.getMenuArticleId())
                .set(CONTENT_PAGE.CONTENT_ARTICLE_ID, page.getContentArticleId())
                .set(CONTENT_PAGE.MODIFIED_DATE, DSL.currentTimestamp())
                .where(CONTENT_PAGE.PAGE_ID.eq(page.getPageId()))
                .execute() > 0;
    }

    @Override
    public ContentPage create(ContentPage page) {
        final ContentPageRecord ret = this.dslContext.insertInto(CONTENT_PAGE)
                .set(CONTENT_PAGE.TITLE, page.getTitle())
                .set(CONTENT_PAGE.MENU_ARTICLE_ID, page.getMenuArticleId())
                .set(CONTENT_PAGE.CONTENT_ARTICLE_ID, page.getContentArticleId())
                .set(CONTENT_PAGE.CREATED_DATE, DSL.currentTimestamp())
                .set(CONTENT_PAGE.MODIFIED_DATE, DSL.currentTimestamp())
                .returning(CONTENT_PAGE.PAGE_ID)
                .fetchOne();
        if (ret != null) {
            page.setPageId(ret.getValue(CONTENT_PAGE.PAGE_ID));
            return page;
        } else {
            throw new IllegalStateException("failed to retrieve inserted id");
        }
    }

}
