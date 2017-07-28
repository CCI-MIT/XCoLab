<?php
require 'lib/class.phpmailer.php';
require 'lib/class.smtp.php';

if (file_exists("config.ini")):
    $config = parse_ini_file("config.ini");
else:
    $config = parse_ini_file("config-default.ini");
endif;
$databaseUser = $config["databaseUser"];
$databasePassword = $config["databasePassword"];
$databaseName = $config["databaseName"];

//Configuration for the colab instance
$colabName = $config["colabName"];
$colabUrl = $config["colabUrl"];
$fromEmail = $config["fromEmail"];

//Email config
$smtpHost = $config["smtpHost"];
$smtpPort = $config["smtpPort"];
$smtpUser = $config["smtpUser"];
$smtpPassword = $config["smtpPassword"];


echo "[INFO] Connecting to database '$databaseName' as '$databaseUser'\n\n";
$c = mysqli_connect("127.0.0.1", $databaseUser, $databasePassword, $databaseName) or die(mysqli_error($c));
$r = mysqli_query($c, "select * from email;") or die(mysqli_error($c));

while ($row = mysqli_fetch_row($r)) {
    echo "[INFO] Sending email to: " . $row[0] . "\n";
    $subject = "$colabName is back online!";
    $message = "Dear $colabName member,<br/> <br />" .
        "Thank you very much for waiting for us to make some critical updates to the $colabName. The site is back online now: $colabUrl.<br /> <br />" .
        "Best regards,<br />The $colabName Team";

    sendEmail($row[0], $fromEmail, "The $colabName Team", $subject, $message,
            $smtpHost, $smtpPort, $smtpUser, $smtpPassword);
}
echo "[INFO] Removing users\n\n";
$r = mysqli_query($c,"DELETE FROM email") or die(mysqli_error($c));
        mysqli_close($c);

function sendEmail($to, $fromAddress, $fromName, $subject, $emailBody,
                   $smtpHost, $smtpPort, $smtpUser, $smtpPassword) {
    $mail = new PHPMailer;

    $mail->IsSMTP();
    $mail->SMTPAuth = true;
    $mail->Host = $smtpHost;
    $mail->Port = $smtpPort;
    $mail->Username = $smtpUser;
    $mail->Password = $smtpPassword;

    $mail->SetFrom($fromAddress, $fromName);
    $mail->Subject = $subject;
    $mail->MsgHTML($emailBody);
    $mail->AddAddress($to);

    if ($mail->Send()) {
        echo "Message sent!";
    } else {
        echo "[WARN] failed to send email to $to.\n" . $mail->ErrorInfo;
    }
 }
 ?>
