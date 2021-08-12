<?php
session_start();
$time=strtotime(date(time()));
include('database_connect.php');

$table_name= $_POST['category'];
$item_name= $_POST['name'];
$description= $_POST['description'];

if(!file_exists($_FILES['uploadedfile']['tmp_name']) || !is_uploaded_file($_FILES['uploadedfile']['tmp_name'])) {

}
if(isset($_POST['submit'])&&is_uploaded_file($_FILES['uploadedfile']['tmp_name'])){
$moveto   = 'C:/xampp/htdocs/Project cse327/item_image';
$tmp_name = $_FILES["uploadedfile"]["tmp_name"];
$name     = $_FILES["uploadedfile"]["name"];
$final_path1= $moveto.'/'.time().$name;
$final_path= '/Project cse327/item_image'.'/'.time().$name;
move_uploaded_file( $tmp_name, $final_path1);

}
else {
  $final_path="";
}

  $mail= $_SESSION["login"];
 $sql= "select user_id from user where mail='$mail'";
 $result= mysqli_query($conn,$sql);
 $row= mysqli_fetch_row($result);

 $sql_itm= "INSERT INTO `item post` (`user_id`, `book_id`) VALUES ('$row[0]', NULL)";
 $result_itm= mysqli_query($conn,$sql_itm);

 $sql_ret= "SELECT MAX(book_id) FROM `item post` WHERE user_id='$row[0]'";
 $result_ret= mysqli_query($conn,$sql_ret);
 $row_ret= mysqli_fetch_row($result_ret);
 if(strcmp($table_name,"book_information")==0){
    $sql_b= "INSERT INTO `book information`(`book_name`, `description`, `post_time`, `image`, `book_id`) VALUES ('$item_name','$description',NOW(),'$final_path','$row_ret[0]')";
    $result_b= mysqli_query($conn,$sql_b);
}
if(strcmp($table_name,"calculator")==0){
   $sql_b= "INSERT INTO `calculator`(`calculator_name`, `description`, `post_time`, `image`, `calculator_id`) VALUES ('$item_name','$description',NOW(),'$final_path','$row_ret[0]')";
   $result_b= mysqli_query($conn,$sql_b);
}
if(strcmp($table_name,"bag")==0){
   $sql_b= "INSERT INTO `bag`(`bag_name`, `description`, `post_time`, `image`, `bag_id`) VALUES ('$item_name','$description',NOW(),'$final_path','$row_ret[0]')";
   $result_b= mysqli_query($conn,$sql_b);
}
if(strcmp($table_name,"note")==0){
   $sql_b= "INSERT INTO `note`(`note_name`, `description`, `post_time`, `image`, `note_id`) VALUES ('$item_name','$description',NOW(),'$final_path','$row_ret[0]')";
   $result_b= mysqli_query($conn,$sql_b);
}
if(strcmp($table_name,"others")==0){
   $sql_b= "INSERT INTO `other`(`item_name`, `description`, `post_time`, `image`, `item_id`) VALUES ('$item_name','$description',NOW(),'$final_path','$row_ret[0]')";
   $result_b= mysqli_query($conn,$sql_b);
}
if(strcmp($table_name,"pendrive")==0){
   $sql_b= "INSERT INTO `pendrive`(`pendrive_name`, `description`, `post_time`, `image`, `pendrive_id`) VALUES ('$item_name','$description',NOW(),'$final_path','$row_ret[0]')";
   $result_b= mysqli_query($conn,$sql_b);
}


?>
