<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/teacherPage.css">
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/icon/cms2.ico" media="screen" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/layui.css">
<script src="<%=request.getContextPath()%>/layui/layui.js "></script>
<title>超级管理员</title>

<script type="text/javascript">
	 $(document).ready(function () {
		 $('#checkStudentShow').click(function name() {
			 $('#studentInfo').show();
			 $('#messageList').html("学生信息");
			 $('#studentLog').hide();
			 $('#teacherInfo').hide();
			 $('#teacherLog').hide();
			 $('#crud').hide();
			 $('#manageJurisdiction').hide();
			 $('#feedbackDiv').hide();
			 $('#studentDetail').hide();
			 $('#manageBlogDiv').hide();
			 $('#teacherDetail').hide();
		});
		 $('#studentLogShow').click(function name() {
			 $('#messageList').html("操作日志");
			 $('#studentInfo').hide();
			 $('#studentLog').show();
			 $('#teacherInfo').hide();
			 $('#teacherLog').hide();
			 $('#feedbackDiv').hide();
			 $('#crud').hide();
			 $('#manageJurisdiction').hide();
			 $('#studentDetail').hide();
			 $('#manageBlogDiv').hide();
			 $('#teacherDetail').hide();
		});
		 $('#teacherInfoShow').click(function name() {
			 $('#messageList').html("教师信息");
			 $('#teacherInfo').show();
			 $('#studentInfo').hide();
			 $('#studentLog').hide();
			 $('#feedbackDiv').hide();
			 $('#teacherLog').hide();
			 $('#crud').hide();
			 $('#manageJurisdiction').hide();
			 $('#manageBlogDiv').hide();
			 $('#studentDetail').hide();
			 $('#teacherDetail').hide();
		});
		 $('#teacherLogShow').click(function name() {
			 $('#teacherLog').show();
			 $('#messageList').html("操作日志");
			 $('#studentInfo').hide();
			 $('#studentLog').hide();
			 $('#manageBlogDiv').hide();
			 $('#teacherInfo').hide();
			 $('#feedbackDiv').hide();
			 $('#crud').hide();
			 $('#manageJurisdiction').hide();
			 $('#studentDetail').hide();
			 $('#teacherDetail').hide();
		});
		 $('#crudShow').click(function name() {
			 $('#crud').show();
			 $('#messageList').html("CRUD管理");
			 $('#studentInfo').hide();
			 $('#studentLog').hide();
			 $('#teacherInfo').hide();
			 $('#feedbackDiv').hide();
			 $('#manageBlogDiv').hide();
			 $('#teacherLog').hide();
			 $('#manageJurisdiction').hide();
			 $('#studentDetail').hide();
			 $('#teacherDetail').hide();
		});
		 $('#manageJurisdictionShow').click(function name() {
			 $('#manageJurisdiction').show();
			 $('#messageList').html("权限管理");
			 $('#studentInfo').hide();
			 $('#studentLog').hide();
			 $('#feedbackDiv').hide();
			 $('#teacherInfo').hide();
			 $('#manageBlogDiv').hide();
			 $('#teacherLog').hide();
			 $('#crud').hide();
			 $('#studentDetail').hide();
			 $('#teacherDetail').hide();
		});
		 $('#manageBlogShow').click(function name() {
			 $('#messageList').html("管理博客");
			 $('#manageJurisdiction').hide();
			 $('#studentInfo').hide();
			 $('#studentLog').hide();
			 $('#teacherInfo').hide();
			 $('#feedbackDiv').hide();
			 $('#teacherLog').hide();
			 $('#crud').hide();
			 $('#studentDetail').hide();
			 $('#manageBlogDiv').show();
 		});
// 		 $('#manageBlog').click(function name() {
// 			 $('#messageList').html("发布公告");
// 			 $('#studentInfo').hide();
// 		});
	 });
		//清理redis功能
		function clearRedis() {
		 	$.ajax({
	         	type: "GET",
	         	data: {
	        	},
	         	contentType: "application/json; charset=utf-8",
	         	dataType: "json",
	         	async: false,
	         	url: "<%=request.getContextPath()%>/admin/clearRedis.do",
					success : function(data) {
						if (data.result == true) {
							layui.use('layer', function(){
				 	               var $ = layui.jquery, layer = layui.layer; 
				   			      layer.open({
				   			        type: 1
				   			        ,offset: 'auto'
				   			        ,id: 'layerDemo'+'auto'
				   			        ,title: '提示'
				   			        ,content: '<div style="padding: 20px 100px;">'+ "清理成功" +'</div>'
				   			        ,btn: '关闭'
				   			        ,btnAlign: 'c'
				   			        ,skin: 'demo-class'
				   			        ,shade: 0 
				   			        ,yes: function(){
				   			        	 layer.closeAll();
				   			        }
				   			      });
				 	            });
						} else {
							alert("清理出现问题");
						}
					},
					error : function(data) {
						alert("服务器异常！");
					},
				});
		};
		 	 
	 //学生详细信息
	    function studentDetailShow(id){
		 $('#studentDetail').show();
		 $('#messageList').html("学生详细信息");
		 $('#studentLog').hide();
		 $('#teacherInfo').hide();
		 $('#teacherLog').hide();
		 $('#crud').hide();
		 $('#manageJurisdiction').hide();
		 $('#feedbackDiv').hide();
		 $('#manageBlogDiv').hide();
		 $('#studentInfo').hide();
		 $('#teacherDetail').hide();
		 
		 	$.ajax({
	         	type: "GET",
	         	data: {
	         		"studentRoNo":id
	        	},
	         	contentType: "application/json; charset=utf-8",
	         	dataType: "json",
	         	async: false,
	         	url: "<%=request.getContextPath()%>/student/confirmExitsStudent.do",
					success : function(data) {
						if(data.result == false){
							$('#studentRoNoInfo').html(data.student.studentRoNo);
							$('#studentNameInfo').html(data.student.studentName);
							$('#studentPhotoInfo').attr('src','/ClassManageSys/studentPhoto/'+data.student.studentPhoto); 
							$('#studentMobileInfo').html(data.student.studentMobile);
							$('#studentGenderInfo').html(data.student.studentGender);
							$('#studentEmailInfo').html(data.student.studentEmail);
							$('#birthDayInfo').html(data.student.birthDay);
							$('#schoolRecordInfo').html(data.student.schoolRecord);
							$('#intoSchoolYearInfo').html(data.student.intoSchoolYear);
							$('#collegeInfo').html(data.student.college);
							$('#specialInfo').html(data.student.special);
							$('#freeStyleInfo').html(data.student.freeStyle);
						}
					},
					error : function(data) {
						alert("服务器异常！");
					},
				});
	 }
	 
	    //教师详细信息
function teacherDetailShow(id){
	 $('#teacherDetail').show();
	 $('#messageList').html("教师详细信息");
	 $('#studentLog').hide();
	 $('#teacherInfo').hide();
	 $('#teacherLog').hide();
	 $('#manageBlogDiv').hide();
	 $('#feedbackDiv').hide();
	 $('#crud').hide();
	 $('#manageJurisdiction').hide();
	 $('#studentInfo').hide();
	 $('#studentDetail').hide();
	 
 	$.ajax({
        	type: "GET",
        	data: {
        		"teacherMobile":id
       	},
        	contentType: "application/json; charset=utf-8",
        	dataType: "json",
        	async: false,
        	url: "<%=request.getContextPath()%>/teacher/confirmExitsTeacher.do",
			success : function(data) {
				if(data.result == false){
					$('#teacherNameInfo').html(data.teacher.teacherName);
					$('#teacherMobileInfo').html(data.teacher.teacherMobile);
					$('#teacherGenderInfo').html(data.teacher.teacherGender);
					$('#teacherEmailInfo').html(data.teacher.teacherEmail);
					$('#birthDayInfo').html(data.teacher.birthDay);
					$('#schoolRecordInfo').html(data.teacher.schoolRecord);
					$('#collegeInfo').html(data.teacher.college);
					$('#specialInfo').html(data.teacher.special);
					$('#freeStyleInfo').html(data.teacher.freeStyle);
				}
			},
			error : function(data) {
				alert("服务器异常！");
			},
		});
}	 
//查看反馈信息
function feedbackDShow() {
	 $('#manageJurisdiction').hide();
	 $('#messageList').html("反馈处理");
	 $('#studentInfo').hide();
	 $('#studentLog').hide();
	 $('#teacherInfo').hide();
	 $('#manageBlogDiv').hide();
	 $('#teacherLog').hide();
	 $('#crud').hide();
	 $('#studentDetail').hide();
	 $('#teacherDetail').hide();
	$('#feedbackDiv').show();
}
</script>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<!-- 头部导航 -->
		<div class="layui-header header header-demo">
			<div class="layui-main">
				<a class="CMSlogo" href="/"><span
					style="color: white; font-size: 25px;">CMS</span></a>

				<ul class="layui-nav">
					<li class="layui-nav-item"><a id="checkCourseShow2" href="#">
							<i class="layui-icon bbbbb"
							style="font-size: 20px; color: #d2d2d2">&#xe68e;</i>
					</a></li>
					<li class="layui-nav-item"><a id="messageButtton" href="#">
							<i class="layui-icon bbbbb"
							style="font-size: 20px; color: #d2d2d2">&#xe63a;</i><span
							id="TmessageCount" class="layui-badge">2</span>
					</a></li>
					<li class="layui-nav-item"><a id="teacherInfoCenter" href="#">
							<i class="layui-icon bbbbb"
							style="font-size: 20px; color: #d2d2d2">&#xe612;</i><span
							id="redSignal" style="display: none;" class="layui-badge-dot"></span>
					</a></li>
					<li class="layui-nav-item"><a href="#">超管</a>
						<dl class="layui-nav-child">
							<dd>
								<a id="changeTeacherInfoh" href="#">修改信息</a>
							</dd>
							<dd>
								<a href="#" id="safeManage">安全管理</a>
							</dd>
							<dd>
								<a href="<%=request.getContextPath()%>/index.jsp">注销</a>
							</dd>
						</dl></li>
				</ul>

			</div>
		</div>
		<!-- 左侧垂直导航 -->
		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
					<li class="layui-nav-item layui-nav-itemed"><a
						href="javascript:;">学生管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a id="checkStudentShow" href="#">学生信息</a>
							</dd>
							<dd>
								<a id="studentLogShow" href="#">操作日志</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item layui-nav-itemed"><a href="#">教师管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a id="teacherInfoShow" href="#">教师信息</a>
							</dd>
							<dd>
								<a id="teacherLogShow" href="#">操作日志</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="#">资料管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a id="crudShow" href="#">CRUD管理</a>
							</dd>
							<dd>
								<a id="manageJurisdictionShow" href="#">权限管理</a>
							</dd>
							<dd>
								<a onclick="clearRedis()" href="#">清理redis</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a id="manageBlogShow" href="#">博文审核</a></li>
					<li class="layui-nav-item"><a id="manageAnnouncementShow"
						href="#">发布公告</a></li>
					<li class="layui-nav-item"><a onclick="feedbackDShow()"
						href="#">反馈处理</a></li>
				</ul>
			</div>
		</div>

		<!-- 内容显示 -->
		<div class="layui-body site-demo"
			style="padding-top: 3%; overflow: auto;">
			<span id="messageList"
				style="margin-left: 5%; color: #c2c2c2; font-style: oblique;">学生信息</span>
			<hr class="layui-bg-cyan">
			
			
			<!-- 反馈 -->
			<div id="feedbackDiv" class="site-text site-block" style="padding-left: 0;padding-right: 0;display: none;">
			<ul id="feedbackUl" style="width: 100%; margin-top: 10px; margin-left: 0;margin-right: 0;padding-left: 0;padding-right: 0;padding-top: 0;">
		      <c:choose>
		        <c:when test="${!empty feedbacks}">
		          <c:forEach  items="${feedbacks}" var="s"  varStatus="status" begin="0">
		            <li  class="site-block" style="background-color: #eeeeee;margin-top: 0;">
		              (${status.index}).<a target='_blank' href="">${s.feedbackContent}</a>&nbsp;&nbsp;
		             <a href="#" id="${s.feedbackId}" onclick="beforResolveFeedback(this.id)"><span id="F${s.feedbackId}" style="color: #FF5722">${s.result}</span></a>
		            </li>
		            <input type="text" id="C${s.feedbackId}" value="${s.reson}" readonly="readonly" style="display: none;"/>
		          </c:forEach>
		        </c:when>
		        <c:otherwise>
		           <li  class="site-block" style="background-color: #dddddd;">暂无</li>
		        </c:otherwise>
		      </c:choose>
		    </ul>
			</div>
			
			<script type="text/javascript">
			  function beforResolveFeedback(id) {
				  if($('#F' + id).html() == "未解决"){
					  layui.use('layer', function(){
		 	          var $ = layui.jquery, layer = layui.layer; 
					  layer.open({
				            type: 1
				            ,title: false //不显示标题栏
				            ,closeBtn: true
				            ,area: '300px;'
				            ,shade: 0.8
				            ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
				            ,btn: ['提交处理', '暂时忽略']
				            ,btnAlign: 'c'
				            ,moveType: 0 //拖拽模式，0或者1
				            ,content: '<div style="background-color: #393D49; color: #fff;"><div  style="width:100%;"> <label>解决方法</label><textarea style="color:#393D49" id="returnInfoToAdmin"  placeholder="针对反馈的解决方案" class="layui-textarea"></textarea></div></div>'
				            ,yes: function(){
				            	resolveFeedback(id);
							  }
				          });
					  });
				  }else {
					  layui.use('layer', function(){
		 	               var $ = layui.jquery, layer = layui.layer; 
		   			      layer.open({
		   			        type: 1
		   			        ,offset: 'auto'
		   			        ,id: 'layerDemo'+'auto'
		   			        ,title: '处理明细'
		   			        ,content: '<div style="padding: 20px 100px; overflow:auto; color:#FF5722">'+ $('#C'+id).val() +'</div>'
		   			        ,btn: '关闭'
		   			        ,btnAlign: 'c'
		   			        ,skin: 'demo-class'
		   			        ,shade: 0 
		   			        ,yes: function(){
		   			         //window.location.reload();
		   			        layer.closeAll();
		   			        }
		   			      });
		 	            });
				}
			}
			  function resolveFeedback(id) {
				  if($('#returnInfoToAdmin').val() != ""){
					  $.ajax({
			              type: "GET",
			              data: {
			                  "feedbackId":id,
			                  "reson":$('#returnInfoToAdmin').val()
			              },
			              contentType: "application/json; charset=utf-8",
			              async: false,
			              dataType: "json",
			              url: "<%=request.getContextPath()%>/admin/resolveFeedback.do",
			              success: function (data) {
			            	  if(data.result == true){
			            		  layer.msg("处理成功");
			            		  $('#F'+id).html("已处理");
			            	  }
			              },
			              error: function (data) {
			            	  
			              }
			          });
					 
				  }else {
					  layer.msg("方案内容不可为空！");
				}
				 
			}
			 </script>
			
			
			<!-- 博客管理 -->
			<div id="manageBlogDiv" class="site-text site-block" style="padding-left: 0;padding-right: 0;display: none;">
				<table class="layui-table" lay-skin="line" lay-even style="text-align: center; width: 100%;">
					<colgroup>
						<col width="200">
						<col width="200">
						<col width="200">
						<col width="200">
						<col width="200">
						<col width="200">
						<col width="200">
						<col width="200">
					</colgroup>
					<thead>
						<tr>
							<th style="text-align: center;">批量处理</th>
							<th style="text-align: center;">作者</th>
							<th style="text-align: center;">角色</th>
							<th style="text-align: center;">标题</th>
							<th style="text-align: center;">类别</th>
							<th style="text-align: center;">权限</th>
							<th style="text-align: center;">时间</th>
							<th style="text-align: center;">查验</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${! empty myBlogs}">
								<c:forEach items="${myBlogs}" var="s">
									<tr id="T${s.blogId}">
										<td> <input type="checkbox" name="like[write]"></td>
										<td>${s.blogAuthor}</td>
										<td>${s.role}</td>
										<td>${s.blogTitle}</td>
										<td>${s.belongTo}</td>
										<td>${s.blogFifter}</td>
										<td>${s.createTime}</td>
										<td><a id="${s.blogId}" onclick="getBlog(this.id)" href="#"><i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe63c;</i>
										</a></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="9">(暂无待审博文)</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			
			<script type="text/javascript">
			function getBlog(id) {
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
		            	  layer.open({
		                      type: 1
		                      ,title: false //不显示标题栏
		                      ,closeBtn: true
		                      ,area: '800px;'
		                      ,shade: 0.8
		                      ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
		                      ,btn: ['通过审核', '残忍拒绝']
		                      ,btnAlign: 'c'
		                      ,moveType: 0 //拖拽模式，0或者1
		                      ,content: '<div style="background-color: #393D49; color: #fff;"><div  style="width:100%;"> <label>待审博文<i class="layui-icon" style="font-size: 13px; color: white;">&#xe623;</i>  </label><br/><br/><div style="heigh:500px; overflow: auto;"><h3>作者:'+data.userName+'<h3><br/><h3>标题:'+data.myBlog.blogTitle+'<h3><br/>'+data.myBlog.blogContent+'</div></div></div>'
		                      ,yes: function(){
		                    	  pass(id); 
		          			  }
		            	    ,btn2: function(index, layero){
		            		    nopass(id);
		            		  }
		                    });
		              },
		              error: function (data) {
		            	  alert("服务器异常");
		              }
		          });
			}
			function pass(id) {
				$.ajax({
		              type: "GET",
		              data: {
		            	  "blogId":id
		              },
		              contentType: "application/json; charset=utf-8",
		              async: false,
		              dataType: "json",
		              url: "<%=request.getContextPath()%>/admin/updateBlogofVerify.do",
		              success: function (data) {
		            	  if(data.result == true){
		            		  layui.use('layer', function(){
				 	               var $ = layui.jquery, layer = layui.layer; 
				   			      layer.open({
				   			        type: 1
				   			        ,offset: 'auto'
				   			        ,id: 'layerDemo'+'auto'
				   			        ,title: '成功'
				   			        ,content: '<div style="padding: 20px 100px; color:#FF5722">'+ "审核通过" +'</div>'
				   			        ,btn: '关闭'
				   			        ,btnAlign: 'c'
				   			        ,skin: 'demo-class'
				   			        ,shade: 0 
				   			        ,yes: function(){
				   			        	$('#T'+id).hide();
				   			        	 layer.closeAll();
				   			        }
				   			      });
				 	            });
		            	  }else {
		            		  layer.msg('审核失败');
						}
		            	  
		              },
		              error: function (data) {
		            	  alert("服务器异常");
		              }
		          });
			}
			function nopass(id){
				$.ajax({
		              type: "GET",
		              data: {
		            	  "blogId":id
		              },
		              contentType: "application/json; charset=utf-8",
		              async: false,
		              dataType: "json",
		              url: "<%=request.getContextPath()%>/admin/updateBlogofVerifySecond.do",
		              success: function (data) {
		            	  if(data.result == true){
		            		  layui.use('layer', function(){
				 	               var $ = layui.jquery, layer = layui.layer; 
				   			      layer.open({
				   			        type: 1
				   			        ,offset: 'auto'
				   			        ,id: 'layerDemo'+'auto'
				   			        ,title: '成功'
				   			        ,content: '<div style="padding: 20px 100px; color:#FF5722">'+ "审核修订为未通过" +'</div>'
				   			        ,btn: '关闭'
				   			        ,btnAlign: 'c'
				   			        ,skin: 'demo-class'
				   			        ,shade: 0 
				   			        ,yes: function(){
				   			        	$('#T'+id).hide();
				   			        	 layer.closeAll();
				   			        }
				   			      });
				 	            });
		            	  }else {
		            		  layer.msg('审核失败');
						}
		            	  
		              },
		              error: function (data) {
		            	  alert("服务器异常");
		              }
		          });
			}
			</script>

			<!-- 学生信息模块 -->
			<div id="studentInfo" class="site-text site-block" style="padding-left: 0;padding-right: 0">
				<table class="layui-table" lay-even style="text-align: center; width: 100%;">
					<colgroup>
						<col width="200">
						<col width="200">
						<col width="200">
						<col width="200">
						<col width="200">
						<col width="200">
					</colgroup>
					<thead>
						<tr>
							<th style="text-align: center;">账号</th>
							<th style="text-align: center;">姓名</th>
							<th style="text-align: center;">性别</th>
							<th style="text-align: center;">手机</th>
							<th style="text-align: center;">邮箱</th>
							<th style="text-align: center;">详细</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${! empty students}">
								<c:forEach items="${students}" var="s">
									<tr>
										<td>${s.studentRoNo}</td>
										<td>${s.studentName}</td>
										<td>${s.studentGender}</td>
										<td>${s.studentMobile}</td>
										<td>${s.studentEmail}</td>
										<td><a id="${s.studentRoNo}" onclick="studentDetailShow(this.id)" href="#"><i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe63c;</i>
										</a></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="9">(暂无学生)</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			
			<!-- 学生详细信息模块 -->
			<div id="studentDetail" class="site-text site-block" style="display: none;text-align: center;padding-left: 0;padding-right: 0">
				<table class="layui-table">
					<tr>
						<th style="color: #5FB878;text-align: center;">学号</th>
						<td id="studentRoNoInfo"></td>
						<th style="color: #5FB878;text-align: center;">姓名</th>
						<td colspan="2" id="studentNameInfo"></td>
						
						<td rowspan="5" style="padding-left: 0;padding-right: 0;"><img id="studentPhotoInfo" lay-src="" width="100%" style="width: 100px; heigh: 120px;"/></td>
					</tr>
					<tr>
						<th style="color: #5FB878;text-align: center;">性别</th>
						<td id="studentGenderInfo"></td>
						<th style="color: #5FB878;text-align: center;">手机</th>
						<td colspan="2" id="studentMobileInfo"></td>
					</tr>
					<tr>
						<th style="color: #5FB878;text-align: center;">邮箱</th>
						<td id="studentEmailInfo"></td>
						<th style="color: #5FB878;text-align: center;">出生日期</th>
						<td colspan="2" id="birthDayInfo"></td>
					</tr>
					<tr>
						<th style="color: #5FB878;text-align: center;">学历</th>
						<td id="schoolRecordInfo"></td>
						<th style="color: #5FB878;text-align: center;">入学时间</th>
						<td colspan="2" id="intoSchoolYearInfo"></td>
							
					</tr>
					<tr>
						<th style="color: #5FB878;text-align: center;">学院</th>
						<td id="collegeInfo"></td>
						<th style="color: #5FB878;text-align: center;">专业</th>
						<td id="specialInfo"></td>
						
					</tr>
					<tr>
						<th style="color: #5FB878;text-align: center;">freestyle</th>
						<td colspan="5" id="freeStyleInfo"></td>
					</tr>
				</table>
			</div>
			<script type="text/javascript">
			layui.use('flow', function(){
				  var flow = layui.flow;
				  //当你执行这样一个方法时，即对页面中的全部带有lay-src的img元素开启了懒加载（当然你也可以指定相关img）
				  flow.lazyimg({
					  elem:'#studentDetail'
				  }); 
				});
			
			</script>

			<!-- 学生操作日志模块 -->
			<div id="studentLog" class="site-text site-block" style="display: none; text-align: center;padding-left: 0;padding-right: 0">
				<table class="layui-table" lay-even>
					<colgroup>
						<col width="150">
						<col width="300">
						<col width="200">
						<col width="200">
						<col width="150">
					</colgroup>
					<thead>
						<tr>
							<th style="text-align: center;">学号</th>
							<th style="text-align: center;">活动</th>
							<th style="text-align: center;">响应时间</th>
							<th style="text-align: center;">Ip</th>
							<th style="text-align: center;">时间</th>
							<th style="text-align: center;">结果</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${! empty studentLogEntitys}">
								<c:forEach items="${studentLogEntitys}" var="sl">
									<tr>
										<td>${sl.userId}</td>
										<td style="text-align: left; padding-left: 10%;">${sl.method}</td>
										<td>${sl.reponseTime}</td>
										<td>${sl.ip}</td>
										<td>${sl.date}</td>
										<td>${sl.result}</td>
										<!--  <td colspan="8"></td>-->
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="6">(暂无信息)</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>

			<!-- 教师信息模块 -->
			<div id="teacherInfo" class="site-text site-block" style="display: none;padding-left: 0;padding-right: 0">
				<table class="layui-table" style="text-align: center;">
					<colgroup>
						<col width="130">
						<col width="150">
						<col width="100">
						<col width="130">
						<col width="200">
						<col width="200">
						<col width="120">
					</colgroup>
					<thead>
						<tr>
							<th style="text-align: center;">账号</th>
							<th style="text-align: center;">姓名</th>
							<th style="text-align: center;">性别</th>
							<th style="text-align: center;">手机</th>
							<th style="text-align: center;">邮箱</th>
							<th style="text-align: center;">学科</th>
							<th style="text-align: center;">详细</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${! empty teachers}">
								<c:forEach items="${teachers}" var="t">
									<tr>
										<td>${t.teacherMobile}</td>
										<td>${t.teacherName}</td>
										<td>${t.teacherGender}</td>
										<td>${t.teacherMobile}</td>
										<td>${t.teacherEmail}</td>
										<td>${t.teacherSubject}</td>
										<td><a id="${t.teacherMobile}" onclick="teacherDetailShow(this.id)" href="#"><i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe63c;</i>
										</a></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="9">(暂无教师)</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<!-- 教师详细信息模块 -->
			<div id="teacherDetail" class="site-text site-block" style="display: none;padding-left: 0;padding-right: 0">
				<table class="layui-table">
					<tr>
						<th style="color: #5FB878;text-align: center;">账号</th>
						<td id="teacherMobileInfo"></td>
						<th style="color: #5FB878;text-align: center;">姓名</th>
						<td id="teacherNameInfo" colspan="2"></td>
					</tr>
					<tr>
						<th style="color: #5FB878;text-align: center;">性别</th>
						<td id="teacherGenderInfo"></td>
						<th style="color: #5FB878;text-align: center;">邮箱</th>
						<td id="teacherEmailInfo" colspan="2"></td>
					</tr>
					<tr>
						<th style="color: #5FB878;text-align: center;">出生日期</th>
						<td id="birthDayInfo"></td>
						<th style="color: #5FB878;text-align: center;">学历</th>
						<td id="schoolRecordInfo" colspan="2"></td>
					</tr>
					<tr>
						<th style="color: #5FB878;text-align: center;">学院</th>
						<td id="collegeInfo"></td>
						<th style="color: #5FB878;text-align: center;">学科</th>
						<td id="specialInfo" colspan="2"></td>
					</tr>
					<tr>
						<th style="color: #5FB878;text-align: center;">备注</th>
						<td id="freeStyleInfo" colspan="5"></td>
					</tr>
				</table>
			</div>

			<!-- 教师操作日志模块 -->
			<div id="teacherLog" class="site-text site-block" style="display: none; padding-left: 0;padding-right: 0">
				<table class="layui-table" lay-even>
					<colgroup>
						<col width="150">
						<col width="300">
						<col width="200">
						<col width="200">
						<col width="150">
					</colgroup>
					<thead>
						<tr>
							<th style="text-align: center;">账号</th>
							<th style="text-align: center;">活动</th>
							<th style="text-align: center;">响应时间</th>
							<th style="text-align: center;">Ip</th>
							<th style="text-align: center;">时间</th>
							<th style="text-align: center;">结果</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${! empty teacherLogEntitys}">
								<c:forEach items="${teacherLogEntitys}" var="tl">
									<tr>
										<td>${tl.userId}</td>
										<td style="text-align: left; padding-left: 10%;">${tl.method}</td>
										<td>${tl.reponseTime}</td>
										<td>${tl.ip}</td>
										<td>${tl.date}</td>
										<td>${tl.result}</td>
										<!--  <td colspan="8"></td>-->
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="6">(暂无信息)</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>

			<!-- CRUD管理模块 -->
			<div id="crud" class="layui-tab layui-tab-card" style="display: none;text-align: center; margin: 3% 10%">
				<ul class="layui-tab-title">
   					<li class="layui-this">增加学生</li>
   					<li>增加教师</li>
    				<li>删</li>
   					<li>查</li>
    				<li>改</li>
  				</ul>
  				<div class="layui-tab-content" style="height: 500px;">
   					<div class="layui-tab-item layui-show">
   						<form class="layui-form">
   							<div class="layui-form-item">
   								<label class="layui-form-label">增个屁吧</label>
    							<div class="layui-input-block">
     								<input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
    							</div>
  							</div>
   						</form>
   					</div>
    				<div class="layui-tab-item">2</div>
    				<div class="layui-tab-item">3</div>
    				<div class="layui-tab-item">
    					<form class="layui-form">
   							<div class="layui-form-item">
   								<label class="layui-form-label">按账号搜索</label>
    							<div class="layui-input-block">
     								<input type="text" name="title" required  lay-verify="required" placeholder="请输入账号" autocomplete="off" class="layui-input">
    							</div>
  							</div>
  							<div class="layui-form-item">
   								<label class="layui-form-label">按姓名搜索</label>
    							<div class="layui-input-block">
     								<input type="text" name="title" required  lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
    							</div>
  							</div>
  							<div class="layui-form-item">
    							<div class="layui-input-block">
      								<button class="layui-btn" lay-submit lay-filter="formDemo">搜索</button>
      								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
    							</div>
   						</form>
    				</div>
    				<div class="layui-tab-item">4</div>
  				</div>
			</div>

			<!-- 权限管理模块 -->
			<div id="manageAnnouncement" class="site-text site-block"
				style="display: none;"></div>

		</div>
	</div>
	<script type="text/javascript">
		layui.use([ 'element', 'layer', 'table' ,'form'],function() {
			var element = layui.element
			,$ = layui.jquery
			,layer = layui.layer
			,table = layui.table
			,form = layui.form;
		});
	</script>
</body>
</html>