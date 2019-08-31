<?php

include 'databaseConfig.php' ;
 
$con = mysqli_connect($HostName, $HostUser, $HostPass, $DatabaseName);

$query = isset($_POST['query']) ? $_POST['query'] : '';
echo $query;
$result = mysqli_query($con, $query);

if (mysqli_num_rows($result) > 0) {
    // output data of each row
    while($row = mysqli_fetch_assoc($result)) {
        echo $row;
    }
} else {
    echo "0 results";
}

mysqli_close($con);
?>