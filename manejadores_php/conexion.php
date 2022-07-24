<?php

 function conectar(){

     $servername = "localhost";
     $username = "conarperu_pruebas_post";
     $password = "3Afm4Dv3MmK2";
     $dbname = "conarperu_bbdd_pruebas";
  
     // Create connection
     $conn = new mysqli($servername, $username, $password, $dbname);
     // Check connection
     if ($conn->connect_error) {
         die("Connection failed: " . $conn->connect_error);
     }else{
      //   echo '<h1>conecxion valida';
         return $conn;
     }
 }

?>