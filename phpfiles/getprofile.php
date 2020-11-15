<?php

include 'config.php';
$email=$_GET["email"];

$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");
$query_json ="SELECT * from table_registration where email='$email'" ;
$retval_json = mysqli_query($con,$query_json);
$rows = array();
while($row = mysqli_fetch_assoc($retval_json)) {
$rows[] = array('name' => $row['name'],'email' => $row['email'],'phone' => $row['phone'],'role' => $row['role'],'password' => $row['password'],'photo' => $row['photo']);
}

echo json_encode($rows);
//Json End
mysqli_close($con);
?>