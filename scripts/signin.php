<?php

include 'databaseConfig.php' ;
 
 $con = mysqli_connect($HostName, $HostUser, $HostPass, $DatabaseName);
 
 $user_name = isset($_POST['user_name']) ? $_POST['user_name'] : '';
 $user_pass = isset($_POST['password']) ? $_POST['password'] : '';
 $table = isset($_POST['table']) ? isset($_POST['table']) : '';
 
 $Sql_Query = "select * from ".$table." where email like '$user_name' and password like '$user_pass';";
 
 if(mysqli_query($con,$Sql_Query)){
 
 echo 'Login successful';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($con);
 
?>