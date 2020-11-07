<?php

include 'config.php';
 

$bid=$_GET["bid"];

$lname=$_GET["lname"];
$lmsg=$_GET["lmsg"];

$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");


                $query_dis="update booking set lname='$lname',lmsg='$lmsg' where bid='$bid'";
                //echo $query_dis; 
                
                $retval_dis = mysqli_query($con,$query_dis);
                
                mysqli_close($con);
                
                echo '{"message":"Request send successfully.","status":"true"}';
            
            
         
?>