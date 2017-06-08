<?php

$handle = fopen("urls_crawled_not_existing.txt", "r");
if ($handle) {
    while (($url_origin = fgets($handle)) !== false) {
        
        $link_image   = fgets($handle);
        $line3        = $link_image;
        $complete_url = $url_origin . $link_image;
        $splitup      = explode('|', $complete_url);
        //print_r($splitup);
        echo '<br />';
        
        echo '<a href ="' . $url_origin . '">wordpress page</a>  |  ';
        echo '<a href ="' . $link_image . '">missing</a>  |  ';
        $url = str_replace('http://climatecolab.org/', 'http://cognosis.mit.edu:8081/', $link_image);
        echo '<a href ="' . $url . '">fixed</a><br>';
        
        // die();
    }
    
    fclose($handle);
} else {
    //echo 'closed.. cannot continue';
}

?>
