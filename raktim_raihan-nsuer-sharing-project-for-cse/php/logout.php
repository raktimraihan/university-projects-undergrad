<?php
session_start();
session_unset();


if(!isset($_SESSION["fname"])){
  header('Location: ../../Project cse327/Login-page/index.php');
}
 ?>
