package org.xcolab.service.comments.service.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.service.comments.domain.comment.CommentDao;

@Service
public class CommentService {

    private final static int MAX_SCREEN_NAME_LENGTH = 26;

    private final CommentDao commentDao;

    @Autowired
    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
}