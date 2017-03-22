<?php

	$book_id = $_REQUEST['book_id'];
	$user_id = $_REQUEST['user_id'];
	$time = $_REQUEST['time'];


	include 'conn.php';

	$sql = "insert into orders(book_id,user_id,time) values('$book_id','$user_id','$time')";
	$result = @mysql_query($sql);

	if ($result){
		echo json_encode(array('success'=>true));
	} else {
		echo json_encode(array('msg'=>'Some errors occured.'));
	}
?>
