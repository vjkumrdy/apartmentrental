<?php

include 'config.php';
$a_status=$_GET["status"];
$pid=$_GET["id"];
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");

if($a_status == "Deny")
{
$query_dis="update add_property set a_status='$a_status' where pid='$pid' ";
//echo $query_dis; 

$retval_dis = mysqli_query($con,$query_dis);

mysqli_close($con);

echo '{"message":"Denied successfully.","status":"true"}';
}

else if($a_status == "Verified")
{
    $query_dis="update add_property set a_status='$a_status' where pid='$pid' ";
//echo $query_dis; 

$retval_dis = mysqli_query($con,$query_dis);
  echo '{"message":"Verified successfully.","status":"true"}';  
}
else
{
    echo '{"message":"Updated Failed.","status":"false"}';
}
?>