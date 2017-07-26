<?php
http_response_code(503);
?>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet"  href="/css/main.css" />
        <title>Planned maintenance</title>
        <meta http-equiv="Cache-Control" content="no-cache">
    </head>
    <body>
        <?php if (!empty($_POST)): ?>
            <?php
            $config = parse_ini_file("config.ini");
            $c = mysqli_connect("127.0.0.1", $config["databaseUser"], $config["databasePassword"], $config["databaseName"]) or die(mysqli_error($c));
                $r = mysqli_query($c, "INSERT INTO email (email) VALUES ('" . mysqli_real_escape_string($c,$_POST["email"]) . "')") or die(mysqli_error($c));
                mysqli_close($c);
             ?>
            <div class="contestbox">
                Thank you! We will notify you at <?php echo $_POST["email"]?> as soon as we are finished.
            </div>

        <?php else: ?>
            <div class="contestbox">
                <div style="text-align: center;">
                    <img src="/img/logo-climate-colab.png"/>
                </div>
                <p style="text-align: justify;">
                    We are currently performing a planned maintenance on crowdsensor.org. During this time the site will be unavailable. We hope this won't be too much of an inconvenience as we work to perform some necessary upgrades that will provide you with more features in the future.
                </p>
                <p>Sincerely, <br/>The Crowdsensor Team</p>

                </div>
                <div class="contestbox">
                <p style="margin-top:0;">
                    If you would like to get notified once the maintenance period is over, please leave your email below and we'll drop you a line once the site is back online.
                </p>
                <form action=<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?> method="post">
                    <input id="email" name="email" type="text" placeholder="Your email address" style="width:100%;">
                    <a class="c-Button__primary" onclick="get_form(this).submit(); return false" href="javascript:;">Notify me</a>
                </form>
            </div>
        <?php endif; ?>


        <script type="text/javascript">
            //<![CDATA[
            function get_form( element )
            {
                while( element )
                {
                    element = element.parentNode;
                    if( element.tagName.toLowerCase() == "form" )
                    {
                        //alert( element ) //debug/test
                        return element
                    }
                }
                return 0; //error: no form found in ancestors
            }
            //]]>
        </script>
    </body>
</html>
