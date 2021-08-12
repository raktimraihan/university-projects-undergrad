<?php

include_once 'database_connect.php';

$sql = "select * from `pendrive` WHERE pendrive_id=(SELECT max(pendrive_id) FROM `pendrive`)";
$result= mysqli_query($conn,$sql);

while($row=mysqli_fetch_row($result)){
  $_SESSION["pname"]= $row[0];
  $_SESSION["pook_des"]= $row[1];
  $_SESSION["pook_post"]= $row[2];
  $_SESSION["pook_img"]= $row[3];
  $_SESSION["pook_id"]= $row[4];
  $c_id= $row[4];
}

$sql_name1 = "SELECT CONCAT(user.first_name,' ',user.last_name) as 'Name' from `user`, `item post`,`pendrive` where `item post`.`book_id`='$c_id' and `item post`.`user_id`=`user`.`user_id`";
$result_usr1= mysqli_query($conn,$sql_name1);
while($row_11=mysqli_fetch_row($result_usr1)){
    $_SESSION["pook_user_name"]= $row_11[0];
}


?>
