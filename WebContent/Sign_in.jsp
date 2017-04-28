<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆界面</title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <style>
        body{
            background:url("images/sign_in_bg.jpg") ;
            background-size: cover;
            background-repeat: no-repeat;
            backgroud-position:center;
            z-index: -1;
        }
        #forget{
            margin-left: 100px;}
        #mysign{
            margin-top:170px;
            float:right;
            margin-right: 280px;
            max-width: 300px;

            padding:40px 30px 45px 30px;
            background:transparent;
            background-color:rgba(255,255,255,0.8);
            border-radius: 5px;
        }

        .mybutton{
            width:240px;
            margin-top: 10px;
            padding-top: 10px;
        }
        .mycheck{
            padding-top: 10px;
        }
        
        #toolTip{
        	display:none;
        	color:red;
        	margin-top:5px;
        	margin-buttom:5px;
        }

    </style>
</head>
<body>
<form role="form " action="Sign_in_servlet" method="post" onsubmit="return signInCheck()">
    <div class="container" id="mysign">
    	<div id="toolTip"></div>
    	<!-- use to give log in tip -->
        <div class="form-group " >
            <label >登录名：</label>
            <input name="user_name" id="user_name" type="text" class="form-control" placeholder="用户名 /邮箱 " >
        </div>

        <div class="from-group " >
            <label>密码：</label>
             <input name="password" id="password" type="password" class="form-control" placeholder="请输入密码">
        </div>
        <div class="checkbox mycheck">
            <label>
                <input type="checkbox" name="remember_me" value="1">记住密码
            </label>
            <a href="#" id="forget" >忘记密码</a>
        </div>
        <div>
            <button class="btn btn-success mybutton" type="submit" >登陆</button>
        </div>
        <div>
            <a class="btn btn-default mybutton" href="sign_up.jsp">注册</a>
        </div>


    </div>
</form>
</body>

<script language="JavaScript" type="text/javascript">
    function signInRequest(url) {
        http_request = false;
        if (window.XMLHttpRequest) {    							   // 非IE浏览器
            http_request = new XMLHttpRequest();	                      //创建XMLHttpRequest对象
        } else if (window.ActiveXObject) {     						   // IE浏览器
            try {
                http_request = new ActiveXObject("Msxml2.XMLHTTP");	   //创建XMLHttpRequest对象
            } catch (e) {
                try {
                    http_request = new ActiveXObject("Microsoft.XMLHTTP");  //创建XMLHttpRequest对象
                } catch (e) {}
            }
        }
        if (!http_request) {
            alert("不能创建XMLHttpRequest对象实例！");
            return false;
        }
        http_request.onreadystatechange = getResult;					   //调用返回结果处理函数
        http_request.open('GET', url, true);							   //创建与服务器的连接
        http_request.send(null);									   //向服务器发送请求
    }

    function getResult(){
        if(http_request.readyState == 4){
            if(http_request.status == 200){
                var tip = http_request.responseText;
                showTip(tip);

            }else{
                alert("request page wrong")
            }
        }
    }

    function showTip(tip){

        document.getElementById("toolTip").innerHTML = tip;
        document.getElementById("toolTip").style.display="block";
    }

    function signInCheck(){

        var user = document.getElementById("user_name").value;
        var password = document.getElementById("password").value;
        var tip;

        if(user == ""){
            tip = "请输入用户名";
            showTip(tip);
            document.getElementById("user_name").focus();
            return false;
        }
        else if(password ==""){
            tip = "请输入密码";
            showTip(tip);
            document.getElementById("password").focus();
            return false;
        }
        //else{
            //signInRequest('Sign_in_servlet?user_name=' + user+'password='+password);
        //}
        
        return;
    }
</script>
</html>