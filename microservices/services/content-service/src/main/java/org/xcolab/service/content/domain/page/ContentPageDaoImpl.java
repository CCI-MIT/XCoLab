package org.xcolab.service.content.domain.page;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.content.pojo.IContentPage;
import org.xcolab.client.content.pojo.tables.pojos.ContentPage;
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
    public Optional<IContentPage> get(long pageId) {
        final Record record = dslContext.select()
                .from(CONTENT_PAGE)
                .where(CONTENT_PAGE.ID.eq(pageId))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ContentPage.class));
    }

    @Override
    public Optional<IContentPage> getByContentArticleId(long contentArticleId) {
        final Record record = dslContext.select()
                .from(CONTENT_PAGE)
                .where(CONTENT_PAGE.CONTENT_ARTICLE_ID.eq(contentArticleId))
                .limit(1)
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ContentPage.class));
    }

    @Override
    public List<IContentPage> list(String title) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTENT_PAGE)
                .getQuery();

        if (title != null && !title.isEmpty()) {
            query.addConditions(CONTENT_PAGE.TITLE.eq(title));
        }

        return query.fetch().into(ContentPage.class);
    }

    @Override
    public boolean update(IContentPage page) {
        return dslContext.update(CONTENT_PAGE)
                .set(CONTENT_PAGE.TITLE, page.getTitle())
                .set(CONTENT_PAGE.META_DESCRIPTION, page.getMetaDescription())
                .set(CONTENT_PAGE.MENU_ARTICLE_ID, page.getMenuArticleId())
                .set(CONTENT_PAGE.CONTENT_ARTICLE_ID, page.getContentArticleId())
                .set(CONTENT_PAGE.UPDATED_AT, DSL.currentTimestamp())
                .where(CONTENT_PAGE.ID.eq(page.getId()))
                .execute() > 0;
    }

    @Override
    public IContentPage create(IContentPage page) {
        final ContentPageRecord ret = this.dslContext.insertInto(CONTENT_PAGE)
                .set(CONTENT_PAGE.TITLE, page.getTitle())
                .set(CONTENT_PAGE.META_DESCRIPTION, page.getMetaDescription())
                .set(CONTENT_PAGE.MENU_ARTICLE_ID, page.getMenuArticleId())
                .set(CONTENT_PAGE.CONTENT_ARTICLE_ID, page.getContentArticleId())
                .set(CONTENT_PAGE.CREATED_AT, DSL.currentTimestamp())
                .set(CONTENT_PAGE.UPDATED_AT, DSL.currentTimestamp())
                .returning(CONTENT_PAGE.ID)
                .fetchOne();
        if (ret != null) {
            page.setId(ret.getValue(CONTENT_PAGE.ID));
            return page;
        } else {
            throw new IllegalStateException("failed to retrieve inserted id");
        }
    }
}
