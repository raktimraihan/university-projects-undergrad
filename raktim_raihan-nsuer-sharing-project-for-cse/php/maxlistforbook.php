<?php

include_once 'database_connect.php';

$sql = "select * from `book information` WHERE book_id=(SELECT max(book_id) FROM `book information`)";
$result= mysqli_query($conn,$sql);

while($row=mysqli_fetch_row($result)){
  $_SESSION["bname"]= $row[0];
  $_SESSION["book_des"]= $row[1];
  $_SESSION["book_post"]= $row[2];
  $_SESSION["book_img"]= $row[3];
  $_SESSION["book_id"]= $row[4];
  $book_id= $row[4];
}

$sql_name1 = "SELECT CONCAT(user.first_name,' ',user.last_name) as 'Name' from user, `item post`,`book information` where `item post`.`book_id`='$book_id' and `item post`.`user_id`=`user`.`user_id`";
$result_usr1= mysqli_query($conn,$sql_name1);
while($row_11=mysqli_fetch_row($result_usr1)){
    $_SESSION["book_user_name"]= $row_11[0];
}

?>
