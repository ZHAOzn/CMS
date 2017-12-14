<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CMS学生博客</title>
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
<style type="text/css">
#lllww:HOVER {
	text-decoration: none;
}
#firstDiv a:HOVER {
	text-decoration: none;
}
#secondDiv a:HOVER {
	text-decoration: none;
}
</style>
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
			<a class="navbar-brand" id="pageTitle" onclick="returnStudentIndex()"
					href="#">CMS</a>
		</div>
		<form class="navbar-form navbar-right"
			style="margin-right: 5%; float: left;">
			<a id="lllww" href="#"
				style="width: 100px; color: white; font-size: 1.7em;">个人博客</a>
		</form>
	</div>
	</nav>
	
	<!-- 教师首页 -->
			<form id="returnStudentIndexForm" action="<%=request.getContextPath()%>/student/studentLogin.do" method="post" style="display: none;">
			  <input type="text" name="studentRoNo" value="${student.studentRoNo}"/>
			  <input type="text" name="password" value="${student.studentPassword}"/>
			</form>
	
	<textarea id="replace" rows="20" cols="20" style="display: none;"></textarea>

	<div class="layui-container">
		<div class="" pad20 style="padding-top: 1px;">
			<!--<div class="fly-none">没有权限</div>-->
			<div class="layui-form layui-form-pane">
				<div class="layui-tab layui-tab-brief" lay-filter="user">
					<ul class="layui-tab-title">
						<li id="pushBlog" onclick="pushBlog()" class="layui-this">发表博客</li>
						<li id="lookAtBlog" onclick="lookAtBlog()" class="">查看博客</li>
					</ul>
					<script type="text/javascript">
					//返回首页
					function returnStudentIndex() {
						$('#returnStudentIndexForm').submit();
					}
					layui.use(['form'], function(){
						var form = layui.form;
						form.render(); 
						});
					   function pushBlog() {
						$('#D_ucm').hide();
						$('#LAY_ucm').show();
					}
					   //查看博客通过发布时间
					   function lookAtBlog() {
						   $.ajax({
					              type: "GET",
					              data: {
					            	  "blogAuthor":${student.studentRoNo}
					              },
					              contentType: "application/json; charset=utf-8",
					              async: false,
					              dataType: "json",
					              url: "<%=request.getContextPath()%>/blog/teacherReadBlog.do",
					              success: function (data) {
					            	 var dataObj = data.myBlogs;
									 con = "";
									 $.each(dataObj, function (index, item) {
										    con += "<tr id='TD"+item.blogId+"' class='ttr'>";
						        	        con += "<td style='text-align:center;'>" + index + "</td>";
						        	        con += "<td style='text-align:center;'><a id='"+item.blogId+"' onclick='getBlogById(this.id)' href='#'>" + item.blogTitle + "</a></td>";
						        	        con += "<td style='text-align:center;'><a href='#'>" + item.verify + "</a></td>";
						        	        con += "<td style='text-align:center;'><div class='site-demo-button' id='layerDemo'><a class='ttd' id='D"+item.blogId+"' data-method='notice' onclick='beforeDeleteMyBlog(this.id)' href='#'><i class='layui-icon' style='font-size: 20px; color: #1E9FFF;'>&#xe640;</i></a></div></td>";
						        	        con += "<td style='text-align:center;'><a id='U"+item.blogId+"' onclick='beforeUpdateMyBlog(this.id)' href='#'><i class='layui-icon' style='font-size: 20px; color: #1E9FFF;'>&#xe642;</i></a></td>";
						        	        con += "<tr/>";
						        	        if(index == 0){
						        	        	getBlogById(item.blogId);
						        	        }
						        	    });
									  $('#blogThreafd').html(con);
					            	  $('#D_ucm').show();
									  $('#LAY_ucm').hide();
					              },
					              error: function (data) {
					            	  alert("服务器异常");
					              }
					          });
							 //删除
							layui.use([ 'element', 'layer'], function() {
								var element = layui.element, $ = layui.jquery, layer = layui.layer;
								//触发事件
								var active = {
									notice : function() {
										//示范一个公告层
										layer.open({
													type : 1,
													title : false //不显示标题栏
													,
													closeBtn : false,
													area : '300px;',
													shade : 0.8,
													id : 'LAY_layuipro' //设定一个id，防止重复弹出
													,
													btn : [ '删除', '取消' ],
													yes : function(index, layero) {
														deleteMyBlog();
														layer.closeAll();
													},
													btn2 : function(index, layero) {
														//按钮【按钮二】的回调
													},
													btnAlign : 'c',
													moveType : 1 //拖拽模式，0或者1
													,
													content : '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">确定删除该博文？</div>'
												});
									}

								};

								$('#layerDemo .ttd').on(
										'click',
										function() {
											var othis = $(this), method = othis.data('method');
											active[method] ? active[method].call(this, othis) : '';
										});
					          });
					    }
					   //查看自己博客通过热度
					   function lookAtBlogByHot() {
						   $.ajax({
					              type: "GET",
					              data: {
					            	  "blogAuthor":${student.studentRoNo}
					              },
					              contentType: "application/json; charset=utf-8",
					              async: false,
					              dataType: "json",
					              url: "<%=request.getContextPath()%>/blog/teacherReadBlogByHot.do",
					              success: function (data) {
					            	 var dataObj = data.myBlogs;
									 con = "";
									 $.each(dataObj, function (index, item) {
										    con += "<tr id='TD"+item.blogId+"' class='ttr'>";
						        	        con += "<td style='text-align:center;'>" + index + "</td>";
						        	        con += "<td style='text-align:center;'><a id='"+item.blogId+"' onclick='getBlogById(this.id)' href='#'>" + item.blogTitle + "&nbsp;&nbsp;<i class='layui-icon' style='font-size: 10px; color: #FF5722;'>&#xe756;</i>("+ item.hotClick + ")</a></td>";
						        	        con += "<td style='text-align:center;'><div class='site-demo-button' id='layerDemo'><a class='ttd' id='D"+item.blogId+"' data-method='notice' onclick='beforeDeleteMyBlog(this.id)' href='#'><i class='layui-icon' style='font-size: 20px; color: #1E9FFF;'>&#xe640;</i></a></div></td>";
						        	        con += "<td style='text-align:center;'><a id='U"+item.blogId+"' onclick='beforeUpdateMyBlog(this.id)' href='#'><i class='layui-icon' style='font-size: 20px; color: #1E9FFF;'>&#xe642;</i></a></td>";
						        	        con += "<tr/>";
						        	        if(index == 0){
						        	        	getBlogById(item.blogId);
						        	        }
						        	    });
									  $('#blogThreafd').html(con);
					            	  $('#D_ucm').show();
									  $('#LAY_ucm').hide();
					              },
					              error: function (data) {
					            	  alert("服务器异常");
					              }
					          });
							 //删除
							layui.use([ 'element', 'layer'], function() {
								var element = layui.element, $ = layui.jquery, layer = layui.layer;
								//触发事件
								var active = {
									notice : function() {
										//示范一个公告层
										layer.open({
													type : 1,
													title : false //不显示标题栏
													,
													closeBtn : false,
													area : '300px;',
													shade : 0.8,
													id : 'LAY_layuipro' //设定一个id，防止重复弹出
													,
													btn : [ '删除', '取消' ],
													yes : function(index, layero) {
														deleteMyBlog();
														layer.closeAll();
													},
													btn2 : function(index, layero) {
														//按钮【按钮二】的回调
													},
													btnAlign : 'c',
													moveType : 1 //拖拽模式，0或者1
													,
													content : '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">确定删除该博文？</div>'
												});
									}

								};

								$('#layerDemo .ttd').on(
										'click',
										function() {
											var othis = $(this), method = othis.data('method');
											active[method] ? active[method].call(this, othis) : '';
										});
					          });
					}
					   function getBlogById(id) {
						   $.ajax({
					              type: "GET",
					              data: {
					            	  "blogId":id
					              },
					              contentType: "application/json; charset=utf-8",
					              async: false,
					              dataType: "json",
					              url: "<%=request.getContextPath()%>/blog/getStudentBlogById.do",
					              success: function (data) {
									  $('#thisBlogFifter').html(data.myBlog.blogFifter);
					                  $('#thisAuthor').html("作者:&nbsp;"+data.student.studentName);
									  $('#thisBeLongTo').html("类别:&nbsp;"+data.myBlog.belongTo);
									  $('#thisCreateTime').html("发布时间:&nbsp;"+data.myBlog.createTime);
									  $('#thisTarget').html("标签:&nbsp;"+data.myBlog.blogTarget);
					            	  $('#thisTitle').html(data.myBlog.blogTitle);
					            	  $('#thisContent').html(data.myBlog.blogContent);
					            	  $('#thisUp').html("(" + data.myBlog.up + ")");
					            	  $('#thisDown').html("(" + data.myBlog.down + ")");
					            	  
					              },
					              error: function (data) {
					            	  alert("服务器异常");
					              }
					          });
					}
					   var blogIdTem;
					   function beforeDeleteMyBlog(id) {
						   blogIdTem = id.substring(1);
					}
					   function deleteMyBlog() {
						   $.ajax({
					              type: "GET",
					              data: {
					            	  "blogId":blogIdTem
					              },
					              contentType: "application/json; charset=utf-8",
					              async: false,
					              dataType: "json",
					              url: "<%=request.getContextPath()%>/blog/deleteMyBlog.do",
					              success: function (data) {
					            	  if(data.result == true){
					            		  $('#TD'+blogIdTem).hide();
					            	  }else {
					            		  layui.use('layer', function(){
							 	               var $ = layui.jquery, layer = layui.layer; 
							   			      layer.open({
							   			        type: 1
							   			        ,offset: 'auto'
							   			        ,id: 'layerDemo'+'auto'
							   			        ,title: '失败'
							   			        ,content: '<div style="padding: 20px 100px; color:#FF5722">'+ "删除失败" +'</div>'
							   			        ,btn: '关闭'
							   			        ,btnAlign: 'c'
							   			        ,skin: 'demo-class'
							   			        ,shade: 0 
							   			        ,yes: function(){
							   			        	 layer.closeAll();
							   			        }
							   			      });
							 	            });
									}
					              },
					              error: function (data) {
					            	  alert("服务器异常");
					              }
					          });
					}
					   //修改博文
					   var myBlogIdTem;
					   function beforeUpdateMyBlog(id) {
						   blogId = id.substring(1);
						   $.ajax({
					              type: "GET",
					              data: {
					            	  "blogId":blogId
					              },
					              contentType: "application/json; charset=utf-8",
					              async: false,
					              dataType: "json",
					              url: "<%=request.getContextPath()%>/blog/getStudentBlogById.do",
					              success: function (data) {
					            	  if(data.result == true){
					            		  myBlogIdTem = data.myBlog.blogId;
					            		   $('#belongTo').val(data.myBlog.belongTo);
					            		   $('#title').val(data.myBlog.blogTitle);
					            		   $('#blogContent').val(data.myBlog.blogContent);
					            		   $('#blogTarget').val(data.myBlog.blogTarget);
					            		   $('#blogFifter').val(data.myBlog.blogFifter);
					            		  
					            		    $('#firstDiv').hide();
					            		    $('#secondDiv').hide();
					            		    $('#beforesaveChange').show();
					            		    $('#D_ucm').hide();
											$('#LAY_ucm').show();
					            	  }else {
					            		  layui.use('layer', function(){
							 	               var $ = layui.jquery, layer = layui.layer; 
							   			      layer.open({
							   			        type: 1
							   			        ,offset: 'auto'
							   			        ,id: 'layerDemo'+'auto'
							   			        ,title: '失败'
							   			        ,content: '<div style="padding: 20px 100px; color:#FF5722">'+ "获取不到文章" +'</div>'
							   			        ,btn: '关闭'
							   			        ,btnAlign: 'c'
							   			        ,skin: 'demo-class'
							   			        ,shade: 0 
							   			        ,yes: function(){
							   			        	 layer.closeAll();
							   			        }
							   			      });
							 	            });
									}
					              },
					              error: function (data) {
					            	  alert("服务器异常");
					              }
					          });
					}
				 //修改博文
					function saveChange() {
						var blogId = myBlogIdTem;
						if($('#L_vercode').val() == $('#sum').val() && $('#sum').val() != ""){
							  if($('#belongTo').val() != "" && $('#title').val() != "" && 
									  $('#blogTarget').val() != "" && $('#blogFifter').val() != "" ){
								  
							    $('#jiaoben').show();
							    
						    	$.ajax({
						              type: "POST",
						              data: {
						            	  "blogAuthor":${student.studentRoNo},
						            	  "blogId":blogId,
						            	  "role":"教师",
						            	  "title":$('#title').val(),
						            	  "belongTo":$('#belongTo').val(),
						            	  "blogContent":$('#replace').val(),
						            	  "blogTarget":$('#blogTarget').val(),
						            	  "blogFifter":$('#blogFifter').val(),
						              },
						              contentType: "application/x-www-form-urlencoded; charset=utf-8",
						              async: false,
						              dataType: "json",
						              url: "<%=request.getContextPath()%>/blog/updateMyBlog.do",
						              success: function (data) {
						            	  if(data.result == true){
						            		  layui.use('layer', function(){
						   	 	               var $ = layui.jquery, layer = layui.layer; 
						   	   			      layer.open({
						   	   			        type: 1
						   	   			        ,offset: 'auto'
						   	   			        ,id: 'layerDemo'+'auto'
						   	   			        ,title: '成功'
						   	   			        ,content: '<div style="padding: 20px 100px;">'+ "修改博文成功" +'</div>'
						   	   			        ,btn: '关闭'
						   	   			        ,btnAlign: 'c'
						   	   			        ,skin: 'demo-class'
						   	   			        ,shade: 0 
						   	   			        ,yes: function(){
						   	   			 		 window.location.reload();
						   	   			        }
						   	   			      });
						   	 	            });
						            	  }else{
						            		  layui.use('layer', function() {
						          				var $ = layui.jquery, layer = layui.layer;
						          				layer.msg('修改失败..');
						          			});
						            	  }
						              },
						              error: function (data) {
						            	  alert("服务器异常");
						              }
						          });
							  }
						   }else{
							   layui.use('layer', function() {
									var $ = layui.jquery, layer = layui.layer;
									layer.msg('怀疑你是机器人QAQ..');
								});
						}
					}
					</script>
					<div id="D_ucm" class="site-text site-block" style="display: none; padding-top: 15px;
					width: 100%; padding-left: 1%;">
					<div style="width: 100%; height: 800px; " >
					<span>发布过的博文&nbsp;<i class="layui-icon" style="font-size: 23px; color: #1E9FFF;">&#xe609;</i> </span>
					<br/>
					<ul class="layui-tab-title" style="">
						<li><a href="#" onclick="lookAtBlog()">按发布时间&nbsp;<i class="layui-icon" style="font-size: 17px; color: #FF5722">&#xe62f;</i> </a> </li>
						<li><a href="#" onclick="lookAtBlogByHot()">按点击热度&nbsp;<i class="layui-icon" style="font-size: 17px; color: #FF5722">&#xe62f;</i> </a> </li>
					</ul>
					<div style=" width: 30%;float: left; overflow: auto;">
					<table class="layui-table" style="width: 100%;">
					 <colgroup>
						<col width="40">
						<col width="200">
						<col width="100">
						<col width="50">
						<col width="50">
					</colgroup>
					<thead>
						<tr>
							<th style="text-align: center;"></th>
							<th style="text-align: center;">标题</th>
							<th style="text-align: center;">状态</th>
							<th colspan="2" style="text-align: center;">操作</th>
						</tr>
					</thead>
					 <tbody id="blogThreafd">
		                
					 </tbody>
					</table>
					</div>
					<!-- 博文内容 -->
					<div id="waitForYou" style="width: 67%;height:700px; float: left;border-left: solid;  margin-left:2%;
					border-color: #e2e2e2; padding-left: 2%;overflow: auto;">
					   <h4 id="thisTitle" style="width: 100%;text-align: center; font-family: 微软雅黑"></h4>
					   <hr class="layui-bg-cyan">
					   <ul>
					     <li style="color: #FF5722;" >
					     <input type="radio" checked="checked" readonly="readonly" title="访问权限：" style="margin-left: 0;padding-left: 0;"/>
					     <span id="thisBlogFifter"></span> </li>
					     <li id="thisAuthor"></li>
					     <li id="thisBeLongTo"></li>
					     <li id="thisCreateTime"></li>
					     <li id="thisTarget"></li>
					      <li>&nbsp;</li>
					       <li>&nbsp;</li>
					   </ul>
					   <!-- 正文 -->
					   <div id="thisContent" style="width: 100%; padding-left: 2%;">
					   
					   </div>
					   <!-- 点赞啥的 -->
					    <hr class="layui-bg-cyan">
					    <div id="thisFooter" style="width: 100%; padding-left: 20%；">
					        <i class="layui-icon" style="font-size: 20px; color: #FF5722">&#xe6c6;</i><span id="thisUp"></span>  
					        <i class="layui-icon" style="font-size: 20px; padding-left:20px;
					         color: #FF5722;">&#xe6c5;</i>  <span id="thisDown"></span>  
					   </div>
					
					</div>
					<br/>
					</div>
					</div>
					<br/>
					<div class="layui-form layui-tab-content" id="LAY_ucm"
						style="padding: 0px 0;">
						<div class="layui-tab-item layui-show">
							<form action="" method="post">
								<div class="layui-row layui-col-space15 layui-form-item">
								
									<div class="layui-col-md3">
										<label class="layui-form-label">所在专栏</label>
										<div class="layui-input-block">
											<select id="belongTo" lay-verify="required" required name="class"
												lay-filter="column">
												<option></option>
												<option value="AI">AI</option>
												<option value="安全">安全</option>
												<option value="算法">算法</option>
												<option value="后端">后端</option>
												<option value="前端">前端</option>
												<option value="终端">终端</option>
											</select>
										</div>
									</div>
									
									<div class="layui-col-md9">
										<label for="L_title" class="layui-form-label">标题</label>
										<div class="layui-input-block">
											<input type="text" id="title" name="title" required
												lay-verify="required" autocomplete="off" class="layui-input">
											<!-- <input type="hidden" name="id" value="{{d.edit.id}}">  -->
										</div>
									</div>
									
								</div>
								<!-- 富文本编辑器 -->
								<div class="layui-form-item layui-form-text">
									<div class="layui-input-block">
										<textarea id="blogContent" name="blogContent" lay-verify="requirevalidate"
											placeholder="说点什么" style="height: 260px;"></textarea>
									</div>
								</div>
			<script>
			var tem;
				layui.use('layedit', function(){
				  var layedit = layui.layedit;
				  
				  layui.code
				  layedit.set({
				    uploadImage: {
				      url: '<%=request.getContextPath()%>/teacher/myBlogImg.do' //接口url
				      ,type: 'post' //默认post
				    }
				  });
				  
				  var index = layedit.build('blogContent',{
					  
				  }); //建立编辑器
				  tem = index
				});
				
				  function beforeCreateNewBlog() {
					  layui.use('layedit', function(){
						  var layedit = layui.layedit;
						  $('#replace').val(layedit.getContent(tem));
						});
					  if($('#belongTo').val() != "" && $('#title').val() != ""  && 
							  $('#blogTarget').val() != "" && $('#blogFifter').val() != "" ){
					    	$.ajax({
					              type: "GET",
					              data: {
					              },
					              contentType: "application/json; charset=utf-8",
					              async: false,
					              dataType: "json",
					              url: "<%=request.getContextPath()%>/blog/getRandomValue.do",
					              success: function (data) {
					            	   $('#a1').html(data.a1);
					            	   $('#a2').html(data.a2);
					            	   $('#sum').val(data.sum);
					            	   $('#jiaoben').show();
					            	   $('#firstDiv').hide();
					            	   $('#secondDiv').show();
					            	   window.location.hash = "#jiaoben";  
					              },
					              error: function (data) {
					            	  alert("服务器异常");
					              }
					          });
					  }
				}
				  //验证数字
				  function beforeSaveChange() {
					  layui.use('layedit', function(){
						  var layedit = layui.layedit;
						  $('#replace').val(layedit.getContent(tem));
						});
					  if($('#belongTo').val() != "" && $('#title').val() != ""  && 
							  $('#blogTarget').val() != "" && $('#blogFifter').val() != "" ){
					    	$.ajax({
					              type: "GET",
					              data: {
					              },
					              contentType: "application/json; charset=utf-8",
					              async: false,
					              dataType: "json",
					              url: "<%=request.getContextPath()%>/blog/getRandomValue.do",
					              success: function (data) {
					            	   $('#a1').html(data.a1);
					            	   $('#a2').html(data.a2);
					            	   $('#sum').val(data.sum);
					            	   $('#jiaoben').show();
					            	   $('#beforesaveChange').hide();
					            	   $('#saveChange').show();
					            	   window.location.hash = "#jiaoben";  
					              },
					              error: function (data) {
					            	  alert("服务器异常");
					              }
					          });
					  }
				}
				  // 新建博文
				  function createNewBlog() {
					  if($('#L_vercode').val() == $('#sum').val() && $('#sum').val() != ""){
					  if($('#belongTo').val() != "" && $('#title').val() != "" && 
							  $('#blogTarget').val() != "" && $('#blogFifter').val() != "" ){
						  
					    $('#jiaoben').show();
					    
				    	$.ajax({
				              type: "POST",
				              data: {
				            	  "blogAuthor":${student.studentRoNo},
				            	  "role":"学生",
				            	  "title":$('#title').val(),
				            	  "belongTo":$('#belongTo').val(),
				            	  "blogContent":$('#replace').val(),
				            	  "blogTarget":$('#blogTarget').val(),
				            	  "blogFifter":$('#blogFifter').val(),
				              },
				              contentType: "application/x-www-form-urlencoded; charset=utf-8",
				              async: false,
				              dataType: "json",
				              url: "<%=request.getContextPath()%>/blog/insertMyBlog.do",
				              success: function (data) {
				            	  if(data.result == true){
				            		  layui.use('layer', function(){
				   	 	               var $ = layui.jquery, layer = layui.layer; 
				   	   			      layer.open({
				   	   			        type: 1
				   	   			        ,offset: 'auto'
				   	   			        ,id: 'layerDemo'+'auto'
				   	   			        ,title: '成功'
				   	   			        ,content: '<div style="padding: 20px 100px;">'+ "新建博文成功" +'</div>'
				   	   			        ,btn: '关闭'
				   	   			        ,btnAlign: 'c'
				   	   			        ,skin: 'demo-class'
				   	   			        ,shade: 0 
				   	   			        ,yes: function(){
				   	   			 		 window.location.reload();
				   	   			        }
				   	   			      });
				   	 	            });
				            	  }else{
				            		  layui.use('layer', function() {
				          				var $ = layui.jquery, layer = layui.layer;
				          				layer.msg('新建失败..');
				          			});
				            	  }
				              },
				              error: function (data) {
				            	  alert("服务器异常");
				              }
				          });
					  }
				   }else{
					   layui.use('layer', function() {
							var $ = layui.jquery, layer = layui.layer;
							layer.msg('怀疑你是机器人QAQ..');
						});
				}
			}
				//刷新页面函数
					 function yourFunction() {
						 window.location.reload();
					}
				
			</script>
			
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">自定义标签</label>
							<div class="layui-input-inline" style="width: 220px;">
								<input type="text" id="blogTarget" name="" required
									lay-verify="required" placeholder="便于查询,多个标签以逗号隔开" autocomplete="off"
									class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">发表后无法更改</div>
						</div>
					</div>
					
					<div class="layui-col-md3">
							<label class="layui-form-label">访问权限</label>
							<div class="layui-input-block">
								<select lay-verify="required" required name="class"
									lay-filter="column" id="blogFifter">
									<option></option>
									<option value="仅自己可见">私有</option>
									<option value="公开">公开</option>
								</select>
							</div>
						</div><br/><br/><br/>
						
					<div id="jiaoben" class="layui-form-item" style="display: none;">
						<label for="L_vercode" class="layui-form-label">脚本过滤</label>
						<div class="layui-input-inline">
							<input type="text" id="L_vercode" name="vercode"
								 placeholder="请回答后面的问题"
								autocomplete="off" class="layui-input">
						</div>
						<div class="layui-form-mid">
							<span style="color: #c00;">
							<span id="a1"></span>+
							<span id="a2"></span>=?
							</span>
								</div>
						</div>
							
							<input type="text" id="sum" style="display: none;">
							
							
							<div id="firstDiv" >
								<a class="layui-btn" onclick="beforeCreateNewBlog()">立即发布</a>
							</div>
							
							<div id="secondDiv"  style="display: none;">
								<a class="layui-btn" onclick="createNewBlog()">立即发布</a>
							</div>
							
							<div id="beforesaveChange"  style="display: none;">
								<a class="layui-btn" onclick="beforeSaveChange()">保存修改</a>
							</div>
							
							<div id="saveChange"  style="display: none;">
								<a class="layui-btn" onclick="saveChange()">保存修改</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

	<div class="fly-footer" style="margin-top: 10px;">
		<p>
			<a href="#">CMS博客</a> 2017 &copy; <a href="#">cms118.cc 出品</a>
		</p>
		<p>
			<a href="#" onclick="firstPlan()">初创计划</a> <a href="#"
				onclick="getEmail()">联系作者合作</a> <a href="#" onclick="getMsg()">微信公众号</a>
		</p>
	</div>
	<script type="text/javascript">
	  

	</script>

	<script>
	layui.use('form', function() {
		var form = layui.form;
		form.render();
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

</body>
</html>