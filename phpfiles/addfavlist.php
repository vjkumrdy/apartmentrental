<?php

include 'config.php';
 

$email=$_GET["email"];

$p_id=$_GET["p_id"];

$s=$_GET["s"];
$status="Like";

$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");


$query_json = "SELECT * from fav_list where email='$email' and pid='$p_id';";
            $result = mysqli_query($con,$query_json);
            $row = mysqli_fetch_array($result);
            if(!$row)
            {
                $query_dis="insert into fav_list(email,status,pid) values('$email','Like','$p_id');";
                //echo $query_dis; 
                
                $retval_dis = mysqli_query($con,$query_dis);
                
               
                
                echo '{"message":"Add to favourite successfully.","status":"true"}';
            }
            
            else if($s=="Like")
            
            {
                
                
              $query_dis="update fav_list set status='Like' where email='$email' and pid='$p_id';";
                //echo $query_dis; 
                
                $retval_dis = mysqli_query($con,$query_dis);
                
               
                
                echo '{"message":"Like successfully.","status":"true"}';
            }
                
                 else if($s == "Unlike")
                {

              $query_dis="update fav_list set status='Unlike' where email='$email' and pid='$p_id';";
                //echo $query_dis; 
                
                $retval_dis = mysqli_query($con,$query_dis);
                
               
                
                echo '{"message":"Unlike successfully.","status":"true"}';
                }
                
                else
                {
                     echo '{"message":"Something went Wrong","status":"false"}';
                }
            
       
 mysqli_close($con);
?>