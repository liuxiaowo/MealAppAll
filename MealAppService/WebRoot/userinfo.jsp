<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="style/PublicStyle.css" type="text/css"></link>
<script type="text/javascript" src="Script/jquery-1.4.4.js"></script>
<script type="text/javascript">
function getUrlParamValue(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return unescape(r[2]);
	} else {
		return null;
	}
}
$(function() {
if(getUrlParamValue("id")!=null){
	var param = {
		Action : "getOneRow",
		Table:"users",
		ID:getUrlParamValue("id"),
	};
	$.get("servlet/ServletService?ran=" + Math.random(), param, function(data) {	
		if (data.length > 0) {
			data = eval("(" + data + ")");
			if (data.length > 0) {
				$("#txtloginid").val(data[0].loginid) ;
				$("#txtname").val(data[0].name) ;	
				$("#txtpasswords").val(data[0].passwords) ;	
				$("#txtpasswordsok").val(data[0].passwords) ;	
			}
		}
	});
}
})

function check() {
	if ($("#txtloginid").val() == "") {
		alert("请输入账号");
		return;
	}
	if ($("#txtname").val() == "") {
		alert("请输入姓名");
		return;
	}
	if ($("#txtpasswords").val() == "") {
		alert("请输入密码");
		return;
	}
	if ($("#txtpasswordsok").val() == "") {
		alert("请再次输入密码");
		return;
	}
	if ($("#txtpasswords").val() !=$("#txtpasswordsok").val()) {
		alert("你两次输入的密码不一致");
		return;
	}
	var id = getUrlParamValue("id");
	if (id == null)
		id = 0;
	var param = {
		Action : "edituser",
		ID : id,
		name : encodeURI($("#txtname").val()),
		loginid : $("#txtloginid").val(),
		passwords : $("#txtpasswords").val()
	
	};
	$.get("servlet/ServletService?ran=" + Math.random(), param, function(data) {
		if (data.length > 0) {	
			if(data==1){alert("操作成功"); 
			  location.href='/MealAppService/servlet/GridServlet?Action=getuserlist&currentpage=1';
			}
			
		}
	});

}
</script>
</head>
<body>
	<div class="backbody" style="background: #FFF;">
	<jsp:include page="top.jsp"></jsp:include>
		<div style="padding: 15px 5px 10px 0;">
			<table width="400px" style="margin: 10px auto; margin-left: 300px;"
				cellspacing="0px" id="mytable" class="FormTable">
				<tbody>

					<tr>
						<td>账号</td>
						<td>
						<input type="text" id="txtloginid" class="textbox" />
						<span	style="color: Red">*</span>
						</td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" id="txtname" class="textbox" /> <span
							style="color: Red">*</span>
						</td>
					</tr>
					<tr>
						<td>密码</td>
						<td><input type="password" id="txtpasswords" class="textbox" /> <span
							style="color: Red">*</span>
						</td>
					</tr>
					<tr>
						<td>确认密码</td>
						<td><input type="password" id="txtpasswordsok" class="textbox" /> <span
							style="color: Red">*</span>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="button" ID="btnSave" value="确定"
							class="btnClass_79px_A" onclick="javascript:check()" /> &nbsp; <input
							id="Button2" type="button" value="返回" class="btnClass_79px_A"
							onclick="javascript:history.go(-1)" /></td>
					</tr>
				</tbody>
			</table>
		</div>

	</div>
</body>
</html>
