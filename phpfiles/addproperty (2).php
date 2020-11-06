<?php
if (!is_dir('images/')) {
    mkdir('images/', 0777, true);
}
include 'config.php';
 
$pname=$_POST["pname"];
$ptype=$_POST["ptype"];
$pbeds=$_POST["pbeds"];
$pbath=$_POST["pbath"];
$parea=$_POST["parea"];
$ppets=$_POST["ppets"];
$pprice=$_POST["pprice"];
$powner=$_POST["powner"];
$a_status="Deny";
$t="Rental";
$d=$_POST["des"];
$p=$_POST["per"];

$loc=$_POST["location"];
//$loc="Quebec";

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
$query_dis="insert into add_property(p_name,p_type,p_beds,p_bath,p_area,p_pets,p_price,p_owner,p_pic,a_status,location,type,description,per) values($pname,$ptype,$pbeds,$pbath,$parea,$ppets,$pprice,$powner,'$picimg_url','Deny',$loc,'Rental',$d,$p);";
//echo $query_dis; 

$retval_dis = mysqli_query($con,$query_dis);



echo '{"message":"Property Added successfully.","status":"true"}';

  
?>