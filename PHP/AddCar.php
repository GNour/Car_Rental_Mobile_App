<?php

include('config.php');


$carname = addslashes(strip_tags($_POST['carname']));
$carmodel = addslashes(strip_tags($_POST['carmodel']));
$year = addslashes(strip_tags($_POST['year']));
$carnumber = addslashes(strip_tags($_POST['carnumber']));
$cardesc = addslashes(strip_tags($_POST['cardesc']));
$RPD = addslashes(strip_tags($_POST['RPD']));
$imgid = addslashes(strip_tags($_POST['imgid']));



$select = "INSERT INTO cars VALUES('$carname','$carmodel','$year','$RPD','$carnumber','$imgid','$cardesc')";
$responce= mysqli_query($db,$select);

if($responce){
  echo"Car Added";
  mysqli_close($db);
}
else{
  echo "Failed";
}

?>