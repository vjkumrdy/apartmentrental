<?php

include 'config.php';

$pid=$_GET["id"];

$pname=$_GET["pname"];
$ptype=$_GET["ptype"];
$pbeds=$_GET["pbeds"];
$pbath=$_GET["pbath"];
$parea=$_GET["parea"];
$ppets=$_GET["ppets"];
$pprice=$_GET["pprice"];

$des=$_GET["des"];

$loc=$_GET["location"];

$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");


$query_dis="update add_property set p_name='$pname',p_type='$ptype',p_beds='$pbeds',
p_bath='$pbath',p_area='$parea',p_pets='$ppets',p_price='$pprice',location='$loc',description='$des' where pid='$pid' ";
//echo $query_dis; 

$retval_dis = mysqli_query($con,$query_dis);

mysqli_close($con);

echo '{"message":"Updated successfully.","status":"true"}';



?>