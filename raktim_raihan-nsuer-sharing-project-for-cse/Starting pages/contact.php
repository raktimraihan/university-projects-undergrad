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
      <h1 class="mt-4 mb-3">Contact
        <small>Us</small>
      </h1>

      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="index.html">Home</a>
        </li>
        <li class="breadcrumb-item active">Contact</li>
      </ol>

      <!-- Content Row -->
      <div class="row">
        <!-- Map Column -->
        <div class="col-lg-8 mb-4">
            <div style="width: 100%"><iframe width="100%" height="400" src="https://maps.google.com/maps?width=100%&amp;height=400&amp;hl=en&amp;q=NSU%2C%20Dhaka+(NSUer%20Shring)&amp;ie=UTF8&amp;t=&amp;z=16&amp;iwloc=A&amp;output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"><a href="https://www.maps.ie/create-google-map/">Create Google Map</a></iframe></div><br />

        </div>
        <!-- Contact Details Column -->
        <div class="col-lg-4 mb-4">
          <h3>Contact Details</h3>
          <p>
            North South University
            <br>Block-A, Basundhara, Dhaka-1208.
            <br>
          </p>
          <p>
            <abbr title="In case of instant help.">Phone</abbr>: (123) 456-7890
          </p>
          <p>
            <abbr title="Contact Email to reach us.">EMail</abbr>:
            <a href="mailto:nsharing@gmail.com"> nsharing@gmail.com </a>
          </p>
          <p>
            <abbr title="Hours">Office Hours</abbr>: Saturday - Thursday: 9:00 AM to 5:00 PM
          </p>
        </div>
      </div>
      <!-- /.row -->
      <hr>
      <!-- Contact Form -->
      <!-- In order to set the email address and subject line for the contact form go to the bin/contact_me.php file. -->

      <form action= "../../Project cse327/php/admin_message_post.php" method="Post">
      <div class="row">
        <div class="col-lg-8 mb-4">
          <h3 style="color: #008080;">Send us a Message</h3> <hr>
          <form name="sentMessage" id="contactForm" novalidate >

            <div class="control-group form-group">
              <div class="controls">
                <label>Phone Number:</label>
                <input type="tel" class="form-control" id="phone" name="phone" required data-validation-required-message="Please enter your phone number to reach you quickly.">
              </div>
            </div>

            <div class="control-group form-group">
              <div class="controls">
                <label>Message:</label>
                <textarea rows="10" cols="100" class="form-control" id="message" name="message" required data-validation-required-message="Please enter your message" maxlength="1500" style="resize:none"></textarea>
              </div>
            </div>
            <div id="success"></div>
            <!-- For success/fail messages -->
            <button type="submit" class="btn btn-primary" id="sendMessageButton" >Send Message</button>
          </form>
        </div>
      </div>
    </form>
      <!-- /.row -->

    </div>
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

    <!-- Contact form JavaScript -->
    <!-- Do not edit these files! In order to set the email address and subject line for the contact form go to the bin/contact_me.php file. -->
    <script src="js/jqBootstrapValidation.js"></script>
    <script src="js/contact_me.js"></script>

  </body>

</html>
