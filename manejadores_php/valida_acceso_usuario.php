<?php

   require_once('conexion.php');
   
   $conn = conectar();//realizo la conexxion bbdd
  
   if ($conn->connect_error) {// valido la conecxion
        die("Connection failed: " . $conn->connect_error);
		echo 'ERROR AL INTENTAR CONECTAR A LA BBDD ';
   }else{
	   //en casod e conexion exitosa
       $usuario = $_GET["usuario"];
       $clave = $_GET["clave"];
      // echo  $usuario.' -- '.$clave;
	   $sql = "SELECT * from psu00 where usuario = '$usuario' and clave = '$clave'"; 
       
       $result = $conn->query($sql);
	   
       if ($result->num_rows > 0) {
		  while( $row = $result->fetch_assoc()){
            $array = $row; 
		  }	  
		  echo json_encode($array);	 
		}else{
          $array['id']=0; 
		  echo json_encode($array);	          
	    }
	
       $conn->close();	   
	   
   }
 
?>