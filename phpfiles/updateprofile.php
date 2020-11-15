<?php

include 'config.php';
$name=$_GET["name"];
$email=$_GET["email"];
$phoneno=$_GET["phoneno"];
$pwd=$_GET["pwd"];
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");


$query_dis="update table_registration set name='$name',phone='$phoneno',password='$pwd' where email='$email' ";
//echo $query_dis; 

$retval_dis = mysqli_query($con,$query_dis);

mysqli_close($con);

echo '{"message":"Updated successfully.","status":"true"}';



?>