package org.xcolab.service.flagging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.service.flagging.domain.report.ReportDao;
import org.xcolab.service.flagging.domain.reportTarget.ReportTargetDao;

@Service
public class FlaggingService {

    private ReportDao reportDao;
    private ReportTargetDao reportTargetDao;

    @Autowired
    public FlaggingService(ReportDao reportDao, ReportTargetDao reportTargetDao) {
        this.reportDao = reportDao;
        this.reportTargetDao = reportTargetDao;
    }
}
