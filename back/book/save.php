<?php
    $id = intval($_REQUEST['id']);
	$name = $_REQUEST['name'];
	$author = $_REQUEST['author'];
	$price = $_REQUEST['price'];
	$stock = $_REQUEST['stock'];

	include 'conn.php';

	$sql = "insert into book(id,name,author,price,stock) values('$id','$name','$author','$price','$stock')";
	$result = @mysql_query($sql);
	if ($result){
		echo json_encode(array('success'=>true));
	} else {
		echo json_encode(array('msg'=>'Some errors occured.'));
	}
?>
