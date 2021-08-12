<?php

include_once 'database_connect.php';

$sql = "select * from `calculator` WHERE calculator_id=(SELECT max(calculator_id) FROM `calculator`)";
$result= mysqli_query($conn,$sql);

while($row=mysqli_fetch_row($result)){
  $_SESSION["cname"]= $row[0];
  $_SESSION["cook_des"]= $row[1];
  $_SESSION["cook_post"]= $row[2];
  $_SESSION["cook_img"]= $row[3];
  $_SESSION["cook_id"]= $row[4];
  $c_id= $row[4];
}

$sql_name1 = "SELECT CONCAT(user.first_name,' ',user.last_name) as 'Name' from `user`, `item post`,`calculator` where `item post`.`book_id`='$c_id' and `item post`.`user_id`=`user`.`user_id`";
$result_usr1= mysqli_query($conn,$sql_name1);
while($row_11=mysqli_fetch_row($result_usr1)){
    $_SESSION["cook_user_name"]= $row_11[0];
}

?>
