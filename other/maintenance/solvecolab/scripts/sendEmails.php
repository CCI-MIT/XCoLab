<?php
require 'jsonwrapper.php';

$config = parse_ini_file("../config.ini");
$databaseUser = $config["databaseUser"];
$databasePassword = $config["databasePassword"];
$databaseName = $config["databaseName"];

//Configuration for the colab instance
$colabName = "Solve CoLab";
$colabUrl = "www.solvecolab.mit.edu";
$fromUrl = "no-reply@solvecolab.mit.edu";

echo "[INFO] Connecting to database '$databaseName' as '$databaseUser'\n\n";
$c = mysqli_connect("127.0.0.1", $databaseUser, $databasePassword, $databaseName) or die(mysqli_error($c));
$r = mysqli_query($c, "select * from email;") or die(mysqli_error($c));

while ($row = mysqli_fetch_row($r)) {
    echo "[INFO] Sending email to: " . $row[0] . "\n";
    $subject = "$colabName is back online!";
    $message = "Dear $colabName member,<br/> <br />" .
        "Thank you very much for waiting for us to make some critical updates to the $colabName. The site is back online now: $colabUrl.<br /> <br />" .
        "Best regards,<br />The $colabName Team";

    sendEmail($row[0], $fromUrl, $subject, $message);
}
echo "[INFO] Removing users\n\n";
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
    $ch = curl_init('http://localhost:8080/emails-service/emails/send');
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
    if (!$response) {
        echo "[WARN] failed to send email to $to\n";
    }
}
?>