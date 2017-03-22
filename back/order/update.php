<?php

	$book_id = $_REQUEST['book_id'];
	$user_id = $_REQUEST['user_id'];
	$time = $_REQUEST['time'];

	include 'conn.php';
	$sql = "update orders set user_id='$user_id',book_id='$book_id',
		time='$time' where id=$id";
	$result = @mysql_query($sql);
	if ($result){
		echo json_encode(array('success'=>true));
	} else {
		echo json_encode(array('msg'=>'Some errors occured.'));
	}
?>
