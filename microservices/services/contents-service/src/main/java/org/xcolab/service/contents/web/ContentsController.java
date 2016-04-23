package org.xcolab.service.contents.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.service.contents.exceptions.NotFoundException;
import org.xcolab.service.contents.service.contentarticle.ContentArticleService;

@RestController
public class ContentsController {

    @Autowired
    private ContentArticleService contentArticleService;

    // C - CRUD
    @RequestMapping(value = "/contentArticles/{articleId}", method = RequestMethod.PUT)
    public Object createContentArticle(@RequestBody Object object) {

        return null;
    }

    // U - CRUD
    @RequestMapping(value = "/contentArticles/{articleId}", method = RequestMethod.POST)
    public Object updateContentArticle(@RequestBody Object object,
                                       @PathVariable("articleId") Long articleId) throws NotFoundException {

        if (articleId == null || articleId == 0) {
            throw new NotFoundException("No message id given");
        } else {
            return null;// contentArticleService.getMember(memberId);
        }

    }

    // R - CRUD
    @RequestMapping(value = "/contentArticles/{articleId}", method = RequestMethod.GET)
    public Object getMember(@PathVariable("articleId") Long memberId) throws NotFoundException {
        if (memberId == null || memberId == 0) {
            throw new NotFoundException("No message id given");
        } else {
            return null;//contentArticleService.getMember(memberId);
        }
    }

}
