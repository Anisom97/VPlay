<?php

include 'databaseConfig.php' ;
 
 $con = mysqli_connect($HostName, $HostUser, $HostPass, $DatabaseName);
 
 $teamname = isset($_POST['teamname']) ? $_POST['teamname'] : '';
 $members = isset($_POST['members']) ? $_POST['members'] : '';
 $captainname = isset($_POST['captainname']) ? $_POST['captainname'] : '';
 $email = isset($_POST['email']) ? $_POST['email'] : '';
 $phone = isset($_POST['phone']) ? $_POST['phone'] : '';
 $place = isset($_POST['place']) ? $_POST['place'] : '';
 $sports = isset($_POST['sports']) ? $_POST['sports'] : '';
 $password = isset($_POST['password']) ? $_POST['password'] : '';

 $Sql_Query = "INSERT into teamtest (teamname,members,captainname,email,phone,place,sports,password) VALUES ('$teamname','$members','$captainname','$email','$phone','$place','$sports','$password')";
 
 if(mysqli_query($con,$Sql_Query)){
 
 echo 'Data Submit Successfully';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($con);
 
?>