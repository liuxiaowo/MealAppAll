<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- JSTL:JSP Standard Tag Library，JSP标准标签库  必须声明-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 1. request.getContextPath() 获得当前的项目根目录路径；
2.声明一个字符串变量path；
3.将当前项目的根目录复制给变量path。 -->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <!-- 加上下面语句,如果有超链接<a href="wel.jsp"></a>这样的意思就是wel.jsp前面已经加上了这个jsp页面的绝对路径 -->
		<base href="<%=basePath%>">

		<title>晨餐私厨服务器端</title>
       
        <!-- 清除浏览器中的缓存，它和其它几句合起来用，就可以使你再次进入曾经访问过的页面时，ie浏览器必须从服务端下载最新的内容，达到刷新的效果。 -->
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<!-- 向浏览器说明网页关键词 -->
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<!-- 向搜索引擎说明网站的主要内容 -->
		<meta http-equiv="description" content="晨餐私厨">
		<!-- 导入公共样式css ；目录在WebRoot/style/PublicStyle.css-->
		<link rel="stylesheet" href="style/PublicStyle.css" type="text/css"></link>
		<!--  -->
	    <script type="text/javascript" src="Script/jquery-1.4.4.js"></script>
		<script type="text/javascript">
			function fndelete(id) {
			    //弹出框,点确定返回true 否则 false
				if (confirm("确定删除当前记录吗？")) {
				   // jQuery ajax - get() 方法 ; get请求获取json数据
					$.get("servlet/ServletService?Ran=" + Math.random(), {
						Action : "Del",
						Table : "breakfast",
						ID:id
				}, function(data) {
				if (data == 1) {
					alert("恭喜您,删除成功！");
					//浏览器使用缓存中的数据，刷新当前界面,直接向url提交数据
					location.href = location.href;
				} else {
					alert("对不起,删除失败,请稍后再试！");
				}

			});
		}
		}
		function search() {
		    //id为txtkeyword（标题）等于空
			if ($("#txtkeyword").val() == "") {
			    //刷新当前界面
				location.href = "/MealAppService/servlet/GridServlet?Action=getlist&currentpage=1";
			} else {
				location.href = location.href + "&msg="
				+ encodeURI($("#txtkeyword").val());
			}
		}
		</script>

	</head>
	<body>
		<div class="backbody" style="background: #FFF;">
			<jsp:include page="top.jsp"></jsp:include>
			<div style=" padding:10px;">
				<div style="padding: 15px 5px 10px 0;">
					<table class="SearchTable" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td>
								标题：
							</td>
							<td>
							    <!-- 搜索输入框 -->
								<input type="text" class="textbox" id="txtkeyword" />
							</td>
							<td>
								<input type="button" class="btnClass_79px_A" value="搜索"
									onclick="javascript:search();" />
							</td>
							<td>
							    <!-- 添加早餐 -->
								<input type="button" class="btnClass_79px_A" value="添加"
									onclick="javascript:location.href='/MealAppService/uploadFileDialog.jsp'" />

							</td>
						</tr>
					</table>
					<table width="940px;" class="GridTable" border="0" cellspacing="0"
						cellpadding="0" id="GridTableID">

						<tr>
							<th>
								图片
							</th>
							<th>
								标题
							</th>
							<th style='width:300px;'>
								简介
							</th>
							<th>
								类型
							</th>
							<th>
								单价
							</th>
							<th>
								剩余数量
							</th>
							<th style='width:60px;'>
								修改
							</th>
							<th class="right" style='width:60px;'>
								删除
							</th>
						</tr>
						<!-- for循环,获取list -->
						<c:forEach var="data" items="${datalist}">
							<tr>
								<td>
									<a target="_blank" href="UploadFile/${data.img_url}"><img src="UploadFile/${data.img_url}" width="60px"
										height="30px" /></a>
								</td>
								
								<td>
									${data.title}
								</td>
								<td >
									${data.intro}
								</td>
								<td>
									${data.typename}
								</td>
							    <td >
									${data.price}
								</td>
                                <td >
									${data.amount}
								</td>
							       
								<td>
									 
									<input id='Button2' title="修改" type="button" value=""
										class="btnGrid"
										onclick="javascript:location.href='/MealAppService/uploadFileDialog.jsp?id=${data.id}'"
										style="background: url(images/edit.gif)" />
								
								</td>
								<td class="right">
									<input id='${data.id}'
										onclick='javascript:fndelete(${data.id})' title="删除"
										type="button" value="" class="btnDel  btnGrid"
										style="background: url(images/delete.gif)" />
								</td>
							</tr>
						</c:forEach>
					</table>
					<div style="margin-top: 5px;">
						<a href="servlet/GridServlet?Action=getlist&currentpage=${1}">首页</a>
						<a
							href="servlet/GridServlet?Action=getlist&currentpage=${currentpage-1}">上一页</a>
						<a
							href="servlet/GridServlet?Action=getlist&currentpage=${currentpage+1}">下一页</a>
						<a
							href="servlet/GridServlet?Action=getlist&currentpage=${pagecount}">尾页</a>
						当前页是第${currentpage}/${pagecount}页,共有${total}条记录
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
