package org.xcolab.client.admin;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.admin.pojo.IContestTypeAttribute;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Profile("test")
public class ContestTypeClientMockImpl implements IContestTypeClient {

    @Override
    public List<IContestTypeAttribute> listContestTypeAttributes() {
        return Collections.emptyList();
    }

    @Override
    public Optional<IContestTypeAttribute> getContestTypeAttribute(String attributeName, Long additionalId,
            String locale) {
        return null;
    }

    @Override
    public IContestTypeAttribute createContestTypeAttribute(
            IContestTypeAttribute contestTypeAttribute) {
        return null;
    }

    @Override
    public boolean updateContestTypeAttribute(IContestTypeAttribute contestTypeAttribute) {
        return false;
    }
}
