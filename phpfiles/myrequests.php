<?php

include 'config.php';

$email=$_GET["email"];

//creating connection
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");
$teams_query = "SELECT DISTINCT(pid),frm,eto FROM `msgs` where eto='$email'";
//executing query with connection
$teams_res = mysqli_query($con,$teams_query);
$rows = array();
//output
while($row = mysqli_fetch_assoc($teams_res)) {
$rows[] = array('id' => $row['id'],'pid' => $row['pid'],'frm' => $row['frm'],'eto' => $row['eto'],'message' => $row['message']);
}

echo json_encode($rows);
//Json End
mysqli_close($con);
?>

