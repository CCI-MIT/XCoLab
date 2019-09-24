package org.xcolab.service.content.domain.page;

import org.xcolab.client.content.pojo.IContentPage;

import java.util.List;
import java.util.Optional;

public interface ContentPageDao {
    Optional<IContentPage> get(long pageId);

    Optional<IContentPage> getByContentArticleId(long contentArticleId);

    List<IContentPage> list(String title);

    boolean update(IContentPage page);

    IContentPage create(IContentPage page);
}
