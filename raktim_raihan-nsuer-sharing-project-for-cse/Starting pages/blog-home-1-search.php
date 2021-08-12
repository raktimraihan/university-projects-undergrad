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
                <a class="dropdown-item" href="blog-home-2.php">Blog Posts</a>
                <a class="dropdown-item" href="adproduct.php">Post an Ad</a>
                <a class="dropdown-item" href="portfolio-1-col.php">Items Posted for Sharing</a>
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
      <h1 class="mt-4 mb-3">Ideas
        <small>and Random thoughts of NSUers</small>
      </h1>

      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="index.html">Home</a>
        </li>
        <li class="breadcrumb-item active">Blog</li>
      </ol>

      <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

          <!-- Blog Post -->
<?php
                        include("../../Project cse327/php/database_connect.php");
                        $page= $_GET["page_visit"];
                        $search=  $_GET["search"];
                        if($page=="" || $page=="1"){
                          $page_passed= 0;

                        }
                        else{
                          $page_passed= ($page*8)-8;
                        }
                        $sql = "SELECT * FROM `blog`  where topic like '%$search%' order by `date` desc limit $page_passed,8  ";
                        $result= mysqli_query($conn,$sql);

          while($row= mysqli_fetch_row($result)){ ?>
          <div class="card mb-4">
            <img class="card-img-top" src="<?php if($row[2]){ echo $row[2];} else {echo "http://placehold.it/750x300";} ?>" height="300" width="750" alt="Card image cap">
            <div class="card-body">
              <h2 class="card-title" style="color: brown;"><?php echo $row[1]; ?></h2>
              <p class="card-text"><?php echo substr($row[3],0,250); ?></p>
              <a href="#" class="btn btn-primary">Read More &rarr;</a>
            </div>
            <div class="card-footer text-muted">
              <p class="card-text align-text-bottom"><?php $date= explode(" ",$row[4]); echo "Posted on: ". $date[0]."<br>"; echo "Posting time: ".date('H:i:s',strtotime($date[1])); ?> </p>
              <?php
              $sql2 = "select concat(first_name,' ',last_name) FROM user, blog where user.user_id='$row[0]'";
                          $result2= mysqli_query($conn, $sql2);
                          while($row2= mysqli_fetch_row($result2)){
                            echo "Posted by= ".$row2[0];
                          }
              ?>
            </div>
          </div>
          <?php }?>
          <?php
            $sql1 = "SELECT * FROM `blog` where topic like '%$search%'";
            $result1= mysqli_query($conn,$sql1);
            $cnt= mysqli_num_rows($result1);
            $page=ceil($cnt/8);
          ?>


          <!-- Pagination -->
          <ul class="pagination justify-content-center mb-4">
            <li class="page-item">
              <a class="page-link" href="blog-home-1.php?page_visit=<?php if($_GET["page_visit"]==1){echo 1;}else {echo $_GET["page_visit"]-1;} ?>">&larr; Older</a>
            </li>
            <?php
            if($page<11){
            for($b=1;$b<=$page;$b++){?>
            <li class="page-item">
              <a class="page-link" href="blog-home-1.php?page_visit=<?php echo $b; ?>"><?php echo $b; ?></a>
            </li>
          <?php }  }
          else{
            for($b=$_GET['page_visit'];$b<=$_GET['page_visit']+10;$b++){?>
              <li class="page-item">
                <a class="page-link" href="blog-home-1.php?page_visit=<?php echo $b; ?>"><?php echo $b; ?></a>
              </li>
            <?php   }
          }
    ?>
            <li class="page-item ">
              <a class="page-link" href="blog-home-1.php?page_visit=<?php if($_GET["page_visit"]==1){echo 1;}else {echo $_GET["page_visit"]-1;} ?>">Newer &rarr;</a>
            </li>
          </ul>

        </div>

        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">
<?php
      $sql3= "select topic from blog";
      $result3= mysqli_query($conn,$sql3);

?>
          <!-- Categories Widget -->
          <div class="card my-4">
            <h5 class="card-header">Categories</h5>
            <div class="card-body">
              <div class="row">
                <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
<?php                while($row3=mysqli_fetch_row($result3)){ ?>
                    <li>
                      <a href="blog-home-1-search.php?page_visit=1&search=<?php echo $row3[0];?>"><?php echo $row3[0]; $_SESSION["search_key"]= $row3[0]; ?></a>
                    </li>

<?php } ?>

                  </ul>
                </div>
              </div>
            </div>
          </div>

          <!-- Side Widget -->
          <div class="card my-4">
            <h5 class="card-header">Side Widget</h5>
            <div class="card-body">
              You can put anything you want inside of these side widgets. They are easy to use, and feature the new Bootstrap 4 card containers!
            </div>
          </div>

        </div>

      </div>
      <!-- /.row -->

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
