<?php
function conectar(){
	$servidor = "127.0.0.1";
	$usuario = "root";
	$password = "minino";
	$database = "usuarios";
	$conexion = new mysqli($servidor, $usuario, $password, $database);
	return $conexion;
}
function operar($nombre, $apellido){
	$conexion = conectar();
	if(($nombre<>"")&&($apellido<>"")){
		mysqli_query($conexion, "insert into datos(nombre, apellido) values('".$nombre."','".$apellido."')");
	}
	$query = $conexion->query("select * from datos;");
	while($celda = $query->fetch_assoc()) {
		if($i==0){echo $celda["id"]."-".$celda["nombre"]."-".$celda["apellido"]; $i = 1;}
		else{echo "\n".$celda["id"]."-".$celda["nombre"]."-".$celda["apellido"];}
    	}
}
if(isset($_REQUEST["nombre"])&&isset($_REQUEST["apellido"])){
	$nombre = $_REQUEST["nombre"];
	$apellido = $_REQUEST["apellido"];
	operar($nombre, $apellido);
}
else{
	echo "Solo Personal Autorizado";
}
/*function conectar(){
	$servidor = "127.0.0.1";
	$usuario = "root";
	$password = "minino";
	$database = "usuarios";
	$conexion = mysql_connect($servidor, $usuario, $password);
	mysql_select_db($database, $conexion);
	return $conexion;
}
function operar($nombre, $apellido){
	$conexion = conectar();
	if(($nombre<>"")&&($apellido<>"")){
		mysql_query("insert into datos(nombre, apellido) values('".$nombre."','".$apellido."')", $conexion);
	}
	$query = mysql_query("select * from datos;", $conexion);
	$i = 0;
	while($celda=mysql_fetch_array($query)){
		if($i==0){echo $celda["id"]."-".$celda["nombre"]."-".$celda["apellido"]; $i = 1;}
		else{echo "\n".$celda["id"]."-".$celda["nombre"]."-".$celda["apellido"];}
	}

}
if(isset($_REQUEST["nombre"])&&isset($_REQUEST["apellido"])){
	$nombre = $_REQUEST["nombre"];
	$apellido = $_REQUEST["apellido"];
	operar($nombre, $apellido);
}
else{
	echo "Solo Personal Autorizado";
}*/
?>
