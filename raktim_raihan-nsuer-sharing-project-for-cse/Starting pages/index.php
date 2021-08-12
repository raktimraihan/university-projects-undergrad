
<?php
session_start();
require('../../Project cse327/php/maxlistforbook.php');
require('../../Project cse327/php/maxlistforcalculator.php');
require('../../Project cse327/php/maxlistfornote.php');
require('../../Project cse327/php/maxlistforother.php');
require('../../Project cse327/php/maxlistforpendrive.php');
require('../../Project cse327/php/lastaddedindexforallitem.php');
//session_start();
?>
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
                <a class="dropdown-item" href="adproduct.php?page_visit=1">Post an Ad</a>
                <a class="dropdown-item" href="portfolio-1-col.php?page_visit=1">Items Posted for Sharing</a>
                <a class="dropdown-item" href="faq.php?page_visit=1">FAQ</a>
                <a class="dropdown-item" href="#"> Settings</a>
                <a class="dropdown-item" href="../../Project cse327/php/logout.php">Log out</a>

              </div>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <header>
      <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
          <!-- Slide One - Set the background image for this slide in the line below -->
          <div class="carousel-item active slide_head" style="background-image: url('img/library.jpg'); background-position: -1px -1px ; ">
            <div class="carousel-caption d-none d-md-block slide_head_2">
              <h3 style="font-family: 'Lato', sans-serif; text-shadow: 0px 1px 1px black;">Share Your Books</h3>
              <p style="font-family: 'Lato', sans-serif; text-shadow: 0px 1px 1px black;">Donate your books at the end of the semester to help fellow NSU-er.</p>
            </div>
          </div>
          <!-- Slide Two - Set the background image for this slide in the line below -->
          <div class="carousel-item slide_head" style="background-image: url('img/need.jpg'); background-position: -1px -130px ; ">
            <div class="carousel-caption d-none d-md-block slide_head_2">
              <h3 style="font-family: 'Lato', sans-serif; text-shadow: 0px 1px 1px black;">Meet Urgency.</h3>
              <p style="font-family: 'Lato', sans-serif; text-shadow: 0px 1px 1px black;">Share your Calculator, Pendrive, Notes and other belongings instantly and easily.</p>
            </div>
          </div>
          <!-- Slide Three - Set the background image for this slide in the line below -->
          <div class="carousel-item slide_head" style="background-image: url('img/idea.jpg'); background-position: -1px -260px ; ">
            <div class="carousel-caption d-none d-md-block slide_head_2">
              <h3 style="font-family: 'Lato', sans-serif; text-shadow: 0px 1px 1px black;">Maintain Personal Blog</h3>
              <p style="font-family: 'Lato', sans-serif; text-shadow: 0px 1px 1px black;">Share what you think about the topics of your interest effectively.</p>
            </div>
          </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>
    </header>

    <!-- Page Content -->
    <div class="container">

      <h1 class="my-4 text-center" style="font-family: 'Indie Flower', cursive; font-size: 30px;">Welcome to the free market place of NSUers' and their Ideas.</h1>

      <!-- Marketing Icons Section -->
      <div class="row">
        <div class="col-lg-4 mb-4">
          <div class="card h-100">
            <h4 class="card-header" style="font-family: 'Poiret One', cursive;"> Books Sharing. </h4>
            <div class="card-body">
              <p class="card-text" style="font-family: 'Roboto Condensed', sans-serif;">Browse the available books of different categories and request the the owner to own a copy. <br> A message will be sent to you whenever the owner accepts your request to own a book.</p>
            </div>
            <div class="card-footer">
              <a href="portfolio-4-col.php?page_visit=1" class="btn btn-outline-primary">Browse NOW!</a>
            </div>
          </div>
        </div>
        <div class="col-lg-4 mb-4">
          <div class="card h-100">
            <h4 class="card-header" style="font-family: 'Poiret One', cursive;">Classroom Accessories</h4>
            <div class="card-body">
              <p class="card-text" style="font-family: 'Roboto Condensed', sans-serif;">Share your classroom accessories such as pendrive, pen. pencil, Box, Calculator and other things in a click of button. <br> If you forget any of those things,  don't worry someone is here to share it with you. Just browse and place a request.</p>
            </div>
            <div class="card-footer">
              <a href="portfolio-4-col1.php?page_visit=1" class="btn btn-outline-primary">Browse More.</a>
            </div>
          </div>
        </div>
        <div class="col-lg-4 mb-4">
          <div class="card h-100">
            <h4 class="card-header" style="font-family: 'Poiret One', cursive;">Blog. </h4>
            <div class="card-body">
              <p class="card-text" style="font-family: 'Roboto Condensed', sans-serif;">Share your ideas on particular topics of your interest. Just type and post it into the blogging section to receive feedback, and organixe your ideas clealy.</p>
            </div>
            <div class="card-footer">
              <a href="blog-home-2.php" class="btn btn-outline-primary">Visit Blog.</a>
            </div>
          </div>
        </div>
      </div>
      <!-- /.row -->

      <!-- Item Section -->
      <h2 class="card-header" style="font-family: 'Poiret One', cursive; margin-bottom: 7px; border-radius: 10px;">Items are waiting For you to grab. </h2>

      <div class="row">
        <div class="col-lg-4 col-sm-6 portfolio-item">
          <div class="card h-100">
            <a href="#"><img class="card-img-top" src="<?php if($_SESSION["bg_img"]){ echo $_SESSION["bg_img"];} else {echo "http://placehold.it/500x400";} ?>" height= "500" width="700" alt=""></a>
            <div class="card-body">
              <h4 class="card-title">
                <a href="#"> <?php echo "".$_SESSION["bag_name"]; ?></a>
              </h4>
              <p class="card-text"><?php echo "".$_SESSION["bag_des"]; ?></p>
              <p class="card-text"><?php echo "Posted by: ".$_SESSION["bag_user_name"]; ?></p>
              <p class="card-text align-text-bottom"><?php $date= explode(" ",$_SESSION["bg_post"]); echo "Posted on: ". $date[0]."<br>"; echo "Posting time: ".date('H:i:s',strtotime($date[1])); ?> </p><br>
            </div>
          </div>
        </div>

        <div class="col-lg-4 col-sm-6 portfolio-item">
          <div class="card h-100">
            <a href="#"><img class="card-img-top" src="<?php if($_SESSION["book_img"]){ echo $_SESSION["book_img"];} else {echo "http://placehold.it/500x400";} ?>" height= "500" width="700" alt=""></a>
            <div class="card-body">
              <h4 class="card-title">
                <a href="#"><?php echo $_SESSION["bname"];?></a>
              </h4>
              <p class="card-text"><?php echo $_SESSION["book_des"]; ?></p>
              <p class="card-text"><?php echo "Posted by: ".$_SESSION["book_user_name"];?></p>
              <p class="card-text align-text-bottom"><?php $date= explode(" ",$_SESSION["book_post"]); echo "Posted on: ". $date[0]."<br>"; echo "Posting time: ".date('H:i:s',strtotime($date[1])); ?> </p><br>
            </div>
          </div>
        </div>


        <div class="col-lg-4 col-sm-6 portfolio-item">
          <div class="card h-100">
            <a href="#"><img class="card-img-top" src="<?php if($_SESSION["cook_img"]){ echo $_SESSION["cook_img"];} else {echo "http://placehold.it/500x400";} ?>"height= "500" width="700" alt=""></a>
            <div class="card-body">
              <h4 class="card-title">
                <a href="#"><?php echo $_SESSION["cname"];?></a>
              </h4>
              <p class="card-text"> <?php echo $_SESSION["cook_des"]; ?></p>
              <p class="card-text"><?php echo "Posted by: ".$_SESSION["cook_user_name"];?></p>
              <p class="card-text align-text-bottom"><?php $date= explode(" ",$_SESSION["cook_post"]); echo "Posted on: ". $date[0]."<br>"; echo "Posting time: ".date('H:i:s',strtotime($date[1])); ?> </p><br>
            </div>
          </div>
        </div>


        <div class="col-lg-4 col-sm-6 portfolio-item">
          <div class="card h-100">
            <a href="#"><img class="card-img-top" src="<?php if($_SESSION["nook_img"]){ echo $_SESSION["cook_img"];} else {echo "http://placehold.it/500x400";} ?>"height= "500" width="700" alt=""></a>
            <div class="card-body">
              <h4 class="card-title">
                <a href="#"><?php echo $_SESSION["nname"];?></a>
              </h4>
              <p class="card-text"> <?php echo $_SESSION["nook_des"]; ?></p>
              <p class="card-text"><?php echo "Posted by: ".$_SESSION["nook_user_name"];?></p>
              <p class="card-text align-text-bottom"><?php $date= explode(" ",$_SESSION["nook_post"]); echo "Posted on: ". $date[0]."<br>"; echo "Posting time: ".date('H:i:s',strtotime($date[1])); ?> </p><br>
            </div>
          </div>
        </div>


        <div class="col-lg-4 col-sm-6 portfolio-item">
          <div class="card h-100">
            <a href="#"><img class="card-img-top" src="<?php if($_SESSION["pook_img"]){ echo $_SESSION["pook_img"];} else {echo "http://placehold.it/500x400";} ?>"height= "500" width="700" alt=""></a>
            <div class="card-body">
              <h4 class="card-title">
                <a href="#"><?php echo $_SESSION["pname"];?></a>
              </h4>
              <p class="card-text"> <?php echo $_SESSION["pook_des"]; ?></p>
              <p class="card-text"><?php echo "Posted by: ".$_SESSION["pook_user_name"];?></p>
              <p class="card-text align-text-bottom"><?php $date= explode(" ",$_SESSION["pook_post"]); echo "Posted on: ". $date[0]."<br>"; echo "Posting time: ".date('H:i:s',strtotime($date[1])); ?> </p><br>
            </div>
          </div>
        </div>


        <div class="col-lg-4 col-sm-6 portfolio-item">
          <div class="card h-100">
            <a href="#"><img class="card-img-top" src="<?php if($_SESSION["ook_img"]){ echo $_SESSION["ook_img"];} else {echo "http://placehold.it/500x400";} ?>"height= "500" width="700" alt=""></a>
            <div class="card-body">
              <h4 class="card-title">
                <a href="#"><?php echo $_SESSION["oname"];?></a>
              </h4>
              <p class="card-text"> <?php echo $_SESSION["ook_des"]; ?></p>
              <p class="card-text"><?php echo "Posted by: ".$_SESSION["ook_user_name"];?></p>
              <p class="card-text align-text-bottom"><?php $date= explode(" ",$_SESSION["ook_post"]); echo "Posted on: ". $date[0]."<br>"; echo "Posting time: ".date('H:i:s',strtotime($date[1])); ?> </p><br>
            </div>
          </div>
        </div>
      </div>
      <!-- /.row -->

      <!-- Features Section -->
      <div class="row">
        <div class="col-lg-6">
          <h2 style="font-family: 'Oswald', sans-serif;">What is NSUer-s' Sharing.</h2>
          <p style="font-family: 'Oswald', sans-serif;">This website includes the services of:</p>
          <ul>
            <li>Sharing Books at the end of the semester.</li>
            <li>Sharing Classroom accessories instantly to meet the urgent need.</li>
            <li>Share individual idea of academic as well as personal interest to enhance their skills.</li>
            <li>A common palce for all to blogging.</li>
          </ul>
          <p style="font-family: 'Oswald', sans-serif;">The main goal is to present a virtual place for all of us including students, faculty and stuffs for Sharings their belongings. <br> In addition,
          the goal is to present a place for all to share their individual idea to exchange ideas about trending topics such that within this community the exchange of knowledge along with academic interests
        meet the quest for learning new topic.</p>
        </div>
        <div class="col-lg-6">
          <img class="img-fluid rounded" src="img/vision.jpg" alt="" style="height: 450px; width: 700px;">
        </div>
      </div>
      <!-- /.row -->

      <hr>

      <!-- Call to Action Section -->
      <div class="row mb-4">
        <div class="col-md-8">
          <p>Want to give us your valuable suggestion or facing any problem? Give us a mail, We will reach you soon.</p>
        </div>
        <div class="col-md-4">
          <a class="btn btn-lg btn-secondary btn-block" href="contact.php">Contact Us.</a>
        </div>
      </div>
      <hr>
    </div

    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; NSUer Sharing 2018</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>
