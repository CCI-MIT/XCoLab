package com.ext.portlet.service.impl.mock;

import java.io.Serializable;
import java.util.List;

import javax.sql.DataSource;

import com.ext.portlet.NoSuchProposalException;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.persistence.ProposalPersistence;
import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.ServiceContext;

public class ProposalPersistenceImplMock implements ProposalPersistence {

    @Override
    public void clearCache() {
        // TODO Auto-generated method stub

    }

    @Override
    public void clearCache(Proposal model) {
        // TODO Auto-generated method stub

    }

    @Override
    public void clearCache(List<Proposal> modelList) {
        // TODO Auto-generated method stub

    }

    @Override
    public void closeSession(Session session) {
        // TODO Auto-generated method stub

    }

    @Override
    public long countWithDynamicQuery(DynamicQuery dynamicQuery) throws SystemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Proposal fetchByPrimaryKey(Serializable primaryKey) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Proposal findByPrimaryKey(Serializable primaryKey) throws NoSuchModelException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List findWithDynamicQuery(DynamicQuery dynamicQuery) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List findWithDynamicQuery(DynamicQuery dynamicQuery, int start, int end) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List findWithDynamicQuery(DynamicQuery dynamicQuery, int start, int end, OrderByComparator orderByComparator)
            throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataSource getDataSource() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ModelListener<Proposal>[] getListeners() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Session openSession() throws ORMException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SystemException processException(Exception e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void registerListener(ModelListener<Proposal> listener) {
        // TODO Auto-generated method stub

    }

    @Override
    public Proposal remove(Serializable primaryKey) throws NoSuchModelException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Proposal remove(Proposal model) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        // TODO Auto-generated method stub

    }

    @Override
    public void unregisterListener(ModelListener<Proposal> listener) {
        // TODO Auto-generated method stub

    }

    @Override
    public Proposal update(Proposal model, boolean merge) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Proposal update(Proposal model, boolean merge, ServiceContext serviceContext) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void cacheResult(Proposal proposal) {
        // TODO Auto-generated method stub

    }

    @Override
    public void cacheResult(List<Proposal> proposals) {
        // TODO Auto-generated method stub

    }

    @Override
    public Proposal create(long proposalId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Proposal remove(long proposalId) throws NoSuchProposalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Proposal updateImpl(Proposal proposal, boolean merge) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Proposal findByPrimaryKey(long proposalId) throws NoSuchProposalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Proposal fetchByPrimaryKey(long proposalId) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Proposal> findAll() throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Proposal> findAll(int start, int end) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Proposal> findAll(int start, int end, OrderByComparator orderByComparator) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeAll() throws SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public int countAll() throws SystemException {
        // TODO Auto-generated method stub
        return 0;
    }

}
