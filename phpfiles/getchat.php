<?php

include 'config.php';

$frm=$_GET['frm'];
$eto=$_GET['eto'];
$pid=$_GET['pid'];



//creating connection
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");

$teams_query="SELECT * from msgs where (frm='$frm' AND pid='$pid' AND eto='$eto') or (frm='$eto' AND pid='$pid' AND eto='$frm')   ";
//executing query with connection
$teams_res = mysqli_query($con,$teams_query);
$rows = array();
//output
while($row = mysqli_fetch_assoc($teams_res)) {
$rows[] = array('pid' => $row['pid'],'frm' => $row['frm'],'eto' => $row['eto'],'message' => $row['message']);
}

echo json_encode($rows);
//Json End
mysqli_close($con);
?>

