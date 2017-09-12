package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.contestmanagement.beans.ContestFlagTextToolTipBean;
import org.xcolab.view.pages.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.view.pages.contestmanagement.beans.MassMessageBean;
import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ContestMassActionMethods {
}
