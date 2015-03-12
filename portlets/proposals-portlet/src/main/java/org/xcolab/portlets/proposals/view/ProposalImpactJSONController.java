package org.xcolab.portlets.proposals.view;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

/**
 * Created by kmang on 12/03/15.
 */

@Controller
@RequestMapping("view")
public class ProposalImpactJSONController {

    @Autowired
    private ProposalsContext proposalsContext;

    @ResourceMapping("proposalPicker")
    public void proposalPicker(
            ResourceRequest request,
            ResourceResponse response,
            @RequestParam(value = "type", required = false) String requestType,
            @RequestParam(value = "filterKey", required = false) String filterType,
            @RequestParam(required = false) String filterText,
            @RequestParam(required = false) int start,
            @RequestParam(required = false) int end,
            @RequestParam(required = false) String sortOrder,
            @RequestParam(required = false) String sortColumn,
            @RequestParam(required = false) Long sectionId,
            @RequestParam(required = false) long contestPK) throws IOException,
            SystemException, PortalException {

    }
}
