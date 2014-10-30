#!/usr/bin/ruby -w

require 'mysql2'
require 'sanitize'

$client = Mysql2::Client.new(:host => "localhost", :username => "root", :database => "xcolab_old")


def get_attribute_for_proposal_version(proposal_id, version, attribute_name)
  $client.query("
    SELECT * FROM xcolab_ProposalAttribute
    WHERE proposalId = #{proposal_id} AND
          name = '#{attribute_name}' AND
          version >= #{version} AND
          versionWhenCreated <= #{version};
  ")
end

def get_attribute_for_proposal_version_and_additional_id(proposal_id, version, attribute_name, additional_id)
  $client.query("
    SELECT * FROM xcolab_ProposalAttribute
    WHERE proposalId = #{proposal_id} AND
          name = '#{attribute_name}' AND
          additionalId = #{additional_id} AND
          version >= #{version} AND
          versionWhenCreated <= #{version};
  ")
end

def get_string_value_if_set(attr)
  !attr.nil? && !attr.first.nil? ? attr.first["stringValue"] : ""
end


contests = $client.query("SELECT * FROM xcolab_Contest WHERE created > '2012-01-01';")

contests.each do |contest|
  proposal_ids_in_contest = $client.query("
    SELECT DISTINCT
    p2p.proposalId
    FROM
    xcolab_old.xcolab_ContestPhase cp,
    xcolab_Proposal2Phase p2p
    WHERE
    cp.ContestPhasePK = p2p.contestPhaseId AND
    contestPK = #{contest['ContestPK']};
  ").map { |obj| obj["proposalId"] }

  plan_sections_in_contest = $client.query("
    SELECT psd.title, pts.planSectionId FROM xcolab_PlanTemplateSection pts, xcolab_PlanSectionDefinition psd  WHERE psd.id_ = pts.planSectionId AND pts.planTemplateId = #{contest['planTemplateId']} ORDER BY weight ASC;
  ")

  proposal_ids_in_contest.each do |proposal_id|
    versions = $client.query("SELECT * FROM xcolab_ProposalVersion WHERE proposalId = #{proposal_id};")

    versions.each do |version|
      out_file_html = File.new("html/contest#{contest['ContestPK']}.proposal#{proposal_id}.v#{version["version"]}.txt", "w")
      out_file_plain = File.new("plain/contest#{contest['ContestPK']}.proposal#{proposal_id}.v#{version["version"]}.txt", "w")
      #name
      name = get_string_value_if_set(get_attribute_for_proposal_version(proposal_id, version["version"], "NAME"))
      out_file_html.print "NAME:\n"
      out_file_plain.print "NAME:\n"
      out_file_html.print name+"\n\n"
      out_file_plain.print Sanitize.clean(name)+"\n\n"

      #pitch
      pitch = get_string_value_if_set(get_attribute_for_proposal_version(proposal_id, version["version"], "PITCH"))
      out_file_html.print "PITCH:\n"
      out_file_plain.print "PITCH:\n"
      out_file_html.print pitch+"\n\n"
      out_file_plain.print Sanitize.clean(pitch)+"\n\n"

      #description
      description = get_string_value_if_set(get_attribute_for_proposal_version(proposal_id, version["version"], "DESCRIPTION"))
      out_file_html.print "DESCRIPTION:\n"
      out_file_plain.print "DESCRIPTION:\n"
      out_file_html.print description+"\n\n"
      out_file_plain.print Sanitize.clean(description)+"\n\n"

      plan_sections_in_contest.each do |plan_section|
        text = get_string_value_if_set(get_attribute_for_proposal_version_and_additional_id(proposal_id, version["version"], "SECTION", plan_section["planSectionId"]))
        out_file_html.print "#{plan_section["title"]}:\n"
        out_file_plain.print "#{plan_section["title"]}:\n"
        out_file_html.print text+"\n\n"
        out_file_plain.print Sanitize.clean(text)+"\n\n"
      end
      out_file_html.flush
      out_file_html.close
      out_file_plain.flush
      out_file_plain.close
    end
  end


end