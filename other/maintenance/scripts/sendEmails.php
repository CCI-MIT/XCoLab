<?php
$c = mysqli_connect("127.0.0.1", "ColabMaintenance", 'xcolab', "ColabMaintenance") or die(mysqli_error($c));
	$r = mysqli_query($c, "select * from email;") or die(mysqli_error($c));

while ($row = mysqli_fetch_row($r)) {
	echo "Sending email to :" . $row[1] . "\n";  
$subject = "Climate CoLab is back online!";
$message = "Dear Climate CoLab member,\n\n" +
	"Thank you very much for waiting for us to make some critical updates to the Climate CoLab. The site is back online now: www.climatecolab.org.\n\n" +
	"Best regards,\nThe Climate CoLab Team";
$from="no-reply@climatecolab.org";

mail($row[1],$subject,$message,"From: $from\n");
  
}
echo "Removing users\n\n";
$r = mysqli_query($c,"DELETE FROM email") or die(mysqli_error($c));
	mysqli_close($c);
 ?>
