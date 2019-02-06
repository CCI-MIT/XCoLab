package org.xcolab.client.comment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.IThread;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
@Profile("test")
public class ThreadClientMockImpl implements IThreadClient {

    @Override
    public List<IThread> listThreads(Integer startRecord, Integer limitRecord, String sort,
            Long authorUserId, Long categoryId, Long groupId) {
        return Collections.emptyList();
    }

    @Override
    public IThread getThread(Long threadId) throws ThreadNotFoundException {
        return null;
    }

    @Override
    public boolean updateThread(Long threadId , IThread thread ) {
        return false;
    }

    @Override
    public IThread createThread(IThread thread) {
        return null;
    }

    @Override
    public boolean deleteThread(Long threadId) {
        return false;
    }

    @Override
    public Date getLastActivityDate(Long threadId) {
        return null;
    }

    @Override
    public Long getLastActivityAuthorUserId(Long threadId) {
        return null;
    }
}
