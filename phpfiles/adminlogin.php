<?php
            $email=$_GET["email"];
            $pass=$_GET["pass"];

            include 'config.php';
            $con=mysqli_connect($hostname, $username, $password,$dbname);
            mysqli_query ($con,"set character_set_results='utf8'");
            
            $query_json = "SELECT * from admin where a_email='$email' and a_pass='$pass' ";
            $result = mysqli_query($con,$query_json);
            $row = mysqli_fetch_array($result);
            if(!$row)
            {
                echo '{"message":"Invalid Details","status":"false"}';
            }else{
                echo '{"message":"Admin Login is successfully","status":"true"}';
            }
?>