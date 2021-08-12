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
      <h1 class="mt-4 mb-3">About
        <small>NSUer Sharing</small>
      </h1>

      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="index.html">Home</a>
        </li>
        <li class="breadcrumb-item active">About</li>
      </ol>

      <!-- Intro Content -->
      <div class="row">
        <div class="col-lg-6">
          <img class="img-fluid rounded mb-4" src="img/vision.jpg" alt="" style="width: 750px; height: 450px;">
        </div>
        <div class="col-lg-6">
          <h2>About Modern Business</h2>
          <p>This project is a part of the course evaluation CSE-327 (Software engineering). In this projects we try to apply diiferent method and design structures in implementation of our final project. </p>
          <p> We tried out best to meet those <strong>goals:</strong> </p>
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
      </div>
      <!-- /.row -->

      <!-- Team Members -->
      <h2>Our Team</h2>

      <div class="row">
        <div class="col-lg-4 mb-4">
          <div class="card h-100 text-center">
            <img class="card-img-top" src="http://placehold.it/750x450" alt="">
            <div class="card-body">
              <h4 class="card-title">Raktim Raihan Prova</h4>
              <h6 class="card-subtitle mb-2 text-muted">Programmer. </h6>
              <p class="card-text"></p>
            </div>
            <div class="card-footer">
              <a href="mailto:raktim.prova@northsouth.edu">raktim.prova@northsouth.edu
              </a>
            </div>
          </div>
        </div>
        <div class="col-lg-4 mb-4">
          <div class="card h-100 text-center">
            <img class="card-img-top" src="http://placehold.it/750x450" alt="">
            <div class="card-body">
              <h4 class="card-title">Shazzad Hasan.</h4>
              <h6 class="card-subtitle mb-2 text-muted"></h6>
              <p class="card-text"></p>
            </div>
            <div class="card-footer">
              <a href="#"></a>
            </div>
          </div>
        </div>
        <div class="col-lg-4 mb-4">
          <div class="card h-100 text-center">
            <img class="card-img-top" src="http://placehold.it/750x450" alt="">
            <div class="card-body">
              <h4 class="card-title">Ahsan Kabir</h4>
              <h6 class="card-subtitle mb-2 text-muted"></h6>
              <p class="card-text"></p>
            </div>
            <div class="card-footer">
              <a href="#"></a>
            </div>
          </div>
        </div>


      </div>

      <div class="row">
        <div class="col-lg-4 mb-4">
          <div class="card h-100 text-center">
            <img class="card-img-top" src="http://placehold.it/750x450" alt="">
            <div class="card-body">
              <h4 class="card-title">Mehedi</h4>
              <h6 class="card-subtitle mb-2 text-muted"></h6>
              <p class="card-text"></p>
            </div>
            <div class="card-footer">
              <a href="#"></a>
            </div>
          </div>

      </div>
    </div>


      <!-- /.row -->

      <!-- Our Customers -->

      <h2 style="padding-bottom: 4px;">Future institutions that will be added later on. </h2>
      <div class="row">
        <div class="col-lg-2 col-sm-4 mb-4">
          <img class="img-fluid" src="img/AIUB_logo.png" alt="" style="width: 100px; height: 200;">
        </div>
        <div class="col-lg-2 col-sm-4 mb-4">
          <img class="img-fluid" src="img/bracu.png" alt="" style="width: 100px; height: 200;">
        </div>
        <div class="col-lg-2 col-sm-4 mb-4">
          <img class="img-fluid" src="img/download.png" alt="" style="width: 100px; height: 200;">
        </div>
        <div class="col-lg-2 col-sm-4 mb-4">
          <img class="img-fluid" src="img/iub.png" alt="" style="width: 100px; height: 200;">
        </div>
        <div class="col-lg-2 col-sm-4 mb-4">
          <img class="img-fluid" src="img/ewu.png" alt="" style="width: 100px; height: 200;">
        </div>

      </div>

      <!-- /.row -->

    </div>
    <!-- /.container -->
  </div>
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
