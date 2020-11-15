<?php
if (!is_dir('images/')) {
    mkdir('images/', 0777, true);
}
include 'config.php';
 
$pid=$_POST["pid"];


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

            $picimg_url="http://findingaapartment.com/rental/".$picimg_url;
$query_dis="insert into property_photos(pid,photo) values($pid,'$picimg_url');";
//echo $query_dis; 

$retval_dis = mysqli_query($con,$query_dis);



echo '{"message":"Photo added to Property successfully.","status":"true"}';

  
?>