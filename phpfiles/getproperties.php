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
$rows[] = array('pid' => $row['pid'],'p_name' => $row['p_name'],'p_pic' => $row['p_pic'],'p_type' => $row['p_type'],'p_beds' => $row['p_beds'],'p_bath' => $row['p_bath']
,'p_area' => $row['p_area'],'p_pets' => $row['p_pets'],'p_price' => $row['p_price'],'p_owner' => $row['p_owner']
,'a_status' => $row['a_status'],'location' => $row['location'],'description' => $row['description'],'type' => $row['type'],'per' => $row['per']);
}

echo json_encode($rows);
//Json End
mysqli_close($con);
?>

