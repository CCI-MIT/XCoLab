<?php

//fill in the html table from the staffmembers about pages here

$list = '
<ul> <li>Aditi Agarwal</li> <li><a class="external" href="http://www.linkedin.com/pub/rahul-bhattacharya/2/98/462">Rahul Bhattacharya</a></li> <li>Dan Carnese</li> <li>Sheldon Chang</li> <li>Matt Christiano</li> <li>Patrick Hiesel</li> <li>Josh Introne</li> <li>Stephan Link</li> <li>Alison Lueders</li> <li>Mike Matessa</li> <li>Nikhil Pradhan</li> <li>Yang Ruan</li> <li><a class="external" href="http://www.linkedin.com/pub/michael-spector/2a/81/a74">Michael Spector</a></li> <li><a class="external" href="http://www.mckinsey.com.ar/our_people_profile.asp#14">Ingrid Toppelberg</a></li> <li>Patrick Yamane</li> <li>Sean Zhang</li> </ul>

';
//and set the category id
$categoryId = 8;

/*
LEGEND for categoryId

categories.put(1, "Modeling Steering Committee: External Members");
categories.put(2, "Modeling Steering Committee: Climate CoLab Staff");
categories.put(3, "Expert Advisory Board");
categories.put(4, "Expert Council");
categories.put(5, "Project Staff: Team");
categories.put(6, "Project Staff: Vendors");
categories.put(7, "Project Staff: Advisors");
categories.put(8, "Project Staff: Alumni");
categories.put(9, "Advisors");
categories.put(10, "Judges");
categories.put(11, "Fellows 2014");
categories.put(12, "Fellows 2012 & 2013");
categories.put(13, "Catalysts");


*/



$con = mysqli_connect("localhost", "root", "", "xcolab");
// Check connection
if (mysqli_connect_errno()) {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}


$e = '(<br ?/?>|\s*|&nbsp;)*';
preg_match_all('#<li>(<a[^>]*href="([^"]*)"[^>]*>)?(.*?)(</a>)?</li>#si', $list, $matches);

echo "Matches: ".count($matches[0])."<br>";
echo "<textarea style='font-family: Courier;float:left;width:50%;height:2000px'>";


$cclUrl = 'http://climatecolab.org/web/guest/member/-/member/userId/';

for ($i = 0; $i < count($matches[0]); $i++) {
	$url = $matches[2][$i];
	$name = $matches[3][$i];
	$userId = null;
	$row = null;
	$names = null;

	$names = explode(" ", $name);


	
	$doNotAddUrl = false;

	//first, see if the profile link leads directly to the ccl profile
	if (strpos($url, $cclUrl) !== false || strpos($url, "/web/") === 0) {
		if (substr($url, count($url)-2) == "/") {
			$url = substr($url, 0, -1);
		}
		$userId = substr($url, strrpos($url, "/")+1);
		$doNotAddUrl = true;
	} else {
		//look for the name in the database!
		$query = "SELECT userId FROM xcolab.User_ WHERE firstName = '".$names[0]."' AND lastName = '".$names[1]."';";
		$result = mysqli_query($con, $query);
		
		$row = mysqli_fetch_array($result);
		
		if ($row["userId"]) {
			$userId = $row["userId"];
		}
	}



	echo 'INSERT INTO xcolab_StaffMember'."\n";
	
	if ($userId) {
		echo "	    (userId, categoryId, sort, url)\n	VALUES (\n";
		echo "--  ".$name."\n";
		echo "	   $userId, $categoryId, $i, ";
		if ($doNotAddUrl) {
			echo " NULL";
		} else {
			echo "'".$url."'";
		}
	} else {
		
		echo "	    (categoryId, firstNames, lastName, sort, url)\n	VALUES (\n";
		echo "	   $categoryId,";
		echo "'".$names[0]."', ";
		echo "'".$names[1]."', \n";
		echo "	   $i, ";
		echo "    '".$url."'";
	}
	
	echo ');'."\n\n";
}
echo "</textarea>";
echo '<div style="width:50%;float:right">';
echo $list;
echo '</div>';




mysqli_close($con);