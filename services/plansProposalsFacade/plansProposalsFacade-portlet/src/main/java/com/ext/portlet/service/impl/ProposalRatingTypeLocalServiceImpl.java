package com.ext.portlet.service.impl;

import com.ext.portlet.model.ProposalRatingType;
import com.ext.portlet.proposals.ProposalJudgeType;
import com.ext.portlet.service.base.ProposalRatingTypeLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;
/**
 * The implementation of the proposal rating type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ProposalRatingTypeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ProposalRatingTypeLocalServiceBaseImpl
 * @see com.ext.portlet.service.ProposalRatingTypeLocalServiceUtil
 */
public class ProposalRatingTypeLocalServiceImpl
    extends ProposalRatingTypeLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ProposalRatingTypeLocalServiceUtil} to access the proposal rating type local service.
     */
    @Override
    public List<ProposalRatingType> getRatingTypesForJudges() throws SystemException {
        return this.getRatingTypesForJudgeType(ProposalJudgeType.JUDGE.getId());
    }
    @Override
    public List<ProposalRatingType> getRatingTypesForFellows() throws SystemException {
        return this.getRatingTypesForJudgeType(ProposalJudgeType.FELLOW.getId());
    }

    protected List<ProposalRatingType> getRatingTypesForJudgeType(int judgeType) throws SystemException {
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ProposalRatingType.class)
                .add(PropertyFactoryUtil.forName("judgeType").eq(judgeType));
        query.addOrder(OrderFactoryUtil.asc("id"));
        return dynamicQuery(query);
    }
}
