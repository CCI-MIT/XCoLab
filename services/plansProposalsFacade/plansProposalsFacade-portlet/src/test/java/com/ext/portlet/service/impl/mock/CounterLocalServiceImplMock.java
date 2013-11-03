package com.ext.portlet.service.impl.mock;

import java.io.Serializable;
import java.util.List;

import com.liferay.counter.model.Counter;
import com.liferay.counter.service.CounterLocalService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;

/**
 * <p>A mock class that realizes a counter contract.</p>
 * 
 * @author janusz
 *
 */
public class CounterLocalServiceImplMock implements CounterLocalService {
    private long counter = 0;

    @Override
    public Counter addCounter(Counter counter) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Counter createCounter(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteCounter(String name) throws PortalException, SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteCounter(Counter counter) throws SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public List dynamicQuery(DynamicQuery arg0) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List dynamicQuery(DynamicQuery arg0, int arg1, int arg2) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List dynamicQuery(DynamicQuery arg0, int arg1, int arg2, OrderByComparator arg3) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long dynamicQueryCount(DynamicQuery arg0) throws SystemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Counter fetchCounter(String name) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Counter getCounter(String name) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PersistedModel getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Counter> getCounters(int start, int end) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getCountersCount() throws SystemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Counter updateCounter(Counter counter) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Counter updateCounter(Counter counter, boolean merge) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getBeanIdentifier() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBeanIdentifier(String beanIdentifier) {
        // TODO Auto-generated method stub

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<String> getNames() throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long increment() throws SystemException {
        // TODO Auto-generated method stub
        return counter++;
    }

    @Override
    public long increment(String name) throws SystemException {
        // TODO Auto-generated method stub
        return increment();
    }

    @Override
    public long increment(String name, int size) throws SystemException {
        // TODO Auto-generated method stub
        return increment();
    }

    @Override
    public void rename(String oldName, String newName) throws SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void reset(String name) throws SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void reset(String name, long size) throws SystemException {
        // TODO Auto-generated method stub

    }

}
