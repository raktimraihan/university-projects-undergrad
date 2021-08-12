<?php

include_once 'database_connect.php';

$sql_bag= "select * from bag WHERE bag_id=(SELECT max(bag_id) FROM `bag`)";
$result_bag= mysqli_query($conn,$sql_bag);
while($row=mysqli_fetch_row($result_bag)){
  $_SESSION["bag_name"]= $row[0];
  $_SESSION["bag_des"]= $row[1];
  $_SESSION["bg_post"]= $row[2];
  $_SESSION["bg_img"]= $row[3];
  $bid= $row[4];
}


if(true){
$sql_name = "SELECT CONCAT(user.first_name,' ',user.last_name) as 'Name' from user, `item post`,bag where '$bid'=`item post`.`book_id` and `item post`.`user_id`=user.user_id";
$result_usr= mysqli_query($conn,$sql_name);
while($row_1=mysqli_fetch_row($result_usr)){
    $_SESSION["bag_user_name"]= $row_1[0];
}

}
?>
