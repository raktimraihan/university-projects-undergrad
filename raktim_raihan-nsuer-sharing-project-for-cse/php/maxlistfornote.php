<?php

include_once 'database_connect.php';

$sql = "select * from `note` WHERE note_id=(SELECT max(note_id) FROM `note`)";
$result= mysqli_query($conn,$sql);

while($row=mysqli_fetch_row($result)){
  $_SESSION["nname"]= $row[0];
  $_SESSION["nook_des"]= $row[1];
  $_SESSION["nook_post"]= $row[2];
  $_SESSION["nook_img"]= $row[3];
  $_SESSION["nook_id"]= $row[4];
  $c_id= $row[4];
}

$sql_name1 = "SELECT CONCAT(user.first_name,' ',user.last_name) as 'Name' from `user`, `item post`,`note` where `item post`.`book_id`='$c_id' and `item post`.`user_id`=`user`.`user_id`";
$result_usr1= mysqli_query($conn,$sql_name1);
while($row_11=mysqli_fetch_row($result_usr1)){
    $_SESSION["nook_user_name"]= $row_11[0];
}

?>
