<?php


 include 'databaseConfig.php' ;
 
 $con = mysqli_connect($HostName, $HostUser, $HostPass, $DatabaseName);
 
 $name = isset($_POST['name']) ? $_POST['name'] : '';
 $phone = isset($_POST['phone']) ? $_POST['phone'] : '';
 $email = isset($_POST['email']) ? $_POST['email'] : '';
 $password = isset($_POST['password']) ? $_POST['password'] : '';
 $place = isset($_POST['place']) ? $_POST['place'] : '';
 $sports = isset($_POST['sports']) ? $_POST['sports'] : '';
 $experience = isset($_POST['experience']) ? $_POST['experience'] : '';

 $Sql_Query = "insert into playertest (name,phone,email,password,place,sports,experience) 
 values ('$name','$phone','$email','$password','$place','$sports','$experience')";

 echo $Sql_Query;
 
 if(mysqli_query($con,$Sql_Query)){
 
 echo 'Data Submit Successfully';
 
 }
 else{
 echo  mysqli_error($con);
 echo 'Try Again';
 
 }
 mysqli_close($con);
 
?>