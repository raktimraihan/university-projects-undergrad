<?php session_start(); ?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <!--font awesome integration link to import icons -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
    <link rel="shortcut icon" type="image/gif/png" href="img/725210_users_512x512.png"> <!--title bar icon import from img folder-->
    <title> NSUer Sharing. </title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/modern-business.css" rel="stylesheet">

  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <!--navbar title and image section-->
        <img src="img/725210_users_512x512.png" type="image/png" style="height: 30px; Width: 30px; margin-right: 10px; filter: invert(100%);"><a class="navbar-brand" href="index.php">Nsu-er Sharing</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <!--navbar item section-->
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link" href="about.php">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="services.php">Services</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="contact.php">Contact</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Items
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
                <a class="dropdown-item" href="portfolio-4-col.php?page_visit=1">Books.</a>
                <a class="dropdown-item" href="portfolio-4-col0.php?page_visit=1">Calculator.</a>
                <a class="dropdown-item" href="portfolio-4-col1.php?page_visit=1">Pendrive.</a>
                <a class="dropdown-item" href="portfolio-4-col2.php?page_visit=1">Bag.</a>
                <a class="dropdown-item" href="portfolio-4-col3.php?page_visit=1">Notes.</a>
                <a class="dropdown-item" href="portfolio-4-col4.php?page_visit=1">Others.</a>
              </div>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="blog-home-1.php?page_visit=1"> Blog </a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle btn btn-outline-primary"style="border: 5px; color: white;" href="#" id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <?php echo "".$_SESSION["fname"]; ?>
              </a>
              <div class="dropdown-menu dropdown-menu-right aria-labelledby="navbarDropdownBlog">
                <a class="dropdown-item" href="blog-home-2.php?page_visit=1">Blog Posts</a>
                <a class="dropdown-item" href="adproduct.php">Post an Ad</a>
                <a class="dropdown-item" href="portfolio-1-col.php?page_visit=1">Items Posted for Sharing</a>
                <a class="dropdown-item" href="faq.php">FAQ</a>
                <a class="dropdown-item" href="#"> Settings</a>
                <a class="dropdown-item" href="../../Project cse327/php/logout.php">Log out</a>

              </div>
            </li>
          </ul>
        </div>
      </div>
    </nav>


    <!-- Page Content -->
    <div class="container">

      <!-- Page Heading/Breadcrumbs -->
      <h1 class="mt-4 mb-3">Pendrives
        <small>Available now..</small>
      </h1>

      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="index.html">Home</a>
        </li>
        <li class="breadcrumb-item active">Pendrive</li>
      </ol>

        <div class="row">
  <?php
                include("../../Project cse327/php/database_connect.php");
                $page= $_GET["page_visit"];
                if($page=="" || $page=="1"){
                  $page_passed= 0;

                }
                else{
                  $page_passed= ($page*8)-8;
                }
                $sql = "SELECT * FROM `pendrive` order by pendrive_id desc limit $page_passed,8  ";
                $sql1 = "SELECT CONCAT(first_name,last_name) as 'Name' FROM `pendrive` ,user,`item post`
                        where  `pendrive`.`pendrive_id`=`item post`.`book_id` and `item post`.`user_id`=user.user_id";
                $result= mysqli_query($conn,$sql);
                $result1= mysqli_query($conn, $sql1);

        while($row= mysqli_fetch_row($result)){ ?>
        <div class="col-lg-3 col-md-4 col-sm-6 portfolio-item">
          <div class="card h-100">
            <a href="#"><img class="card-img-top" src="<?php if($row[3]){ echo $row[3];} else {echo "http://placehold.it/500x400";} ?>" height= "400" width="500"  alt=""></a>
            <div class="card-body">
              <h4 class="card-title">
                <a href="#"><?php echo $row[0]; ?></a>
              </h4>
              <p class="card-text"><?php echo $row[1]; ?></p>
              <p class="card-text align-text-bottom"><?php $date= explode(" ",$row[2]); echo "Posted on: ". $date[0]."<br>"; echo "Posting time: ".date('H:i:s',strtotime($date[1])); ?> </p><br>
              <p class= "card-text align-text-bottom">
              <?php
              $sql2 = "SELECT * From\n"
                        . "(\n"
                        . "SELECT CONCAT(first_name,' ',last_name) as \"Name\", `item post`.book_id FROM `pendrive` ,user,`item post`\n"
                        . "where  `pendrive`.`pendrive_id`=`item post`.`book_id` and `item post`.`user_id`=user.user_id) as t where t.book_id= $row[4]";
                          $result2= mysqli_query($conn, $sql2);
                          while($row2= mysqli_fetch_row($result2)){
                            echo "Posted by= ".$row2[0];
                          }
              ?>
            </div>
          </div>
        </div>
  <?php }?>
      </div>
  <?php
    $sql1 = "SELECT * FROM `pendrive`";
    $result1= mysqli_query($conn,$sql1);
    $cnt= mysqli_num_rows($result1);
    $page=ceil($cnt/8);
  ?>

      <!-- Pagination -->
      <ul class="pagination justify-content-center">
        <li class="page-item">
          <a class="page-link" href="portfolio-4-col1.php?page_visit=<?php if($_GET["page_visit"]==1){echo 1;}else {echo $_GET["page_visit"]-1;} ?>" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
            <span class="sr-only">Previous</span>
          </a>
        </li>
        <?php
        if($page<11){
        for($b=1;$b<=$page;$b++){?>
        <li class="page-item">
          <a class="page-link" href="portfolio-4-col1.php?page_visit=<?php echo $b; ?>"><?php echo $b; ?></a>
        </li>
      <?php }  }
      else{
        for($b=$_GET['page_visit'];$b<=$_GET['page_visit']+10;$b++){?>
          <li class="page-item">
            <a class="page-link" href="portfolio-4-col1.php?page_visit=<?php echo $b; ?>"><?php echo $b; ?></a>
          </li>
        <?php   }
      }
?>


        <li class="page-item">
          <a class="page-link" href="portfolio-4-col1.php?page_visit=<?php if($_GET["page_visit"]>$page-1){echo 1;}else {echo $_GET["page_visit"]+1;} ?>" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
            <span class="sr-only">Next</span>
          </a>
        </li>
      </ul>

    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; NSUer Shring 2018</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>
