<?php
      include("database_connect.php");
      $sql = "SELECT * FROM `book information`";
      $result= mysqli_query($conn,$sql);

      while($row= mysqli_fetch_row($result)){
          echo "".$row[0];
      }
      echo "go";
  ?>
