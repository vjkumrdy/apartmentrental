<?php
            $email=$_GET["email"];
            $p=$_GET["pwd"];
            $role=$_GET["role"];

            include 'config.php';
            $con=mysqli_connect($hostname, $username, $password,$dbname);
            mysqli_query ($con,"set character_set_results='utf8'");
              
             $query_json = "SELECT * from table_registration where email='$email' and password='$p' and role ='$role'";
            $result = mysqli_query($con,$query_json);
            $row = mysqli_fetch_array($result);
            if(!$row)
            {
                echo '{"message":"Invalid Details","status":"false"}';
            }else{
                echo '{"message":"Login successfully","status":"true"}';
            }
           
?>