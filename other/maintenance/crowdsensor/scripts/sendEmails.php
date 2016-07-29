<?php
require 'jsonwrapper.php';

$c = mysqli_connect("127.0.0.1", "ColabMaintenance", 'ColabMainten234234ance__', "ColabMaintenance") or die(mysqli_error($c));
        $r = mysqli_query($c, "select * from email;") or die(mysqli_error($c));

while ($row = mysqli_fetch_row($r)) {

        echo "Sending email to: " . $row[1] . "\n";
$subject = "Climate CoLab is back online!";
$message = "Dear Climate CoLab member,\n\n" .
        "Thank you very much for waiting for us to make some critical updates to the Climate CoLab. The site is back online now: www.climatecolab.org.\n\n" .
        "Best regards,\nThe Climate CoLab Team";
$from="no-reply@climatecolab.org";

//mail($row[1],$subject,$message,"From: $from\n");
sendEmail($row[1], $from, $subject, $message);
}
echo "Removing users\n\n";
$r = mysqli_query($c,"DELETE FROM email") or die(mysqli_error($c));
        mysqli_close($c);

function sendEmail($to, $from, $subject, $emailBody){
        $postData = array(
            'to' => array($to),
            'from' => $from,
            'subject' => $subject,
            'emailBody' => $emailBody,
        	'html' => true,
        	'replyTo' => ''
        );

        // Setup cURL
        $ch = curl_init('http://localhost:8080/emails-service/sendEmail');
        curl_setopt_array($ch, array(
            CURLOPT_POST => TRUE,
            CURLOPT_RETURNTRANSFER => TRUE,
            CURLOPT_HTTPHEADER => array(
                'Content-Type: application/json'
            ),
            CURLOPT_POSTFIELDS => json_encode($postData)
        ));

        // Send the request
        $response = curl_exec($ch);

 }
 ?>