<?php
  
  $servername="localhost";
  $username="root";
  $password="";
  $db= "nsuer_sharing";

  $conn= mysqli_connect("$servername", "$username", "$password", "$db");

  if(!$conn){
    die("Error in connecting ". mysqli_connect_error);
  }

  ?>
