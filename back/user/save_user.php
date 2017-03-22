<?php

	$user_name = $_REQUEST['user_name'];
	$password = $_REQUEST['password'];
	$phone = $_REQUEST['phone'];
	$email = $_REQUEST['email'];
	$address = $_REQUEST['address'];

	include 'conn.php';

	$sql = "insert into users(user_name,password,phone,email,address) values('$user_name','$password','$phone','$email','$address')";
	$result = @mysql_query($sql);
	if ($result){
		echo json_encode(array('success'=>true));
	} else {
		echo json_encode(array('msg'=>'Some errors occured.'));
	}
?>
