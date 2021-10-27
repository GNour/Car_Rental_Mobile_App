<?php
include('config.php');

$desc = addslashes(strip_tags($_POST['desc']));
$RPD = addslashes(strip_tags($_POST['RPD']));
$cn = addslashes(strip_tags($_POST['cn']));
$key = addslashes(strip_tags($_POST['key']));

$sql = "UPDATE cars SET description = '$desc', RPD = '$RPD' WHERE carnumber = '$cn'";
if($key != "GFHedit")
    die("Access Denied");

mysqli_query($db,$sql) or
    die ("can't edit record");

echo "Edited!";
   
mysqli_close($db);
?>