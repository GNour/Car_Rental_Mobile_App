<?php
include('config.php');
$user = $_GET['user'];
$sql = "SELECT role FROM user WHERE email = '$user'";
$query = mysqli_query($db,$sql);
while($row = mysqli_fetch_row($query))
    $role = $row[0];
echo $role;
?>