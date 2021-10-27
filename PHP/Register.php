<?php
include('config.php');

$fname = addslashes(strip_tags($_POST['fname']));
$lname = addslashes(strip_tags($_POST['lname']));
$email = addslashes(strip_tags($_POST['email']));
$password = addslashes(strip_tags($_POST['pwd']));
$phonenumber = addslashes(strip_tags($_POST['pn']));
$key = addslashes(strip_tags($_POST['key']));

if ($key != "GFHregister")
    die("access denied");

$sql = "INSERT into user values ('$fname', '$lname','$email','$password',0,0,'$phonenumber')";
mysqli_query($db,$sql) or
    die ("can't add record");

echo "Registered! Please login";
   
mysqli_close($db);
?>