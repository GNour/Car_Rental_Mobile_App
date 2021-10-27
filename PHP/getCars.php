<?php
include('config.php');
$query = mysqli_query($db, "Select * from cars");
$result = array();
while($row = mysqli_fetch_array($query)){
    $result[] = $row;
}
echo(json_encode($result));
mysqli_free_result($query);
mysqli_close($db);
?>