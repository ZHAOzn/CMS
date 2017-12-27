<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta charset="utf-8"> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/icon/cms2.ico" media="screen" />
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="css/mycss.css">
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/teacherPage.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/layui.css">
<script src="<%=request.getContextPath()%>/layui/layui.js "></script>

<script type="text/javascript">
function fresh(){  
if(location.href.indexOf("?reload=true")<0){
	$('#teacherMobile').val("");
    location.href+="?reload=true";  
   }  
}  
setTimeout("fresh()",1)
	$(document).ready(function() {
		$('#lll').click(function() {
			$('#examInner').toggle();
			$('#loginForm').toggle();
		});
		$('#sss').click(function() {
			$('#sss').css("background-color", "rgba(0,0,0,1)");
			$('#ttt').css("background-color", "rgba(0,0,0,0)");
			$('#studentForm').show();
			$('#teacherForm').hide();
		});
		$('#ttt').click(function() {
			$('#sss').css("background-color", "rgba(0,0,0,0)");
			$('#ttt').css("background-color", "rgba(0,0,0,1)");
			$('#studentForm').hide();
			$('#teacherForm').show();
		});
		//改变studentRoNo时通过ajax去后台查询是否数据库中存在此学号，存在即开放密码
		$('#studentRono').change(function whenChange() {
			var studentRono = $('#studentRono').val();
			if(trySubmit(studentRono)){
				layui.use('layer', function(){
	  	               var $ = layui.jquery, layer = layui.layer; 
	    			      layer.open({
	    			        type: 1
	    			        ,offset: 'auto' 
	    			        ,id: 'layerDemo'+'auto' 
	    			        ,title: '失败'
	    			        ,content: '<div style="padding: 20px 100px;">'+ "学号不存在~" +'</div>'
	    			        ,btn: '关闭'
	    			        ,btnAlign: 'c' 
	    			        ,shade: 0 
	    			        ,yes: function(){
	    			        	 layer.closeAll();
	    			        }
	    			      });
	  	            });
				$('#stpw').attr("disabled","disabled"); 
				$('#stu').attr("disabled","disabled"); 
			}else {
				$('#stpw').removeAttr("disabled");
				$('#stu').removeAttr("disabled");
			}
		});
		
		function trySubmit(studentRono) {
			var result = false;
			$.ajax({
	              type: "GET",
	              data: {
	                  "studentRoNo": studentRono
	              },
	              contentType: "application/json; charset=utf-8",
	              async: false,
	              dataType: "json",
	              url: "<%=request.getContextPath() %>/student/confirmExitsStudent.do",
	              success: function (data) {
	            	  if(data.result == true){
	            		  result = true;
	            	  }
	              },
	              error: function (data) {
	            	  
	              }
	          });
			  return result;
		}
		
		$('#teacherMobile').change(function whenChange() {
			var teacherMobile = $('#teacherMobile').val();
			if(tryLogin(teacherMobile)){
				layui.use('layer', function(){
	  	               var $ = layui.jquery, layer = layui.layer; 
	    			      layer.open({
	    			        type: 1
	    			        ,offset: 'auto' 
	    			        ,id: 'layerDemo'+'auto' 
	    			        ,title: '失败'
	    			        ,content: '<div style="padding: 20px 100px;">'+ "账号不存在~" +'</div>'
	    			        ,btn: '关闭'
	    			        ,btnAlign: 'c' 
	    			        ,shade: 0 
	    			        ,yes: function(){
	    			        	 layer.closeAll();
	    			        }
	    			      });
	  	            });
				$('#tcpw').attr("disabled","disabled"); 
				$('#tea').attr("disabled","disabled"); 
			}else {
				$('#tcpw').removeAttr("disabled");
				$('#tea').removeAttr("disabled");
			}
		});
		
		function tryLogin(teacherMobile) {
			  var result = false;
			$.ajax({
	              type: "GET",
	              data: {
	                  "teacherMobile": teacherMobile
	              },
	              contentType: "application/json; charset=utf-8",
	              async: false,
	              dataType: "json",
	              url: "<%=request.getContextPath() %>/teacher/confirmExitsTeacher.do",
	              success: function (data) {
	            	  if(data.result == true){
	            		  result = true;
	            	  }
	              },
	              error: function (data) {
	            	  
	              }
	          });
			  return result;
		}
		//获取教师密码，进行后台比对
		function getAnwser(password,teacherMobile) {
			var result = false;
			$.ajax({
	              type: "GET",
	              data: {
	                  "password": password,
	                  "teacherMobile":teacherMobile
	              },
	              contentType: "application/json; charset=utf-8",
	              async: false,
	              dataType: "json",
	              url: "<%=request.getContextPath() %>/teacher/confirmTeacherPassWord.do",
//	              beforeSend:function(){$("#href").html("等待..");},
	              success: function (data) {
	            	  if(data.result == true){
	            		  result = true;
	            	  }
	              },
	              error: function (data) {
	            	  
	              }
	          });
			  return result;
		}
		//点击教师登录触发密码验证
		$('#tea').click(function wannaSubmit() {
			var password = $('#tcpw').val();
			var teacherMobile = $('#teacherMobile').val();
			if(getAnwser(password,teacherMobile)){
				$('#teacherForm').submit();
			}else {
				layui.use('layer', function(){
	  	               var $ = layui.jquery, layer = layui.layer; 
	    			      layer.open({
	    			        type: 1
	    			        ,offset: 'auto' 
	    			        ,id: 'layerDemo'+'auto' 
	    			        ,title: '失败'
	    			        ,content: '<div style="padding: 20px 100px;">'+ "密码错误~" +'</div>'
	    			        ,btn: '关闭'
	    			        ,btnAlign: 'c' 
	    			        ,shade: 0 
	    			        ,yes: function(){
	    			        	 layer.closeAll();
	    			        }
	    			      });
	  	            });
			}
		});
		
		
		//获取学生密码，进行后台比对
		function getStuAnwser(password,studentRono) {
			var result = false;
			$.ajax({
	              type: "GET",
	              data: {
	                  "password": password,
	                  "studentRono":studentRono
	              },
	              contentType: "application/json; charset=utf-8",
	              async: false,
	              dataType: "json",
	              url: "<%=request.getContextPath() %>/student/confirmStudentPassWord.do",
//	              beforeSend:function(){$("#href").html("等待..");},
	              success: function (data) {
	            	  if(data.result == true){
	            		  result = true;
	            	  }
	              },
	              error: function (data) {
	            	  
	              }
	          });
			  return result;
		}
		//点击学生登录触发密码验证
		$('#stu').click(function wannaSubmit() {
			var password = $('#stpw').val();
			var studentRono = $('#studentRono').val();
			if(getStuAnwser(password,studentRono)){
				$('#studentForm').submit();
			}else {
				layui.use('layer', function(){
	  	               var $ = layui.jquery, layer = layui.layer; 
	    			      layer.open({
	    			        type: 1
	    			        ,offset: 'auto' 
	    			        ,id: 'layerDemo'+'auto' 
	    			        ,title: '失败'
	    			        ,content: '<div style="padding: 20px 100px;">'+ "密码错误~" +'</div>'
	    			        ,btn: '关闭'
	    			        ,btnAlign: 'c' 
	    			        ,shade: 0 
	    			        ,yes: function(){
	    			        	 layer.closeAll();
	    			        }
	    			      });
	  	            });
			}
		});
	});
	function studentClick() {
		$('#sss').css('border-bottom','1px solid #009688');
		$('#ttt').css({border:"none"});
	}
	function teacherClick() {
		$('#sss').css({border:"none"});
		$('#ttt').css('border-bottom','1px solid #009688');
	}
</script>
</head>

<body
	style="background-image: url('lib/timg (1).jpg'); background-repeat: no-repeat; background-size: cover;">


	<div style="width: 100%; height: 1000px;" class="layui-layout layui-layout-admin">
		
		<!-- 导航1号 -->
		<div class="layui-header header header-demo" style="background-color: #222; width: 100%; position: fixed;">
			
			
			<div class="layui-main">
				<a class="CMSlogo-FirstPage" href="#"><span
					style="color: white; font-size: 25px;">CMS</span></a>
				
				<!-- 登录form模块 -->
				<div id="loginForm" 
					style="width: 30%; higth: 100%; margin-top: 170px; background-color: rgba(0, 0, 0, 0.7); position: fixed; margin-left: 35%; display: none;">
					<a href="#" id="sss" class="alogin" style="float: left; text-decoration: none; color: white; font-size: 28px; 
					background-color: rgba(0, 0, 0, 1); border-bottom: solid;border-color: #009688; border-bottom-width: 1px;" onclick="studentClick()">学生登录</a>
					<a href="#" id="ttt" class="alogin" style="float: left; text-decoration: none; color: white; font-size: 28px;" onclick="teacherClick()">教师登录</a>
					<form id="studentForm" action="<%=request.getContextPath()%>/student/studentLogin.do" method="post" style="text-align: center; font-size: 20px;">
						<br> <br> <br> <br> <span style="color: white">学号:</span>><input 
							name="studentRoNo" id="studentRono" type="text"/><br /> <br /> <span
							style="color: white">密码:</span>><input name="studentPassword" id="stpw" 
							type="password" disabled="disabled" /><br /> <br />
							 <a href="<%=request.getContextPath()%>/student/getStudentPasswordBack.do"
							    class="aChangePW" target="_blank">忘记密码>></a><br/><br/>
							<div style="text-align: center;">
							 	<input id="stu" class="layui-btn" style="width: 120px;" type="button"
								value="登录" /> 
								<a id="stu" href="<%=request.getContextPath()%>/student/addStudent.do"
								target="_blank" class="layui-btn" style="width: 120px;"> 注册</a>
							</div>
							<br>		
					</form>
					
					<form id="teacherForm"
						action="<%=request.getContextPath()%>/teacher/teacherLogin.do"
						method="post"
						style="text-align: center; font-size: 20px; display: none;">
						<input type="text" name="repageNow" value="1" style="display: none;"/>
						<br> <br> <br> <br> <span style="color: white">手机:</span>><input 
							name="teacherId" id="teacherMobile" type="text" /><br /> <br /> <span
							style="color: white">密码:</span>><input name="password" id="tcpw" 
							type="password" /><br /> <br />
						
							 <a href="<%=request.getContextPath()%>/teacher/getTeacherPasswordBack.do" class="aChangePW"
							target="_blank">忘记密码>></a><br/><br/>
							<div style="text-align: center;">
							 	<input id="tea" class="layui-btn" style="width: 120px;" type="button"
								value="登录" /> 
								<a id="stu" href="<%=request.getContextPath()%>/teacher/forTeacherRegister.do"
								target="_blank" class="layui-btn" style="width: 120px;"> 注册</a>
							</div>
							<br>
					</form>
					<br> <br> <br> <br> <br>
				</div>


				<ul class="layui-nav">
					<li class="layui-nav-item">
							<a id="lll" href="#" type="submit" style="width: 100px;font-size: 20px;" >登录</a>
					</li>
				</ul>

			</div>
		</div>
		
        <!-- 考试入口 -->
			<div id="examInner" class="site-text" 
				style="text-align: center; width: 50%;; 
				top: 300px; position: fixed; margin-left: 29%;">
				<form id="ExamForm" class="layui-form layui-form-pane" action="<%=request.getContextPath()%>/exam/studentToExam.do">
					<div class="layui-form-item" style="width: 100%;">
						<div style="width: 100%; float: left;">
							<label class="layui-form-label" style="width: 18%; background-color: #e2e2e2;">输入考试码：</label>
							<div class="layui-input-block">
								<input id="onlyCode" type="text" lay-verify="CoreKey" name="onlyCode"
									placeholder="请输入考试码" autocomplete="off" class="layui-input"
									style="width: 50%; float: left;"> 
								<input class="layui-btn" lay-submit type="button"
									 value="现在考试" style="float: left;" onclick="toExam()"/>
							</div>
						</div>
					</div>
				</form>
			</div>
			<script type="text/javascript">
			//输入考试码考试
			  function toExam() {
					$.ajax({
			              type: "GET",
			              data: {
			                  "onlyCode": $('#onlyCode').val()
			              },
			              contentType: "application/json; charset=utf-8",
			              async: false,
			              dataType: "json",
			              url: "<%=request.getContextPath() %>/exam/confirmExitsExam.do",
			              success: function (data) {
			            	  if(data.result == true){
			            		  if(data.message == "wait"){
			            			  layui.use('layer', function(){ 
					   	  	               var $ = layui.jquery, layer = layui.layer; 
					   	  	             //触发事件
					   	    			      layer.open({
					   	    			        type: 1
					   	    			        ,offset: 'auto'
					   	    			        ,id: 'layerDemo'+'auto' //防止重复弹出
					   	    			        ,title: '提示'
					   	    			        ,content: '<div style="padding: 20px 100px; color:#FF5722">'+ "考试还未开始，请耐心等候" +'</div>'
					   	    			        ,btn: '关闭'
					   	    			        ,btnAlign: 'c' //按钮居中
					   	    			        ,shade: 0 //不显示遮罩
					   	    			        ,yes: function(){
					   	    			        	 layer.closeAll();
					   	    			        }
					   	    			      });
					   	  	            });
			            		  }else if (data.message == "end") {
			            			  layui.use('layer', function(){ 
					   	  	               var $ = layui.jquery, layer = layui.layer; 
					   	  	             //触发事件
					   	    			      layer.open({
					   	    			        type: 1
					   	    			        ,offset: 'auto'
					   	    			        ,id: 'layerDemo'+'auto' //防止重复弹出
					   	    			        ,title: '提示'
					   	    			        ,content: '<div style="padding: 20px 100px; color:#FF5722">'+ "考试已经结束.." +'</div>'
					   	    			        ,btn: '关闭'
					   	    			        ,btnAlign: 'c' //按钮居中
					   	    			        ,shade: 0 //不显示遮罩
					   	    			        ,yes: function(){
					   	    			        	 layer.closeAll();
					   	    			        }
					   	    			      });
					   	  	            });
								}else if (data.message == "can") {
									  $('#ExamForm').submit();
								}
			            	  }else if(data.result == false){
			            		  layui.use('layer', function(){ 
			   	  	               var $ = layui.jquery, layer = layui.layer; 
			   	  	             //触发事件
			   	    			      layer.open({
			   	    			        type: 1
			   	    			        ,offset: 'auto'
			   	    			        ,id: 'layerDemo'+'auto' //防止重复弹出
			   	    			        ,title: '提示'
			   	    			        ,content: '<div style="padding: 20px 100px; color:#FF5722">'+ "考试码错误.." +'</div>'
			   	    			        ,btn: '关闭'
			   	    			        ,btnAlign: 'c' //按钮居中
			   	    			        ,shade: 0 //不显示遮罩
			   	    			        ,yes: function(){
			   	    			        	 layer.closeAll();
			   	    			        }
			   	    			      });
			   	  	            });
							}
			              },
			              error: function (data) {
			            	  
			              }
			          });
			}
			</script>



		<div
			style="background-color: rgba(0, 0, 0, 0.6); height: 100%; background-size: cover;">

		</div>
         
         
         
         <div style="background-color: #222; height: 100px;">
            <div class="navbar-header" style="text-align: center;width: 100%;margin-top:2%;">
				<a style="margin-left: 40%; color: white;" class="navbar-brand" href="#">copyright by CMS team</a>
        	</div>
		</div>
	
	</div>

</body>
</html>