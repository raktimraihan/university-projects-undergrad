<?php
  session_start();
  include_once 'database_connect.php';
  $email= $_POST['mail'];
  $password= $_POST['pass'];

  $sql= "select * from user where mail='$email' and password='$password'";
  $result= mysqli_query($conn, $sql);
  $count= mysqli_num_rows($result);
  $row = mysqli_fetch_row($result);
  if($count==1){
  session_start();
  $_SESSION["login"]=$_POST['mail'];
  $_SESSION["fname"]= $row[1];
  $_SESSION["u_id"]= $row[4];
  header('Location: /Project cse327/Starting pages/index.php');

}
else {
  $_SESSION["message"]= "Wrong User ID or Password.";
  header('Location: ../../Project cse327/Login-page/index_redline.php');
}



?>
