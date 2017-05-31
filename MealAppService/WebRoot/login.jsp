<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	HttpSession session11 = request.getSession(true);
	session11.removeAttribute("LoginID");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<link rel="stylesheet" href="style/Style_Login.css" type="text/css"></link>
<link rel="stylesheet" href="style/PublicStyle.css" type="text/css"></link>
<script type="text/javascript" src="Script/jquery-1.4.4.js"></script>
<Script type="text/javascript">
	$(function() {
		$("#button1")
				.click(
						function() {
							if ($("#txtLoginID").val() == "") {
								alert("请输入登录名");
								return;
							}
							if ($("#txtPassWord").val() == "") {
								alert("请输入密码");
								return;
							}

							var param = {
								Action : "adminlogin",
								loginid : $("#txtLoginID").val(),
								passwords : $("#txtPassWord").val()
								
							};

							$.get(
											"servlet/ServletService?ran="
													+ Math.random(),
											param,
											function(data) {
												if (data == 1) {
													location.href = "/MealAppService/servlet/GridServlet?Action=getlist&currentpage=0";
												} else {
													alert("登录失败");
												}
											});
						});
	})
</Script>
</head>

<body>
    
	<div
		style="width:300px; height: 400px; margin: 0 auto; position: relative;">
		<table border="0" cellspacing="10"
		      style="position:relative;top:80px;left:400px;">
		    <tr>
		    <td><h1>管理员登录</h1></td>
		    </tr>
			<tr>
				<td>登录名：</td>
				<td class="td_right"><input id="txtLoginID" type="text"
					class="textbox" value=""></td>
			</tr>
			<tr>
				<td>密 码：</td>
				<td class="td_right"><input id="txtPassWord" type="password"
					class="textbox" value=""></td>
			</tr>
			
			<tr>
				<td></td>
				<td class="td_right" valign="top"><input id="button1"
					type="button" class="btnClass_79px_A" value="登录"></td>
			</tr>
		</table>
	</div>
</body>
</html>
