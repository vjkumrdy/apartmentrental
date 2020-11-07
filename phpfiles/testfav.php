<?php

include 'config.php';

$email=$_GET['email'];

//creating connection
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");

//SELECT * FROM `add_property` LEFT JOIN fav_list ON add_property.pid=fav_list.pid AND fav_list.email='sam@gmail.com' GROUP BY add_property.pid

//$teams_query = "SELECT CASE WHEN status IS NULL THEN 'Unlike' ELSE status END AS status, add_property.pid,p_name,p_pic,p_type,p_beds,p_bath,p_area,p_pets,p_price,p_owner,a_status,location,description,type,per,email FROM `add_property` LEFT OUTER JOIN fav_list on add_property.pid=fav_list.pid  where a_status='Verified' GROUP BY add_property.pid";

$teams_query="SELECT CASE WHEN status IS NULL THEN 'Unlike' ELSE status END AS status, add_property.pid,p_name,p_pic,p_type,p_beds,p_bath,p_area,p_pets,p_price,p_owner,a_status,location,description,type,per,email FROM `add_property` LEFT JOIN fav_list on add_property.pid=fav_list.pid  AND fav_list.email='$email' AND a_status='Verified' GROUP BY add_property.pid";

//executing query with connection
$teams_res = mysqli_query($con,$teams_query);
$rows = array();
//output
while($row = mysqli_fetch_assoc($teams_res)) {
$rows[] = array('pid' => $row['pid'],'p_name' => $row['p_name'],'p_pic' => $row['p_pic'],'p_type' => $row['p_type'],'p_beds' => $row['p_beds'],'p_bath' => $row['p_bath']
,'p_area' => $row['p_area'],'p_pets' => $row['p_pets'],'p_price' => $row['p_price']
,'p_owner' => $row['p_owner'],'a_status' => $row['a_status'],'location' => $row['location'],'description' => $row['description'],'type' => $row['type']
,'per' => $row['per'],'email' => $row['email'],'status' => $row['status']);
}

echo json_encode($rows);
//Json End
mysqli_close($con);
?>

