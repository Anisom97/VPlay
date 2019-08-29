<?php

include 'databaseConfig.php' ;
 
 $con = mysqli_connect($HostName, $HostUser, $HostPass, $DatabaseName);
 
 $name = isset($_POST['name']) ? $_POST['name'] : '';
 $email = isset($_POST['email']) ? $_POST['email'] : '';
 $phone = isset($_POST['phone']) ? $_POST['phone'] : '';
 $location = isset($_POST['location']) ? $_POST['location'] : '';
 $landmark = isset($_POST['landmark']) ? $_POST['landmark'] : '';
 $details = isset($_POST['details']) ? $_POST['details'] : '';
 $password = isset($_POST['password']) ? $_POST['password'] : '';

 $Sql_Query = "insert into admintest (name,email,phone,location,landmark,details,password) values ('$name','$email','$phone','$location','$landmark','$details','$password')";
 
 if(mysqli_query($con,$Sql_Query)){
 
 echo 'Data Submit Successfully';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($con);
 
?>