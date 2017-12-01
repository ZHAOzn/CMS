
<%-- 
    Document   : waitForRegister
    Created on : 2017-6-8, 17:33:13
    Author     : Juin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.2.0.min.js"></script>

<title>考试须知</title>
</head>
<body style="background-color: #eeeeee">
	<!-- 头部 -->
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
				<a class="navbar-brand" href="#">CMS考试系统</a>
			</div>
			<form class="navbar-form navbar-right"
				style="margin-right: 5%; float: left;">
				<a id="lllww" style="width: 100px; color: white; font-size: 1.7em;">信息验证</a>
			</form>
		</div>
	</nav>
    <div>
     <br/> <br/> <br/> <br/> <br/> <br/>
    </div>

	<!-- 正文 -->
	<div
		style="heigh: 300px; background-color: white; margin-left: 19%; margin-right: 20%; padding-left: 10%; padding-right: 10%; padding-top: 4%; padding-bottom: 100px;">
		<div id="examInfo">
			<form style="width: 80%;" class="layui-form-pane">
				<div class="layui-form-item">
					<label class="layui-form-label">试卷名称</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input" style="padding-left: 35px;"
							value="${examination.examinationName}" disabled="disabled">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">考试码</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input" style="padding-left: 35px;"
							value="${examination.onlyCode}" disabled="disabled">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">考试时间</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input"
							value="${examination.startTime}" style="padding-left: 35px;"
							disabled="disabled">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">考试时长</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input"
							value="${examination.duration}分钟" style="padding-left: 35px;"
							disabled="disabled">
					</div>
				</div>


				<div style="height: 60px; width: 300px; float: left;">
					<input type="button" style="float: left; margin-left: 4%;"
						class="layui-btn" onclick="nextstep()" value="下一步" /> <input
						type="button" style="float: left; margin-left: 24%;"
						class="layui-btn" onclick="javascript:history.back()" value="返回" />
					<br /> <br />
				</div>
			</form>
		</div>
		<script type="text/javascript">
			function nextstep() {
				$('#lllww').html("考生信息");
				$('#examInfo').hide();
				$('#studentInfo').show();
			}
		</script>

		<!-- 学生信息 -->
		<div id="studentInfo" style="display: none;">
			<form id="ToJoinExamNow" style="width: 80%;" class="layui-form-pane" 
			action="<%=request.getContextPath() %>/exam/ToJoinExamNow.do" method="post">
				<br /> <br />
				<input id="examinationID" name="examinationID" type="text" style="display: none;"/>
				<div class="layui-form-item">
					<label class="layui-form-label">学号</label>
					<div class="layui-input-block">
						<input id="studentRoNo" type="text" name="studentRoNo"
							class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">密码</label>
					<div class="layui-input-block">
						<input id="studentPassword" type="password" name="studentPassword"
							class="layui-input">
					</div>
				</div>

				<div style="height: 60px; width: 300px; float: left;">
					<input type="button" style="float: left; margin-left: 4%;"
						class="layui-btn" onclick="confimStudentInfo()" value="下一步" /> 
					<input type="button" style="float: left; margin-left: 24%;"
						class="layui-btn" onclick="back()" value="返回" /> <br /> <br />
				</div>
			</form>
			<script type="text/javascript">
				function back() {
					$('#lllww').html("信息验证");
					$('#examInfo').show();
					$('#studentInfo').hide();
				}
				</script>
				
				
		   <script type="text/javascript">
				function confimStudentInfo() {
					if($('#studentRoNo').val() != "" && $('#studentPassword').val() != ""){
				
					$.ajax({
			              type: "GET",
			              data: {
			                  "examinationID": ${examination.examinationID},
			                  "studentRoNo":$('#studentRoNo').val(),
			                  "studentPassword":$('#studentPassword').val()
			              },
			              contentType: "application/json; charset=utf-8",
			              async: false,
			              dataType: "json",
			              url: "<%=request.getContextPath() %>/exam/confimStudentInfo.do",
			              success: function (data) {
			            	  if(data.result == "noStudent"){
			            		  layui.use('layer', function(){
					  	               var $ = layui.jquery, layer = layui.layer; 
					    			      layer.open({
					    			        type: 1
					    			        ,offset: 'auto'
					    			        ,id: 'layerDemo'+'auto'
					    			        ,title: '失败'
					    			        ,content: '<div style="padding: 20px 100px; color:#FF5722">'+ "学号错误.." +'</div>'
					    			        ,btn: '关闭'
					    			        ,btnAlign: 'c'
					    			        ,skin: 'demo-class'
					    			        ,shade: 0 
					    			        ,yes: function(){
					    			        	 layer.closeAll();
					    			        }
					    			      });
					  	            });
			            	  }else if (data.result == "passwordError") {
			            		  layui.use('layer', function(){
					  	               var $ = layui.jquery, layer = layui.layer; 
					    			      layer.open({
					    			        type: 1
					    			        ,offset: 'auto'
					    			        ,id: 'layerDemo'+'auto'
					    			        ,title: '错误'
					    			        ,content: '<div style="padding: 20px 100px; color:#FF5722">'+ "密码错误.." +'</div>'
					    			        ,btn: '关闭'
					    			        ,btnAlign: 'c'
					    			        ,skin: 'demo-class'
					    			        ,shade: 0 
					    			        ,yes: function(){
					    			        	 layer.closeAll();
					    			        }
					    			      });
					  	            });
							}else if (data.result == "notInCourse") {
								layui.use('layer', function(){
					  	               var $ = layui.jquery, layer = layui.layer; 
					    			      layer.open({
					    			        type: 1
					    			        ,offset: 'auto'
					    			        ,id: 'layerDemo'+'auto'
					    			        ,title: '提示'
					    			        ,content: '<div style="padding: 20px 100px; color:#FF5722">'+ "您不在此课程内.." +'</div>'
					    			        ,btn: '关闭'
					    			        ,btnAlign: 'c'
					    			        ,skin: 'demo-class'
					    			        ,shade: 0 
					    			        ,yes: function(){
					    			        	 layer.closeAll();
					    			        }
					    			      });
					  	            });
							}else if (data.result == true) {
								$('#examinationID').val(${examination.examinationID});
								$('#ToJoinExamNow').submit();
							}
			              },
			              error: function (data) {
			            	  alert("0");
			              }
			          });
				}else{
					layui.use('layer', function(){
		  	               var $ = layui.jquery, layer = layui.layer; 
		    			      layer.open({
		    			        type: 1
		    			        ,offset: 'auto'
		    			        ,id: 'layerDemo'+'auto'
		    			        ,title: '错误'
		    			        ,content: '<div style="padding: 20px 100px; color:#FF5722">'+ "账号密码不可为空" +'</div>'
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
			}
			</script>
		</div>
	</div>
</body>
</html>
