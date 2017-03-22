<?php

	$id = intval($_REQUEST['id']);
	$name = $_REQUEST['name'];
	$author = $_REQUEST['author'];
	$price = $_REQUEST['price'];
	$stock = $_REQUEST['stock'];

	include 'conn.php';
	$sql = "update book set name='$name',author='$author',
		price='$price',stock='$stock' where id=$id";
	$result = @mysql_query($sql);
	if ($result){
		echo json_encode(array('success'=>true));
	} else {
		echo json_encode(array('msg'=>'Some errors occured.'));
	}
?>
