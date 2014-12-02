#!/usr/bin/ruby -w

require 'mysql2'
require 'sanitize'

$client = Mysql2::Client.new(:host => "localhost", :username => "root", :database => "xcolab_prod2")

contests_affected = $client.query('
SELECT ContestPK
FROM xcolab_Contest c
WHERE EXISTS(
    SELECT DISTINCT proposalId
    FROM xcolab_ProposalAttribute
    WHERE
      additionalId NOT IN (
        SELECT DISTINCT pts.planSectionId
        FROM
            xcolab_PlanTemplateSection pts,
            xcolab_Contest co
        WHERE
            pts.plantemplateId = co.planTemplateId AND
            co.ContestPK = c.ContestPK
        )
        AND
        proposalId IN (
          SELECT DISTINCT p2p.proposalId
          FROM
              xcolab_Proposal2Phase p2p,
              xcolab_ContestPhase cp
          WHERE
              p2p.contestphaseId = cp.contestphasepk AND
              cp.ContestPk = c.ContestPK
      )
      AND additionalId != 0
      AND name = "SECTION"
)')

proposals_affected = {}

contests_affected.each do |contest_id|
  c = contest_id["ContestPK"]
  #find the affected proposals
  proposals = $client.query("
	SELECT proposalId, additionalId, MAX(version) AS version, MAX(stringValue) AS stringValue
	FROM xcolab_ProposalAttribute
	WHERE
		additionalId NOT IN (
			SELECT DISTINCT pts.planSectionId
			FROM xcolab_PlanTemplateSection pts, xcolab_Contest co
			WHERE pts.plantemplateId = co.planTemplateId AND
			co.ContestPK = #{c}
		)
		AND
		proposalId IN (
			SELECT DISTINCT p2p.proposalid
			FROM
			xcolab_Proposal2Phase p2p,
			xcolab_ContestPhase cp
			WHERE
			p2p.contestphaseId = cp.contestphasepk AND
			cp.ContestPk = #{c}
		)
    AND additionalId != 0
    AND name = 'SECTION'
GROUP BY
  proposalId, additionalId
	")

  puts "Contest "+c.to_s+": "+proposals.count.to_s+" proposal/section pairs affected"
  puts ""
  #puts "Proposals and their planTemplateSectionIds which do not exist in the plan template:"
  puts "Proposals which have orphaned planTemplateIds and have never been moved:"
  proposals.each do |p|
    #find out if proposal was ever moved to another contest (= there are contest phases of at least two different contests)
    contest_count = $client.query("
      SELECT COUNT(DISTINCT ContestPK) AS count
      FROM xcolab_Proposal2Phase p2p, xcolab_ContestPhase cp
      WHERE proposalId = #{p["proposalId"]} AND p2p.contestPhaseId = cp.ContestPhasePK;"
    )
    if contest_count.first["count"] < 2
      puts "pId: "+p["proposalId"].to_s+", sectionId: "+p["additionalId"].to_s+", maxV: "+p["version"].to_s+", section-string-length: "+p["stringValue"].length.to_s
      proposals_affected[p["proposalId"]] = c
    end

  end
  puts "---"
  puts ""



end

puts "TOTAL proposals affected which have not been moved: "+proposals_affected.size.to_s
puts "Links:"
proposals_affected.each do |key, value|
  puts "http://climatecolab.org/web/guest/plans/-/plans/contestId/#{value}/planId/#{key}"
end