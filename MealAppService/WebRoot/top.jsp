<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">


		<script type="text/javascript" src="script/jquery-1.4.4.js"></script>
		<link rel="stylesheet" href="style/PublicStyle.css" type="text/css"></link>
		<style type="text/css">
* {
	margin: 0;
	padding: 0;
	font-size: 12px;
	text-align: left;
}

.head {
	height: 120px;
	background: url("images/head_bg.jpg");
}

.head .top a {
	color: #FFF;
	text-decoration: underline;
}

.menu {
	padding: 0 0 0 32px;
	margin: 0;
	list-style: none;
	height: 35px;
	background: #fff url("images/button1.gif");
	position: relative;
	border-width: 0 1px;
}
.menu li a:hover {
color: #fff;
background: #000 url("images/button2.gif");
}
.menu li a:hover b {
	background: url("images/button2.gif") no-repeat right top;
}

.menu li a b {
	float: left;
	display: block;
	padding: 0 16px 0 8px;
}
.menu li a {
display: block;
float: left;
height: 35px;
line-height: 35px;
color: #fff;
text-decoration: none;
font-size: 11px;
font-family: arial, verdana, sans-serif;
font-weight: bold;
text-align: center;
padding: 0 0 0 8px;
cursor: pointer;
}
</style>
	</head>
	<body style="background: #F2F2F2; text-align: center;">
		<div style="width: 960px; margin: 0 auto">
			<div
				style="background: url(images/head_bg.png); height: 130px; width: 100%;">
			</div>
			<div class="menu">
				<ul>
					<li>
						<a
							href="/MealAppService/servlet/GridServlet?Action=getlist&currentpage=0"><b>菜谱管理</b>
						</a>
						<a
							href="/MealAppService/servlet/GridServlet?Action=getuserlist&currentpage=0"><b>用户管理</b>
						</a>
					    <a
							href="/MealAppService/servlet/GridServlet?Action=getorderlist&currentpage=0"><b>订单管理</b>
						</a>
						<a
							href="/MealAppService/servlet/GridServlet?Action=gettypelist&currentpage=0"><b>分组管理</b>
						</a>
						<a
							href="/MealAppService/servlet/GridServlet?Action=getchieflist&currentpage=0"><b>厨师管理</b>
						</a>
						<a
							href="/MealAppService/servlet/GridServlet?Action=getdeliverymanlist&currentpage=0"><b>配送员管理</b>
						</a>
						<a
							href="http://push.baidu.com/console/d75gLNsSLynSFb7QKePx7L2r?device_type=3#/android/notice"><b>推送管理</b>
						</a>
						<a
							href="#"><b>销量管理</b>
						</a>
						<a style="float:right;"
							href="/MealAppService/login.jsp"><b>退出登录</b>
						</a>
					</li>
				</ul>
			</div>
		</div>

	</body>
</html>
