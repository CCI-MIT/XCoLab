package org.xcolab.view.files;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ImageDisplayController {

    private final ImageDisplayService imageDisplayService;

    public ImageDisplayController(ImageDisplayService imageDisplayService) {
        this.imageDisplayService = imageDisplayService;
    }

    @GetMapping("/image/contests/{contestId}")
    public void serveContestImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long contestId) throws IOException {
        try {
            Contest contest = ContestClientUtil.getContest(contestId);
            imageDisplayService.serveImage(request, response,
                    contest.getContestLogoId(), DefaultImage.CONTEST);
        } catch (ContestNotFoundException e) {
            response.sendError(HttpStatus.NOT_FOUND.value(), "Contest not found.");
        }
    }

    @GetMapping("/image/proposals/{proposalId}")
    public void serveProposalImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long proposalId) throws IOException {
        try {
            Proposal proposal = ProposalClientUtil.getProposal(proposalId);
            imageDisplayService.serveImage(request, response,
                    proposal.getImageId(), DefaultImage.PROPOSAL);
        } catch (ProposalNotFoundException e) {
            response.sendError(HttpStatus.NOT_FOUND.value(), "Proposal not found.");
        }
    }

    @GetMapping("/image/members/{memberId}")
    public void serveMemberImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long memberId) throws IOException {
        try {
            Member member = MembersClient.getMember(memberId);
            imageDisplayService.serveImage(request, response,
                    member.getPortraitId(), DefaultImage.PROPOSAL);
        } catch (MemberNotFoundException e) {
            response.sendError(HttpStatus.NOT_FOUND.value(), "Member not found.");
        }
    }

    @GetMapping("/image/uploaded/{imageId}")
    public void serveImage(HttpServletRequest request,
            HttpServletResponse response, @PathVariable long imageId,
            @RequestParam(defaultValue = "NONE") DefaultImage defaultImage) throws IOException {

        imageDisplayService.serveImage(request, response, imageId, defaultImage);
    }

}
