<?php
http_response_code(503);
header("Cache-Control: no-store, no-cache, must-revalidate, max-age=0");
header("Cache-Control: post-check=0, pre-check=0", false);
header("Pragma: no-cache");

if (file_exists("../config.ini")):
    $config = parse_ini_file("../config.ini");
elseif (file_exists("config.ini")):
    $config = parse_ini_file("config.ini");
else:
    $config = parse_ini_file("../config-default.ini");
endif;

$colabName = $config['colabName'];
$colabUrl = $config['colabUrl'];
$colabUrlShort = $config['colabUrlShort'];
$emailEnabled = $config['emailEnabled'];

$logoPath = $config['logoPath'];
?>

<?php include 'includes/css.php'; ?>

<!DOCTYPE html>
<html>
  <head>
      <title>Planned maintenance - <?= $colabName ?></title>
  </head>
  <body>

<?php if (!empty($_POST)): ?>

    <?php
    //if(!filter_var($_POST["email"], FILTER_VALIDATE_EMAIL)) die('Incorrect email.');
    $c = mysqli_connect("127.0.0.1", $config["databaseUser"], $config["databasePassword"], $config["databaseName"]) or die(mysqli_error($c));
        $r = mysqli_query($c, "INSERT INTO email (email) VALUES ('" . mysqli_real_escape_string($c,$_POST["email"]) . "')") or die(mysqli_error($c));
        mysqli_close($c);
     ?>
    <div class="contestbox">
        Thank you! We will notify you at <?= $_POST["email"] ?> as soon as we are finished.
    </div>

<?php else: ?>

    <div class="contestbox">
        <div class="text-center"><img src="<?= $logoPath ?>"/></div>
        <p style="text-align: justify;">
        We are currently performing a planned maintenance on <?= $colabUrlShort ?>.
            During this time the site will be unavailable.
            We hope this won't be too much of an inconvenience as we work to perform some
            necessary upgrades that will provide you with more features in the future.
        </p>
        <p>
            Sincerely, <br/>
            The <?= $colabName ?> Team
        </p>
    </div>

    <?php if ($emailEnabled): ?>
    <div class="contestbox">
        If you would like to get notified once the maintenance period is over,
        please leave your email below and we'll drop you a line once the site is back online.
        <br /> <br/>
        <form action="<?= htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
            <input class="text-center" name="email" type="email" placeholder="Your email address" />
            <button class="c-Button__primary block">Notify me</button>
        </form>
    </div>
    <?php endif; ?>

<?php endif; ?>
  </body>
</html>
