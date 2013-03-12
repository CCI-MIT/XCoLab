/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.plans;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ext.portlet.model.PlanDescription;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanModelRun;
import com.ext.portlet.model.PlanPositions;
import com.ext.portlet.service.PlanDescriptionLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.PlanModelRunLocalServiceUtil;
import com.ext.portlet.service.PlanPositionsLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Sep 12, 2010
 * Time: 3:19:27 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class PlanHistoryWrapper<T> {

	public abstract Date getUpdateDate();
    public abstract User getUpdateAuthor();
    public abstract long getUpdateVersion();
    public abstract boolean isLatest(); 
    public abstract T getWrapped();
    
    public String getUpdateDateFormatted() {
    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm");
    	return sdf.format(getUpdateDate());
    }

    public static PlanHistoryWrapper<PlanModelRun> getWrapper(final PlanModelRun run, final boolean latest) {
        return new PlanHistoryWrapper<PlanModelRun>() {
        	
            @Override
            public Date getUpdateDate() {
                return run.getCreated();
            }

            @Override
            public User getUpdateAuthor() {
               try {
                   return PlanModelRunLocalServiceUtil.getUpdateAuthor(run);
               } catch (SystemException e) {
                   e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
               } catch (PortalException e) {
                   e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
               }
                return null;
            }

            @Override
            public long getUpdateVersion() {
                return run.getId();
            }

            @Override
            public PlanModelRun getWrapped() {
               return run;
            }

            @Override
            public boolean isLatest() {
                return latest;
            }
        };
    }

    public static PlanHistoryWrapper<PlanDescription> getWrapper(final PlanDescription desc, final boolean latest) {
        return new PlanHistoryWrapper<PlanDescription>() {

            @Override
            public Date getUpdateDate() {
               return desc.getCreated();
            }

            @Override
            public User getUpdateAuthor() {
                try {
                    return PlanDescriptionLocalServiceUtil.getUpdateAuthor(desc);
                } catch (PortalException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (SystemException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                return null;
            }

            @Override
            public long getUpdateVersion() {
               return desc.getId();
            }

            @Override
            public PlanDescription getWrapped() {
                return desc;
            }

            @Override
            public boolean isLatest() {
                return latest;
            }
        };
    }

    public static PlanHistoryWrapper<PlanPositions> getWrapper(final PlanPositions positions, final boolean latest) {
        return new PlanHistoryWrapper<PlanPositions>() {

            @Override
            public Date getUpdateDate() {
                return positions.getCreated();
            }

            @Override
            public User getUpdateAuthor() {
                try {
                    return PlanPositionsLocalServiceUtil.getUpdateAuthor(positions);
                } catch (PortalException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (SystemException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                return null;
            }

            @Override
            public long getUpdateVersion() {
                return positions.getId();
            }

            @Override
            public PlanPositions getWrapped() {
                return positions;
            }

            @Override
            public boolean isLatest() {
                return latest;
            }
        };
    }
    
    public static PlanHistoryWrapper<PlanItem> getWrapper(final PlanItem positions, final boolean latest) {
        return new PlanHistoryWrapper<PlanItem>() {

            @Override
            public Date getUpdateDate() {
                return positions.getUpdated();
            }

            @Override
            public User getUpdateAuthor() {
                try {
                    return PlanItemLocalServiceUtil.getUpdateAuthor(positions);
                } catch (PortalException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (SystemException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                return null;
            }

            @Override
            public long getUpdateVersion() {
                return positions.getId();
            }

            @Override
            public PlanItem getWrapped() {
                return positions;
            }

            @Override
            public boolean isLatest() {
                return latest;
            }
        };
    }
   
}
