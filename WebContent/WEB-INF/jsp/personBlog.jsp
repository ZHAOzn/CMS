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
			<a class="navbar-brand" href="<%=request.getContextPath()%>/teacher/teacherLogin.do?id=${teacher.teacherMobile}&&password=${teacher.teacherPassword}">CMS</a>
		</div>
		<form class="navbar-form navbar-right"
			style="margin-right: 5%; float: left;">
			<a id="lllww" href="#"
				style="width: 100px; color: white; font-size: 1.7em;">个人博客</a>
		</form>
	</div>
	</nav>
	
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
					   function pushBlog() {
						$('#D_ucm').hide();
						$('#LAY_ucm').show();
					}
					   function lookAtBlog() {
						   $('#D_ucm').show();
							$('#LAY_ucm').hide();
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
					              url: "<%=request.getContextPath()%>/blog/getBlogById.do",
					              success: function (data) {
					            	   $('#waitForYou').html(data.myBlog.blogContent);
					            	  
					              },
					              error: function (data) {
					            	  alert("服务器异常");
					              }
					          });
					}
					</script>
					<div id="D_ucm" class="site-text site-block" style="display: none; padding-top: 15px;
					width: 80%; padding-left: 15%;">
					<span>发布过的博文&nbsp;<i class="layui-icon" style="font-size: 23px; color: #1E9FFF;">&#xe609;</i> </span>
					<br/>
					<ul class="layui-tab-title">
						<li>按发布时间&nbsp;<i class="layui-icon" style="font-size: 17px; color: #FF5722">&#xe62f;</i>  </li>
						<li>按点击热度&nbsp;<i class="layui-icon" style="font-size: 17px; color: #FF5722">&#xe62f;</i>  </li>
					</ul>
					<ul style="padding-left: 2%;">
					 <c:choose>
					   <c:when test="${! empty myBlogs}">
					      <c:forEach items="${myBlogs}" var="s">
					        <li><span>${s.blogId}.&nbsp;</span> <a id="${s.blogId}" onclick="getBlogById(this.id)" href="#">${s.blogTitle}</a></li>
					      </c:forEach>
					   </c:when>
					     <c:otherwise>
					       <li>暂无数据</li>
					     </c:otherwise>
					 </c:choose>
					</ul>
					
					<div id="waitForYou">
					 <img alt="" src="D:\gitdemo\2014-S7-Java1314\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\CMS\blog\timg (1).jpg
					 ">
					
					</div>
					
					</div>
					
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
/* 					  alert(111111222222);
					  alert($('#belongTo').val());
					  alert($('#title').val());
					  
					  alert($('#blogTarget').val());
					  alert($('#blogFifter').val()); */
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
				  
				  function createNewBlog() {
					  if($('#L_vercode').val() == $('#sum').val() && $('#sum').val() != ""){
					  if($('#belongTo').val() != "" && $('#title').val() != "" && 
							  $('#blogTarget').val() != "" && $('#blogFifter').val() != "" ){
						  
					    $('#jiaoben').show();
					    
				    	$.ajax({
				              type: "GET",
				              data: {
				            	  "blogAuthor":${teacher.teacherMobile},
				            	  "role":"教师",
				            	  "title":$('#title').val(),
				            	  "belongTo":$('#belongTo').val(),
				            	  "blogContent":$('#replace').val(),
				            	  "blogTarget":$('#blogTarget').val(),
				            	  "blogFifter":$('#blogFifter').val(),
				              },
				              contentType: "application/json; charset=utf-8",
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
									<option value="private">私有</option>
									<option value="public">公开</option>
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
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

	<div class="fly-footer">
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