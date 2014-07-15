#!/usr/bin/ruby

$i = 218

def echo_inserts(contest_id, user_ids)
	id_array = user_ids.split("\n")

	id_array.each do |id| 
		puts "INSERT INTO xcolab_ContestTeamMember VALUES  (#{$i}, #{contest_id}, #{id}, \"Judge\");";
		$i += 1
	end
end

#land use
contest_id = 1300205
user_ids = %{1354443
1007210
1298459
1354464
1007188}

echo_inserts(contest_id, user_ids)

#carbon price
contest_id = 1300404
user_ids = %{1421317
1421273
1421295
1475440
1475509}

echo_inserts(contest_id, user_ids)

#urban resilience
contest_id = 1300501
user_ids = %{1462091
1462111
1462131
1423130}

echo_inserts(contest_id, user_ids)

#buildings
contest_id = 1300203
user_ids = %{156853
1475554
1421204
1466954
1466977
1467000}

echo_inserts(contest_id, user_ids)

#transportation
contest_id = 1300202
user_ids = %{1466576
1466596
147165
1008826
1470800}

echo_inserts(contest_id, user_ids)

#shifting behaviour
contest_id = 1300210
user_ids = %{1470048
1466860
1470775
1475356
1466882
1423408}

echo_inserts(contest_id, user_ids)

#coastal resilience
contest_id = 1300801
user_ids = %{1451691
1453367
1466208}

echo_inserts(contest_id, user_ids)

#green buildings
contest_id = 1300403
user_ids = %{1420759
1421339}

echo_inserts(contest_id, user_ids)

#geoengineering
contest_id = 1300209
user_ids = %{1429250
1424772}

echo_inserts(contest_id, user_ids)

#adaptation
contest_id = 1300208
user_ids = %{156311
1470555}

echo_inserts(contest_id, user_ids)

#crowdsourcing disaster risk mgmt
contest_id = 1300402
user_ids = %{1475575
1475595
1475615
1423684
1420983}

echo_inserts(contest_id, user_ids)



# after insertion, query for checking for duplicates:
# SELECT contestId, userId, role, COUNT(*) FROM xcolab_ContestTeamMember GROUP BY contestId, userId, role HAVING COUNT(*) > 1;