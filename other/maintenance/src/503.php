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
    <title>Oh Snap - <?= $colabName ?></title>
</head>
<body>

    <div class="contestbox">
        <div class="text-center"><img src="<?= $logoPath ?>"/></div>
        <br />
        <h1>Oh Snap!</h1>
        <p style="text-align: justify;">
            Looks like you caught us at a bad time - the <?= $colabName ?> is not available right now.
            Our engineers are working on getting the page back up.
            Please try again in a few minutes.
        </p>
        <p>
            Sincerely, <br/>
            The <?= $colabName ?> Team
        </p>
    </div>

</body>
</html>
