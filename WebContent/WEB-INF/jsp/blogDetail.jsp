<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CMS个人博客</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="keywords" content="CMS">
<meta name="description" content="CMS博客">
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/global.css">
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/layui.css">
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/icon/cms2.ico" media="screen" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/layui/layui.js "></script>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<script src="<%=request.getContextPath()%>/layui/mods/index.js"></script>
<title>博客查询结果</title>
</head>
<body>
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
			<a class="navbar-brand" href="<%=request.getContextPath()%>/teacher/teacherLogin.do?id=${teacher.teacherMobile}&&password=${teacher.teacherPassword}">CMS</a>
		</div>
		<form class="navbar-form navbar-right"
			style="margin-right: 5%; float: left;">
			<a id="lllww" href="#"
				style="width: 100px; color: white; font-size: 1.7em;">博文</a>
		</form>
	</div>
	</nav>
	
		<!-- 博文内容 -->
	<div id="waitForYou" style="width: 80%;border-left: solid;  margin-left:2%;
	border-color: #e2e2e2; padding-left: 2%;">
	   <h4 id="thisTitle" style="width: 100%;text-align: center; font-family: 微软雅黑">${myBlog.blogTitle}</h4>
	   <hr class="layui-bg-cyan">
	   <ul>
	     <li style="color: #FF5722;" >
	     访问权限：<input type="radio" checked="checked" readonly="readonly" title="访问权限：" style="margin-left: 0;padding-left: 0;"/>
	     <span id="thisBlogFifter">${myBlog.blogFifter}</span> </li>
	   <li id="thisAuthor">  作者：${teacher.teacherName}</li>
	    <li id="thisBeLongTo"> 类别： ${myBlog.belongTo}</li>
	     <li id="thisCreateTime"> 创建时间：${myBlog.createTime}</li>
	     <li id="thisTarget"> 标签：${myBlog.blogTarget}</li>
	      <li>&nbsp;</li>
	       <li>&nbsp;</li>
	   </ul>
	   <!-- 正文 -->
	   <div id="thisContent" style="width: 100%; padding-left: 2%;">
	   ${myBlog.blogContent}
	   </div>
	   <!-- 点赞啥的 -->
	    <hr class="layui-bg-cyan">
	    <div id="thisFooter" style="width: 100%; padding-left: 20%;padding-bottom: 100px;">
	        <i class="layui-icon" style="font-size: 20px; color: #FF5722">&#xe6c6;</i><span id="thisUp">${myBlog.up}</span>  
	        <i class="layui-icon" style="font-size: 20px; padding-left:20px;
	         color: #FF5722;">&#xe6c5;</i>  <span id="thisDown">${myBlog.down}</span>  
	   </div>
	
	</div>

    <script>
//刷新页面函数
	 function yourFunction() {
		 window.location.reload();
	}
	 layui.use('form', function() {
			var form = layui.form;
		form.verify({
			requirevalidate: [/\S/,'必填项不可为空']
		});
		});
			layui.cache.page = 'jie';
			layui.cache.user = {
			 
			};
			layui.config({
			  version: "3.0.0"
			  ,base: '<%=request.getContextPath()%>/layui/mods/'
			}).extend({
				fly : 'index'
			}).use('fly');
			function getMsg() {
				layui.use('layer', function() {
					var $ = layui.jquery, layer = layui.layer;
					layer.msg('开发中，敬请期待！');
				});
			}
			function getEmail() {
				layui.use('layer', function() {
					var $ = layui.jquery, layer = layui.layer;
					layer.msg('E-Mail:1185547466@qq.com');
				});
			}
			function firstPlan() {
				layui.use('layer', function() {
					var $ = layui.jquery, layer = layui.layer;
					layer.msg('感谢您的关注！');
				});
			}
	</script>
	
	
	<div class="fly-footer" style="margin-top: 10px;">
		<p>
			<a href="#">CMS博客</a> 2017 &copy; <a href="#">cms118.cc 出品</a>
		</p>
		<p>
			<a href="#" onclick="firstPlan()">初创计划</a> <a href="#"
				onclick="getEmail()">联系作者合作</a> <a href="#" onclick="getMsg()">微信公众号</a>
		</p>
	</div>

</body>
</html>