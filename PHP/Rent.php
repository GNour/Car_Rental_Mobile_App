<?php
include('config.php');

$sd = addslashes(strip_tags($_POST['sd']));
$ed = addslashes(strip_tags($_POST['ed']));
$cn = addslashes(strip_tags($_POST['cn']));
$user = addslashes(strip_tags($_POST['user']));
$key = addslashes(strip_tags($_POST['key']));

if ($key != "GFHrent")
    die("access denied");

if (mysqli_connect_errno($db))
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

$sql = "INSERT into rent VALUES ('$sd', '$ed','$user','$cn',0)";
mysqli_query($db,$sql) or
    die (mysqli_error($db));

echo "Rentted! Check yout reservations!";
   
mysqli_close($db);
?>