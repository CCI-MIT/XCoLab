<?php

include_once('simple_html_dom.php');

$handle = fopen("URLS_crawled.txt", "r");
if ($handle) {
    while (($line = fgets($handle)) !== false) {
        //echo 'Bringing images from: '.$line."<br />";
        
        // Create DOM from URL
        $html   = file_get_html($line); //chosen page
        // Find all images
        $images = array();
        foreach ($html->find('img') as $element) {
            $images[] = $element->src;
        }
        reset($images);
        $result = count($images);
        foreach ($images as $out) {
            
            
            if (@getimagesize($out)) {
                echo "$out<br />\n";
                
            } else {
                $file = 'urls_crawled_not_existing.txt';
                $url  = $line . ' | ' . $out . "\n";
                file_put_contents($file, $url, FILE_APPEND | LOCK_EX);
                echo '------------------------- Image not found --------------------' . "$out<br />\n";
            }
        }
    }
    
    fclose($handle);
} else {
    echo 'File didn\'t open';
}



?>
