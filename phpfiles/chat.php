<?php

include 'config.php';
 

$pid=$_GET["pid"];
$frm=$_GET["frm"];
$eto=$_GET["eto"];
$m=$_GET["message"];


$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");


                $query_dis="insert into msgs(pid,frm,eto,message) values('$pid','$frm','$eto','$m');";
                //echo $query_dis; 
                
                $retval_dis = mysqli_query($con,$query_dis);
                
               
                
                echo '{"message":"Message send successfully.","status":"true"}';
                
                 mysqli_close($con);
          
?>