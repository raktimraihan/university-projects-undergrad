<?php

include_once 'database_connect.php';

$sql = "select * from `other` WHERE item_id=(SELECT max(item_id) FROM `other`)";
$result= mysqli_query($conn,$sql);

while($row=mysqli_fetch_row($result)){
  $_SESSION["oname"]= $row[0];
  $_SESSION["ook_des"]= $row[1];
  $_SESSION["ook_post"]= $row[2];
  $_SESSION["ook_img"]= $row[3];
  $_SESSION["ook_id"]= $row[4];
  $c_id= $row[4];
}

$sql_name1 = "SELECT CONCAT(user.first_name,' ',user.last_name) as 'Name' from `user`, `item post`,`other` where `item post`.`book_id`='$c_id' and `item post`.`user_id`=`user`.`user_id`";
$result_usr1= mysqli_query($conn,$sql_name1);
while($row_11=mysqli_fetch_row($result_usr1)){
    $_SESSION["ook_user_name"]= $row_11[0];
}
?>
