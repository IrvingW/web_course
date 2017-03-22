<?php

	$id = intval($_REQUEST['id']);
	$user_name = $_REQUEST['user_name'];
	$password = $_REQUEST['password'];
	$phone = $_REQUEST['phone'];
	$email = $_REQUEST['email'];
	$address = $_REQUEST['address'];

	include 'conn.php';
	$sql = "update users set user_name='$user_name',password='$password',
		phone='$phone',email='$email',address='$address' where id=$id";
	$result = @mysql_query($sql);
	if ($result){
		echo json_encode(array('success'=>true));
	} else {
		echo json_encode(array('msg'=>'Some errors occured.'));
	}
?>
