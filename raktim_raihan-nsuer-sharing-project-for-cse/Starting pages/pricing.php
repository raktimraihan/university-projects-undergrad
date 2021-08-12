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
                <a class="dropdown-item" href="portfolio-4-col.php">Books.</a>
                <a class="dropdown-item" href="portfolio-4-col0.php">Calculator.</a>
                <a class="dropdown-item" href="portfolio-4-col1.php">Pendrive.</a>
                <a class="dropdown-item" href="portfolio-4-col2.php">Bag.</a>
                <a class="dropdown-item" href="portfolio-4-col3.php">Notes.</a>
                <a class="dropdown-item" href="portfolio-4-col4.php">Others.</a>
              </div>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="blog-home-1.php"> Blog </a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle btn btn-outline-primary"style="border: 5px; color: white;" href="#" id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <?php echo "".$_SESSION["fname"]; ?>
              </a>
              <div class="dropdown-menu dropdown-menu-right aria-labelledby="navbarDropdownBlog">
                <a class="dropdown-item" href="blog-home-2.php">Blog Posts</a>
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
      <h1 class="mt-4 mb-3">Pricing
        <small>Subheading</small>
      </h1>

      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="index.html">Home</a>
        </li>
        <li class="breadcrumb-item active">Pricing</li>
      </ol>

      <!-- Content Row -->
      <div class="row">
        <div class="col-lg-4 mb-4">
          <div class="card h-100">
            <h3 class="card-header">Basic</h3>
            <div class="card-body">
              <div class="display-4">$19.99</div>
              <div class="font-italic">per month</div>
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">Cras justo odio</li>
              <li class="list-group-item">Dapibus ac facilisis in</li>
              <li class="list-group-item">Vestibulum at eros</li>
              <li class="list-group-item">
                <a href="#" class="btn btn-primary">Sign Up!</a>
              </li>
            </ul>
          </div>
        </div>
        <div class="col-lg-4 mb-4">
          <div class="card card-outline-primary h-100">
            <h3 class="card-header bg-primary text-white">Plus</h3>
            <div class="card-body">
              <div class="display-4">$39.99</div>
              <div class="font-italic">per month</div>
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">Cras justo odio</li>
              <li class="list-group-item">Dapibus ac facilisis in</li>
              <li class="list-group-item">Vestibulum at eros</li>
              <li class="list-group-item">
                <a href="#" class="btn btn-primary">Sign Up!</a>
              </li>
            </ul>
          </div>
        </div>
        <div class="col-lg-4 mb-4">
          <div class="card h-100">
            <h3 class="card-header">Ultra</h3>
            <div class="card-body">
              <div class="display-4">$159.99</div>
              <div class="font-italic">per month</div>
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">Cras justo odio</li>
              <li class="list-group-item">Dapibus ac facilisis in</li>
              <li class="list-group-item">Vestibulum at eros</li>
              <li class="list-group-item">
                <a href="#" class="btn btn-primary">Sign Up!</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <!-- /.row -->

    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2018</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>
