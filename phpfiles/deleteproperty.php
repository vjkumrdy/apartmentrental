<?php
$id=$_GET["id"];
include 'config.php';

$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");

$delete_query_msg1="delete from property_photos where pid=$id";
 mysqli_query($con,$delete_query_msg1);
 
 $delete_query_msg2="delete from fav_list where pid=$id";
 mysqli_query($con,$delete_query_msg2);

$delete_query_msg="delete from msgs where pid=$id";
 mysqli_query($con,$delete_query_msg);

                $delete_query="delete from add_property where pid=$id";
mysqli_query($con,$delete_query);

mysqli_close($con);
echo '{"message":"Property deleted successfully.","status":"true"}';
            


?>