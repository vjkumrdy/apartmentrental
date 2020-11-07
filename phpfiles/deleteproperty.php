<?php
$id=$_GET["id"];
include 'config.php';

$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");


                $delete_query="delete from add_property where pid=$id";
$retval_dis = mysqli_query($con,$delete_query);
$result = mysqli_query($con,$retval_dis);
mysqli_close($con);
echo '{"message":"Property deleted successfully.","status":"true"}';
            


?>