<%@page import="com.qdu.pojo.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.webcam.min.js"></script>
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
			<a id="lllww" style="width: 100px; color: white; font-size: 1.7em;">考试须知</a>
		</form>
	</div>
	</nav>

	<div>
		<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
	</div>

	<div
		style="heigh: 300px; background-color: white; margin-left: 19%; margin-right: 20%; padding-left: 10%; padding-right: 10%; padding-top: 4%; padding-bottom: 100px;">

		<fieldset>
			<legend> 温馨提示： </legend>
			<br> 
			1.<span class="style4">考试需由考生本人亲自作答，系统随机抓拍，最后由系统比对及送交教师审核，录入诚信档案，
				一经发现作假问题系统将判考生所有试卷0分及限制考生后续所有科目考试</span><br>
			2.<span style="color: red;">考试过程中跳出或者刷新页面系统不会保存答案</span>，跳出超出一定次数试卷将自动提交<br>
			3.考试中如有问题可直接在考试页面进行反馈，耐心等待管理员及老师的协调<br> <br>
		</fieldset>
		
		<form id="examForm" style="display: none;" action="<%=request.getContextPath() %>/exam/reallyToJoinExam.do" method="post">
		    <input id="examinationID" name="examinationID" type="text" style="display: none;"/>
		    <input id="studentRoNo" name="studentRoNo" type="text" style="display: none;"/>
		</form>

		<div style="height: 60px; width: 300px; float: left;">
			<input type="submit" style="float: left; margin-left: 4%;"
				class="layui-btn" onclick="nextstep()" value="我已阅读,开始考试" /> <input
				type="button" style="float: left; margin-left: 24%;"
				class="layui-btn" onclick="javascript:history.back()" value="返回" />
			<br /> <br />
		</div>
	</div>
	
	<script type="text/javascript">
	function nextstep() {
		$('#studentRoNo').val(${student.studentRoNo});
		$('#examinationID').val(${examination.examinationID});
		$('#examForm').submit();
	}
	</script>
</body>
</html>