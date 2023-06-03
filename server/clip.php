<?php
$ret = array(
"status" => -1,
"data" => "",
);
$fname = "fongmitv.txt";

if(isGet()){
$psdata = file_get_contents($fname);
if($_GET["clear"] == 1){
;//echo "clear";
}
echo $psdata;
	;
}else{
$psdata = file_get_contents("php://input");
$pdata = json_decode($psdata , true);

$ret["data"] = $pdata["message"];
$ret["status"] = 0;
$xieru = time()."asdasdasd;".$pdata["message"];
file_put_contents("fongmitv.txt",$xieru);
echo json_encode($ret);
}
function isGet(){
return $_SERVER['REQUEST_METHOD'] == 'GET' ? true : false;
}
?>
