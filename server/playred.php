<?php
$ret = array(
"status" => -1,
"data" => "",
);
$fname = "fongmitv.txt";

if(isGet()){
$psdata = file_get_contents($fname);
$allData = explode("asdasdasd;", $psdata); 
$url=$allData[1];
header("Location: $url");
    exit();
}
function isGet(){
return $_SERVER['REQUEST_METHOD'] == 'GET' ? true : false;
}
?>
