<?php
  session_start();

  include_once 'database_connect.php';
  $fname= $_POST['firstname'];
  $lname= $_POST["last_name"];
  $email= $_POST["email"];
  $password= $_POST["password"];
  $cpassword= $_POST["confirm_password"];

  $_SESSION["message"]="";
  $sql = "insert into user values ('$email','$fname','$lname','$password')";
  if(mysqli_query($conn,$sql)){
  $_SESSION["message"]= "Sign up Complete. Use mail and Password to log in. ";
  header('Location: /Project cse327/Login-page/index_redline.php');
  }
  else{
  $sql1 = "select * from user where mail='$email'";
  $result= mysqli_query($conn,$sql1);
  if(mysqli_num_rows($result)==1){
  $_SESSION["message"]  = "User Name Exists.";
        }
    header('Location: /Project cse327/Login-page/index_redline.php');
 }
 ?>
