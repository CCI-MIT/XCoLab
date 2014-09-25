package com.ext.portlet.service.impl;


import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.*;
import com.ext.portlet.service.persistence.PointsPersistence;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionCheckerUtil;
import com.liferay.portal.service.*;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.*;


public class PointsLocalServiceImplTest extends XCoLabTest {

    @Test
    public void firstTest() throws SystemException, PortalException, ParseException, NoSuchFieldException, IllegalAccessException {
        this.setupBasicDataset();
        GlobalContestPointsSimulator.initSimulatorWithTestEnvironment(this);
        GlobalContestPointsSimulator gcs = new GlobalContestPointsSimulator();
        gcs.initializeContests(
                500,
                20000,
                false,
                40,
                10,
                15,
                0.02,
                0.40,
                0.04
        );
        gcs.setPointsDistributions(0.3, 0.6, 0.2);
        gcs.runPointDistributionAlgorithm();
        gcs.assertPointDistributions();
    }
}
