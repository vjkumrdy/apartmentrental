<?php
if (!is_dir('images/')) {
    mkdir('images/', 0777, true);
}
//server granting permisions line
include 'config.php';
 
$name=$_POST["name"];
$email=$_POST["email"];
$phonenumber=$_POST["phonenumber"];
$role=$_POST["role"];
$pwd=$_POST["pwd"];

//$pic=trim($pic,'"');

$result = array("success" => $_FILES["file"]["name"]);
$file_path = basename( $_FILES['file']['name']);
$picimg_url='images/'.$file_path;
if(move_uploaded_file($_FILES['file']['tmp_name'], 'images/'.$file_path)) {
    $result = array("success" => "File successfully uploaded");
} else{
    $result = array("success" => "error uploading file");
}

$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");
$query_json = "SELECT * from table_registration where email='$email';";
            $result = mysqli_query($con,$query_json);
            $row = mysqli_fetch_array($result);
            if(!$row)
            {
$query_dis="insert into table_registration(name,email,phone,role,password,photo) values($name,$email,$phonenumber,$role,$pwd,'$picimg_url');";
//echo $query_dis; 

$retval_dis = mysqli_query($con,$query_dis);

mysqli_close($con);

echo '{"message":"User Registered successfully.","status":"true"}';
}else{
   echo '{"message":"Email Id is already exist.","status":"false"}'; 
}
?>