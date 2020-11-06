<?php

include 'config.php';
 

$pid=$_GET["pid"];

$bname=$_GET["bname"];
$bmsg=$_GET["bmsg"];

$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");

$query_json = "SELECT * from booking where bname='$bname' and pid='$pid';";
            $result = mysqli_query($con,$query_json);
            $row = mysqli_fetch_array($result);
            if(!$row)
            {
                $query_dis="insert into booking(pid,bname,bmsg,lname,lmsg) values('$pid','$bname','$bmsg','','');";
                //echo $query_dis; 
                
                $retval_dis = mysqli_query($con,$query_dis);
                
                mysqli_close($con);
                
                echo '{"message":"Request send successfully.","status":"true"}';
            }
            
            else{
               echo '{"message":"Already Requested This Property.","status":"false"}'; 
            }
?>