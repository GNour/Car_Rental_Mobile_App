<?php
include('config.php');
$user = $_GET['user'];
$sql="SELECT rent.startdate,rent.enddate,cars.carname,user.firstname,user.lastname,cars.imageID,cars.RPD FROM cars,user,rent WHERE cars.carnumber=rent.car AND user.email=rent.user AND user.email = '$user'";
$query = mysqli_query($db, $sql) or die("Error!".mysqli_error($db));

if ($result = mysqli_query($db,$sql))
  {
   $emparray = array();
   while($row =mysqli_fetch_array($result)){
       $resultt = substr($row[6], 0, 2);
     $date1=date_create($row[0]);
$date2=date_create($row[1]);
$diff=date_diff($date1,$date2);
$days= $diff->format("%a"); 
$totalprice=$days * $resultt;
$row[] = $totalprice;
$emparray[] = $row;
   }
  echo(json_encode($emparray));
  mysqli_free_result($result);
  mysqli_close($db);
}

?>