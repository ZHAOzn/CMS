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
	href="<%=request.getContextPath()%>/icon/天网.ico" media="screen" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/layui.css">
<script src="<%=request.getContextPath()%>/layui/layui.js "></script>
<title>班级信息</title>



<script type="text/javascript">
function aClick(clazzId) {
	  document.getElementById("asd"+clazzId).submit()
}
//修改班级信息
function changeWhenClick(clazzId) {
	 var clazzIdpre = clazzId.substring(3);
	 document.getElementById("preclazzId").value = clazzIdpre;
	$('#clazzForm').toggle();	
}
function saveChange() {
	 $.ajax({
         type: "GET",
         data: {
        	 "clazzId": $('#preclazzId').val(),
        	 "clazzName": $('#preclazzName').val()
         },
         contentType: "application/json; charset=utf-8",
         dataType: "json",
         async: false,
         url: "<%=request.getContextPath()%>/clazz/changeClazzByAjax.do",
         success: function (data) {
        	 alert(data.message);
        	 window.location.reload();
         },
         error: function (data) {
             alert("修改失败！");
         },
     });
}
//ajax删除clazz,本质上是修改clazz的外键courseId为null
function deleteClazzByAjax(clazzid) {
	var clazzId = clazzid.substring(3);
	 $.ajax({
         type: "GET",
         data: {
        	 "clazzId": clazzId
         },
         contentType: "application/json; charset=utf-8",
         dataType: "json",
         async: false,
         url: "<%=request.getContextPath()%>/clazz/deleteClazzById.do",
         success: function (data) {
        	 alert(data.message);
        	 window.location.reload();
         },
         error: function (data) {
             alert("删除失败！");
         },
     });
}
//隐藏正在滚动刷新的验证码
function closeValidateCode() {
	$("#validateCode").hide();
}
//二维码要用到的随机数
function hello(){ 
	 $.ajax({
         type: "GET",
         data: {
         },
         contentType: "application/json; charset=utf-8",
         dataType: "json",
         async: false,
         url: "<%=request.getContextPath()%>/student/getVertifyCode.do",
         success: function (data) {
        	 $("#validateCode").html(data.code);
        	 var t2 = window.setTimeout("hello()",15000);//15s后刷新随机数
         },
         error: function (data) {
             alert("服务器异常！");
         },
     });
	 window.setTimeout("closeValidateCode()",30000);
	} 
//倒计时
var maxtime = 10;
function CountDown(){  
		if(maxtime>=0){   
			 seconds = maxtime;  
			 msg = "签到码还有"+seconds+"秒刷新";  
			 document.all["timer"].innerHTML=msg;   
			 --maxtime;  
		}else{  
		clearInterval(timer);    
		}  
}  
//timer = setInterval("CountDown()",1000); 	
//显示签到二维码
function showQrImg() {
	 $.ajax({
         type: "GET",
         data: {
        	 "courseId": $('#cccourseId').val(),
        	 "teacherMobile":$('#teacherMobile').val()
         },
         contentType: "application/json; charset=utf-8",
         dataType: "json",
         async: false,
         url: "<%=request.getContextPath()%>/course/getQrImg.do",
         success: function (data) {
        	 var url = "/ClassManageSys/qrImg/" + data.url + ".gif";
        	 var imgPre = document.getElementById("qrImg");
//         	 imgPre.style.display = "block";
             imgPre.src = url;
             setInterval("CountDown()",1000);
             //5s刷新
             setInterval('YesConfirm()', 5000);
             var t1 = window.setTimeout("hello()",10000); //10s后显示随机数
            // window.clearTimeout(t1);//去掉定时器             
         },
         error: function (data) {
             alert("服务器异常！");
         },
     });
}
//获取签到成功学生列表
function YesConfirm() {
	 $.ajax({
         type: "GET",
         data: {
        	 "courseId": $('#cccourseId').val()
         },
         contentType: "application/json; charset=utf-8",
         dataType: "json",
         async: true,
         url: "<%=request.getContextPath()%>/student/getTemStudent.do",
         success: function (data) {
        	    var dataObj = data.clazzStus, //返回的data为json格式的数据
        	    con =  '\
        	    		<caption>实时签到</caption>\
        				<tr>\
        					<th>学号</th>\
        					<th>姓名</th>\
        					<th>班级</th>\
        				</tr>\
        				';
        	    $.each(dataObj, function (index, item) {
        	        con += "<tr>";
        	        con += "<td>" + item.student.studentRoNo + "</td>";
        	        con += "<td>" + item.student.studentName + "</td>";
        	        con += "<td>" + item.clazz.clazzName + "</td>";
        	        con += "<tr/>";
        	    });
        	        //可以在控制台打印一下看看，这是拼起来的标签和数据
        	        //把内容入到这个div中即完成
        	    $("#showStudents").html(con);
         },
         error: function (data) { 
             console.log(data);
             alert("服务器异常！");
         },
     });
}
//提交签到表
function submitSignIn() {
	 $.ajax({
         type: "GET",
         data: {
        	 "courseId": $('#cccourseId').val(),
         },
         contentType: "application/json; charset=utf-8",
         dataType: "json",
         async: false,
         url: "<%=request.getContextPath()%>/student/submitSignIn.do",
			success : function(data) {
				alert(data.message);
				window.location.reload();
			},
			error : function(data) {
				alert("服务器异常！");
			},
		});
	}  
	//获取所有签到记录

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
					<li class="layui-nav-item"><a href="">控制台<span
							class="layui-badge">9</span></a></li>
					<li class="layui-nav-item"><a href="">个人中心<span
							class="layui-badge-dot"></span></a></li>
					<li class="layui-nav-item"><a href="#">${teacher.teacherName}老师</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">修改信息</a>
							</dd>
							<dd>
								<a href="javascript:;">安全管理</a>
							</dd>
							<dd>
								<a href="javascript:;">注销</a>
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
						href="javascript:;">签到</a>
						<dl class="layui-nav-child">
							<dd>
								<a id="signShow" href="#">点名签到</a>
							</dd>
							<dd>
								<a id="otherShow" href="#">签到记录 & 补签</a>
							</dd>
							<dd>
								<a href="#">待定</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="#">解决方案</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="#">移动模块</a>
							</dd>
							<dd>
								<a href="#">后台模版</a>
							</dd>
							<dd>
								<a href="#">电商平台</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="#">产品</a></li>
					<li class="layui-nav-item"><a href="#">大数据</a></li>
				</ul>
			</div>
		</div>


		<!-- 内容 -->
		<div class="layui-body site-demo">
			
			<span class="layui-breadcrumb" lay-separator="|">
 				<a href="#">发布公告</a>
 				<a href="#">上传资料</a>
			</span>
			<br><br><br><br>
			<span id="" style="margin-left: 5%;color: #c2c2c2; font-style: oblique;">${course.courseName}：考勤签到</span>
			<hr class="layui-bg-cyan">
			<!-- 签到content -->
			<div id="signModel"
				style="width: 100%; overflow: hidden; height: 100%;">
				<!-- 签到模块 -->
				<div
					style=" width: 49%; float: right;margin-top: 7%;">
					<!-- 二维码模块 -->
					<div
						style="width: 98%; height: 20%;text-align: center;">
						<!-- 签到数字 -->
						<div id="validateCode"
							style="width: 98; height: 30px; font-size: 25px; text-align: center; ">
							<span id="timer">签到码</span>
						</div>
						<!-- 签到二维码 -->
						<div
							style=" padding: 10px; width: auto; text-align: center;">
							<img style="border: solid; border-color: black;" id="qrImg"
								 src="">
						</div>
					</div>
					<!-- 签到操作 -->
					<div style="text-align: center;">
						<input type="text" value="${course.courseId}"
							style="display: none;" /> <br /> <a id="qrHref"
							class="layui-btn layui-btn-normal" onclick="showQrImg()" href="#">开始签到</a>
						<a class="layui-btn layui-btn-danger" href="#"
							onclick="submitSignIn()">提交签到表</a><br /> <br />
					</div>
				</div>
				<!-- 垂直分界线 -->
				<div
					style="float: right; width: 2%; height: 100%; text-align: center;">
					<hr style="width: 2px; height: 100%; background-color: #c2c2c2;"></hr>
				</div>
				<!-- 签到状况模块 -->
				<div style="width: 48%;">
					<table class="layui-table" width="99%" border="1" id="showStudents"
						style="margin-top: 10%;margin-left: 15px;">

					</table>
				</div>
			</div>
			
			<script>
				//签到模块显示
				$('#signShow').click(function() {
					$('#signModel').show();
					$('#otherModel').hide();
				});
			</script>

			<!-- 其他模块 -->
			<div id="otherModel" style="display: none;">
				
				<!-- 班级信息修改 -->
				<div
					style="width: 49%; heigh: 600px; overflow: auto;">
					<form id="clazzForm" style="display: none">
						<input type="text" id="courseId" name="courseId" value="${course.courseId}" /><br />
						 <input type="text" id="preclazzId" name="preclazzId"><br />
							 班级名称：<input type="text" id="preclazzName" name="preclazzName"><br />
						<input onclick="saveChange()" type="button" value="提交修改" />
					</form>
				</div>

				
				<div style="width: 49%; heigh: 600px; border: solid;; border-color: red;">

					<a href="#">补签</a>
					<br />
					<input type="text" id="cccourseId" value="${course.courseId}" style="display: none;" />
					<input type="text" id="teacherMobile" value="${teacher.teacherMobile}" style="display: none;" />
					<a href="<%=request.getContextPath()%>/clazz/forAddClazz.do?courseId=${course.courseId}">+添加班级</a>
					<br />
					
					<!-- 班级表格 -->
					<table border="1">
						<tr>
							<th>班级</th>
							<th>学年</th>
							<th colspan="3">操作</th>
						</tr>

						<c:choose>
							<c:when test="${! empty course.clazz }">
								<c:forEach items="${course.clazz}" var="c">
									<tr>
										<td>${c.clazzName}</td>
										<td>${c.currentYear}</td>
										<td>
											<form id="asd${c.clazzId}"
												action="<%=request.getContextPath()%>/student/selectStudentByClazzId.do"
												method="post">
												<input name="clazzId" style="display: none;"
													value="${c.clazzId}" /> <a id="${c.clazzId}"
													onclick="aClick(this.id)" href="#">查看</a>
											</form>
										</td>
										<td><a id="zxc${c.clazzId}"
											onclick="changeWhenClick(this.id)" href="#">修改</a></td>
										<td><a id="del${c.clazzId}"
											onclick="deleteClazzByAjax(this.id)" href="#">删除</a></td>
									</tr>
									<br />
								</c:forEach>
							</c:when>
							<c:otherwise>
								<a>(空)</a>
							</c:otherwise>
						</c:choose>
					</table>
					<br /> <br />
				</div>
			
			</div>

			<script>
				//其它模块显示
				$('#otherShow').click(function(){
					$('#otherModel').show();
					$('#signModel').hide();
				})
			</script>
			


			<br />

			<!-- 签到记录表 -->
			<div style="border: solid;border-color: yellow;padding:30px 100px;">
				<table lay-filter="recordTable"	>
					<caption>本学期签到汇总</caption>
					<thead>
						<tr>
							<th lay-data="{field:'userId', width:200}">学号</th>
							<th lay-data="{field:'userClass', width:200}">班级</th>
							<th lay-data="{field:'username', width:200}">姓名</th>
							<th lay-data="{field:'sign', width:150, sort:true}">签到</th>
							<th lay-data="{field:'late', width:150, sort:true}">迟到</th>
							<th lay-data="{field:'leaveEarly', width:150, sort:true}">早退</th>
							<th lay-data="{field:'absenteeism', sort:true, width:200}">旷课</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${! empty studentInfo}">
								<c:forEach items="${studentInfo}" var="s">
									<tr>
										<td>${s.student.studentRoNo}</td>
<!-- 										<td>班级怎么获取</td> -->
										<td>${s.student.studentName}</td>
										<td>${s.signIn}</td>
										<td>${s.comeLate}</td>
										<td>${s.leaveEarlier}</td>
										<td>${s.absenteeism}</td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</tbody>
				</table>
			</div>


		</div>

	</div>


 
	<script>
		layui.use([ 'element', 'layer' ,'table'], function() {
			var element = layui.element, $ = layui.jquery,table = layui.table;
			//监听导航点击
			element.on('nav(demo)', function(elem) {
				//console.log(elem)
				layer.msg(elem.text());
			});
			//转换静态表格
			table.init('recordTable', {
			  //设置高度
			  //支持所有基础参数
			});
			
		});
	</script>

</body>
</html>