<?php

$stack = array();


ini_set('display_errors', '0'); # don't show any errors...
error_reporting(E_ALL | E_STRICT); # ...but do log them

// It may take a whils to crawl a site ...
set_time_limit(10000);


// Inculde the phpcrawl-mainclass
include("PHPCrawl_083/libs/PHPCrawler.class.php");

// Extend the class and override the handleDocumentInfo()-method 
class MyCrawler extends PHPCrawler
{
    function handleDocumentInfo($DocInfo)
    {
        // Just detect linebreak for output ("\n" in CLI-mode, otherwise "<br>").
        if (PHP_SAPI == "cli")
            $lb = "\n";
        else
            $lb = "<br />";
        
        // Print the URL and the HTTP-status-Code
        $url = $DocInfo->url;
        
        
        
        
        //and strpos($url, "category") == FALSE   && strpos($url, "wp-json") == FALSE && strpos($url, "/tag/") == FALSE
        if (strpos($url, "/tag/") == FALSE) {
            if (strpos($url, "category") == FALSE) {
                if (strpos($url, "wp-json") == FALSE) {
                    if (substr_count($url, "/") > 5) {
                        echo "---------------------------------------------------------------------Page requested: " . $DocInfo->url . $lb;
                        $file = 'URLS_crawled.txt';
                        $url  = $DocInfo->url . "\n";
                        file_put_contents($file, $url, FILE_APPEND | LOCK_EX);
                        
                    }
                }
            }
        }
        echo "Page requested: " . $DocInfo->url . $lb;
        //echo "Page requested: ".$DocInfo->url.$lb;
        
        // Print the refering URL
        //echo "Referer-page: ".$DocInfo->referer_url.$lb;
        
        // Print if the content of the document was be recieved or not
        //if ($DocInfo->received == true)
        //  echo "Content received: ".$DocInfo->bytes_received." bytes".$lb;
        //else
        //  echo "Content not received".$lb; 
        
        // Now you should do something with the content of the actual
        // received page or file ($DocInfo->source), we skip it in this example 
        
        //echo $lb;
        
        flush();
    }
}

// Now, create a instance of your class, define the behaviour
// of the crawler (see class-reference for more options and details)
// and start the crawling-process.


for ($j = 2009; $j <= 2009; $j++) {
    for ($i = 1; $i <= 12; $i++) {
        
        $crawler = new MyCrawler();
        
        // URL to crawl
        $crawler->setURL('http://news.climatecolab.com/' . $j . '/' . $i . '/');
        
        // Only receive content of files with content-type "text/html"
        //$crawler->addContentTypeReceiveRule("#text/html#");
        
        // Ignore links to pictures, dont even request pictures
        //$crawler->addURLFilterRule("#\.(jpg|jpeg|gif|png)$# i");
        //$crawler->addURLFilterRule("#\.(jpg|jpeg|gif|png)$# i");
        
        // Store and send cookie-data like a browser does
        $crawler->enableCookieHandling(true);
        
        // Set the traffic-limit to 1 MB (in bytes,
        // for testing we dont want to "suck" the whole site)
        //$crawler->setTrafficLimit(1000 * 1024);
        
        // Thats enough, now here we go
        $crawler->go();
        
        // At the end, after the process is finished, we print a short
        // report (see method getProcessReport() for more information)
        $report = $crawler->getProcessReport();
        
        if (PHP_SAPI == "cli")
            $lb = "\n";
        else
            $lb = "<br />";
        
        //echo "Summary:".$lb;
        //echo "Links followed: ".$report->links_followed.$lb;
        //echo "Documents received: ".$report->files_received.$lb;
        //echo "Bytes received: ".$report->bytes_received." bytes".$lb;
        //echo "Process runtime: ".$report->process_runtime." sec".$lb; 	        
        echo 'printing after 1 iteration';
        print_r($stack);
    }
}


?>


<?php

function getImagesFromURL($url)
{
    $html = file_get_contents($url);
    preg_match_all('|<img.*?src=[\'"](.*?)[\'"].*?>|i', $html, $matches);
    //echo $matches[ 1 ][ 0 ];
    
    //print_r(array_filter($matches, "odd"));
    
    $str = $matches[1];
    for ($i = 0; $i < count($str); $i++) {
        if ((endsWith((string) $str[$i], ".jpg") or endsWith((string) $str[$i], ".png")) and strpos((string) $str[$i], 'icon') == FALSE and strpos((string) $str[$i], 'logo') == FALSE) {
            echo $str[$i] . PHP_EOL;
        }
    }
    
    //print_r($matches);
}


for ($j = 2009; $j <= 2009; $j++) {
    for ($i = 1; $i < 12; $i++) {
        
        crawl_page_zeeshan("http://news.climatecolab.com/'.$j.'/'.$i.'/", 1);
    }
}

function startsWith($haystack, $needle)
{
    $length = strlen($needle);
    return (substr($haystack, 0, $length) === $needle);
}

function endsWith($haystack, $needle)
{
    $length = strlen($needle);
    if ($length == 0) {
        return true;
    }
    
    return (substr($haystack, -$length) === $needle);
}

?>
