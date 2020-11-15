<?php

include 'config.php';

//creating connection
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");
$teams_query = "SELECT * FROM add_property";
//executing query with connection
$teams_res = mysqli_query($con,$teams_query);
$rows = array();
//output
while($row = mysqli_fetch_assoc($teams_res)) {
$rows[] = array('p_price' => $row['p_price']);
}

echo json_encode($rows);
//Json End
mysqli_close($con);
?>

