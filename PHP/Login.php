<?php
include('config.php');
$email = addslashes(strip_tags($_POST['email']));
$password = addslashes(strip_tags($_POST['password']));
$key = addslashes(strip_tags($_POST['key']));
if($key != "GFHlogin"){
    die("Access Denied");
}

$sql = "SELECT role FROM user WHERE email = '$email' and password = '$password'";

$result = mysqli_query($db,$sql);

while($row = mysqli_fetch_array($result))
    $role = $row[0];

$count = mysqli_num_rows($result);      
		
if($count == 1){
    echo "$role";
}
?>