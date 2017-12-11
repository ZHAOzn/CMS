<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width; initial-scale=1.0">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        *{margin:0;padding:0;text-decoration:none;list-style:none;
         font-size:14px;font-family:"微软雅黑";text-align:center;
         color:black;}
         .clear{clear:both;}
         #header,#content,#footer{margin:0 auto;margin-top:10px;}  
         #header,#footer{margin-top:10px;height:100px;}
         #header,#footer,.left,.right,.center{background:#333;}
        /*通用样式*/
         /*手机*/
        @media screen and (max-width:600px){
           #header,#content,#footer{width:400px;}
           .right,.center{margin-top:10px;}
           .left,.right{height:100px;}
           .center{height:200px;}
        }

        /*平板*/
        @media screen and (min-width:600px) and (max-width:960px){
        #header,#content,#footer{width:600px;}
        .right{display:none;}
        .left,.center{height:400px;float:left;}
        .left{width:160px;margin-right:10px;}
        .center{width:430px;}
        }

        /*PC*/
        @media screen and (min-width:960px){
        #header,#content,#footer{width:960px;}
        .left,.center,.right{height:400px;float:left;}
        .left{width:200px;margin-right:10px;}
        .center{width:540px;margin-right:10px;}
        .right{width:200px;} 
        }
    </style>
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/icon/cms2.ico" media="screen" />

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mycss.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.2.0.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/layui.css">
<script src="<%=request.getContextPath()%>/layui/layui.js "></script>
<title>课程信息</title>
</head>
<body style="background-color: #eeeeee">


	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">CMS</a>
		</div>
		<form class="navbar-form navbar-right" style="margin-right: 5%; float: left; ">
					<a id="lllww" style="width: 100px; color: white; font-size: 1.7em;">添加课程</a>
				</form>
	</div>
	</nav>
	<div class="site-text site-block"
		style="width: 100%; margin-top: 16%;">
		<form action="<%=request.getContextPath()%>/course/createQrNew.do">
			<table style="width: 100%; height: 30em;"
				class="layui-table">
				<tr>
					<td>课程名称</td>
					<td><%=request.getParameter("courseName")%></td>
				</tr>
				
				<tr>
					<td>任课老师</td>
					<td><%=request.getParameter("teacherName")%></td>
				</tr>
				
				<tr>
					<td>当前学年</td>
					<td><%=request.getParameter("currentTime")%></td>
				</tr>
				
				<tr>
					<td>当前学期</td>
					<td><%=request.getParameter("tem")%></td>
				</tr>
				
				<tr>
					<td>课程编码</td>
					<td><%=request.getParameter("courseId")%></td>
				</tr>
				
				<tr style="text-align: center;">
					<td colspan="2"><input
						id="perfectButton" type="submit"
						value="加入课程" class="layui-btn" style="width: 70%;"></td>
				</tr>
				
			</table>
			<input style="display: none;" type="text" name="courseId"
				value="<%=request.getParameter("courseId")%>"> <input
				style="display: none;" type="text" name="teacherMobile"
				value="<%=request.getParameter("teacherMobile")%>">
		</form>

	</div>

</body>
</html>