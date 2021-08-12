<html>
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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div class="container">
<section class="panel panel-default">
<div class="panel-heading">
<h3 class="panel-title" style="color: brown;">Post an Ad of your belongings to Share. </h3>
</div>
<div class="panel-body">

<form action="../../Project cse327/php/pocessaditem.php" class="form-horizontal" role="form" method="post" enctype="multipart/form-data">

  <div class="form-group col-sm-9">
    <label for="exampleFormControlSelect1" name="category"class="col-sm-3 control-label">Select Category. </label>
      <select class=" col-sm-9 form-control" name="category" required id="exampleFormControlSelect1">
      <option value="book_information">Book.</option>
      <option value="bag">Bag.</option>
      <option value="note">Note.</option>
      <option value="pendrive">Pendrive.</option>
      <option value="calculator">Calculator.</option>
      <option value="others"> Other</option>
      </select>

  </div><!-- form-group // -->

  <div class="form-group">
    <label for="name" class="col-sm-3 control-label">Item Name: </label>
    <div class="col-sm-9">
      <input type="text" class="form-control" name="name" id="name" placeholder="Insert Name of your Item." required>
    </div>
  </div> <!-- form-group // -->

  <div class="form-group">
    <label for="about" class="col-sm-3 control-label" >Description</label>
    <div class="col-sm-9">
      <textarea class="form-control" placeholder="Place a short dexcription to make it more understandable." name="description"></textarea>
    </div>
  </div> <!-- form-group // -->

  <div class="form-group">
    <label for="name" class="col-sm-3 control-label" style="color: blue;">Upload image of your Item. </label>
    <div class="col-sm-3">
      <label class="control-label small" >Picture (jpg/png):</label>
      <input type="file" name="uploadedfile" id="uploadedfile">
    </div>
  </div> <!-- form-group // -->

  <hr>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-9">
      <button type="submit" name="submit" class="btn btn-primary">Submit</button>
    </div>
  </div> <!-- form-group // -->

</form>
<div class="col-sm-offset-3 col-sm-9">
  <a href="index.php"><button type="cancel" class="btn btn-cancel">Cancel</button></a>
</div>

</div><!-- panel-body // -->
</section><!-- panel// -->


</div> <!-- container// -->



<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>

</html>
