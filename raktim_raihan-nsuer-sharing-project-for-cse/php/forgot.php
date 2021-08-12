<?php
session_start();
include_once 'database_connect.php';
$email= $_POST['mail'];

$sql= "select * from user where mail='$email'";
$result= mysqli_query($conn, $sql);
$row= mysqli_fetch_row($result);

if(mysqli_num_rows($result)==1){
  $reciepent= $row[0];
  $subject= "@NSUer Sharing. Passwod reset Automated Mail. <strong> Don't reply back.</strong> ";

  $message= "
  <html>
  <head>
    <title> NSUer Sharing. </title>
  </head>

  <body>
  <p> <Strong> Greetings! From NSUer Sharing. </strong>
  As per your request your password is being sent to you. <br>
  We suggest you to reset your password in most priority. <br>
  Your password is: </p>
  </body>
  </html>". $row[3]."<br> Thanks for being with NSUer Sharing. ";
  $headers = "MIME-Version: 1.0" . "\r\n";
  $headers .= "Content-type:text/html;charset=UTF-8" . "\r\n";

  mail($reciepent,$subject,$message,$headers);


  $_SESSION["message"]= "A  mail is sent to you. ";
  header('Location:../../Project cse327/Login-page/index_redline.php');

  }
else {
  $_SESSION["message"]= "User not found. ";
  header('Location:../../Project cse327/Login-page/index_redline.php');
}

 ?>
