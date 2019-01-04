package org.xcolab.service.contents.domain.page;

import org.xcolab.model.tables.pojos.ContentPage;

import java.util.List;
import java.util.Optional;

public interface ContentPageDao {
    Optional<ContentPage> get(long pageId);

    Optional<ContentPage> getByContentArticleId(long contentArticleId);

    List<ContentPage> list(String title);

    boolean update(ContentPage page);

    ContentPage create(ContentPage page);
}
