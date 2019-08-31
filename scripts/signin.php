<?php

include 'databaseConfig.php' ;
 
 $con = mysqli_connect($HostName, $HostUser, $HostPass, $DatabaseName);
 
 $table_name = isset($_POST['table']) ? $_POST['table'] : '';
 $user_name = isset($_POST['user_name']) ? $_POST['user_name'] : '';
 $user_pass = isset($_POST['password']) ? $_POST['password'] : '';
 
 $Sql_Query = "select * from ".$table_name." where email like '".$user_name."' and password like '".$user_pass."';";
 //echo $Sql_Query;
 $result = mysqli_query($con, $Sql_Query);
 
 if(mysqli_num_rows($result) > 0){
 
 echo '1';
 
 }
 else{
 
 echo '0';
 
 }
 mysqli_close($con);
 
?>