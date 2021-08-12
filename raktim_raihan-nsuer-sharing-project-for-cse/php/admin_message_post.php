<?php
session_start();
include_once 'database_connect.php';
$sender= $_SESSION["login"];
$phone= $_POST["phone"];
$message=$_POST["message"];
$name= $_SESSION["fname"];

$sql = "INSERT INTO `admin_message` (`sender`, `phone`, `message`, `message_id`, `name`) VALUES ('$sender', '$phone', '$message', NULL,'$name')";
$result= mysqli_query($conn,$sql);

if($result){
    $_SESSION["message_admin"]= "We will reach you soon, Help us to grow Faster.";
}
else{
    $_SESSION["message_admin"]= "Something went wrong. Please try again.";
}
header('Location: /Project cse327/Starting pages/contact1.php');

?>
