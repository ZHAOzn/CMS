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
		});
		 $('#studentLog').click(function name() {
			 $('#studentInfo').hide();
		});
		 $('#teacherInfo').click(function name() {
			 $('#studentInfo').hide();
		});
		 $('#teacherLog').click(function name() {
			 $('#studentInfo').hide();
		});
		 $('#crud').click(function name() {
			 $('#studentInfo').hide();
		});
		 $('#quxianmanage').click(function name() {
			 $('#studentInfo').hide();
		});
		 
	 });
function clearRedis() {
	alert("....");
	 $.ajax({
         type: "GET",
         data: {
         },
         contentType: "application/json; charset=utf-8",
         dataType: "json",
         async: false,
         url: "<%=request.getContextPath()%>/admin/clearRedis.do",
         success: function (data) {
        	if(data.result == true){
        		alert("清理成功");
        	}else {
				alert("清理出现问题");
			}    
         },
         error: function (data) {
             alert("服务器异常！");
         },
     });
}
</script>
</head>
<body>
	<!-- 课程二维码 -->
	<img id="target"
		style="width: 390px; height: 390px; display: none; z-index: 9; background-color: rgba(0, 0, 0, 1);"
		src="" />

	<!-- 新建课程成功提示信息 -->
	<div id="addCourseShow"
		style="background-color: #393D49; height: 20%; width: 20%; z-index: 20; position: fixed; margin-top: 23%; text-align: center; margin-left: 50%; display: none;">
		<h3 style="color: white; margin-top: 19%">添加课程成功..</h3>
	</div>

	<div class="layui-layout layui-layout-admin">
		<!-- 头部导航 -->
		<div class="layui-header header header-demo">
			<div class="layui-main">
				<a class="CMSlogo" href="/"><span
					style="color: white; font-size: 25px;">CMS</span></a>

				<ul class="layui-nav">
					<li class="layui-nav-item"><a id="checkCourseShow2" href="#">
					<i class="layui-icon bbbbb" style="font-size: 20px; color: #d2d2d2">&#xe68e;</i></a></li>
					<li class="layui-nav-item"><a id="messageButtton" href="#">
					<i class="layui-icon bbbbb" style="font-size: 20px; color: #d2d2d2">&#xe63a;</i><span
							id="TmessageCount" class="layui-badge">2</span></a></li>
					<li class="layui-nav-item"><a id="teacherInfoCenter" href="#">
					<i class="layui-icon bbbbb" style="font-size: 20px; color: #d2d2d2">&#xe612;</i><span
							id="redSignal" style="display: none;" class="layui-badge-dot"></span></a></li>
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
								<a id="studentLog" href="#">操作日志</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="#">教师管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a id="teacherInfo" href="#">教师信息</a>
							</dd>
							<dd>
								<a id="teacherLog" href="#">操作日志</a>
							</dd>
						</dl></li>
						<li class="layui-nav-item"><a href="#">资料管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a id="crud" href="#">CRUD</a>
							</dd>
							<dd>
								<a onclick="clearRedis()" href="#">清理redis</a>
							</dd>
							<dd>
								<a id="quxianmanage" href="#">权限管理</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="#">管理博客</a></li>
					<li class="layui-nav-item"><a id="teacherLog" href="#">公告发布</a></li>
				</ul>
			</div>
		</div>

		<!-- 内容显示 -->
		<div class="layui-body site-demo"
		style="padding-top: 3%; overflow: auto;">
			<br />
			
			<span id="messageList" style="margin-left: 5%;">学生信息</span>
			<hr class="layui-bg-cyan">
			
			<!-- 学生信息模块 -->
			<div id="studentInfo">
				<table class="layui-table" style="text-align: center;">
					<colgroup>
						<col width="150">
						<col width="120">
						<col width="150">
						<col width="150">
						<col width="120">
						<col width="120">
					</colgroup>
					<thead>
						<tr>
							<th style="text-align: center;">学号</th>
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
										<td><a id="studentDetail" href="#"><i class="layui-icon" style="font-size: 30px; 
										color: #1E9FFF;">&#xe65b;</i> </a></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="9">(暂无课程)</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<script>
			layui.use([ 'element', 'layer' ,'table'], function() {
				var element = layui.element, $ = layui.jquery,table = layui.table;
				//转换静态表格
				table.init('courseInfo', {
				  //设置高度
				  //支持所有基础参数
				});
				table.init('signRecord',{
					
				});
			});
			</script>
			
			
			
		</div>	
		</div>
		<script type="text/javascript">
		layui.use([ 'element', 'layer' ],function() {
			var element = layui.element, $ = layui.jquery, layer = layui.layer;
			//触发事件
			var active = {
				notice : function() {
					//示范一个公告层
					layer
							.open({
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
									deleteThis();
									layer.closeAll();
								},
								btn2 : function(index, layero) {
									//按钮【按钮二】的回调
								},
								btnAlign : 'c',
								moveType : 1 //拖拽模式，0或者1
								,
								content : '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">确定删除该课程？</div>'
							});
				},
				page : function() {
					layer.open({
						type : 1,
						title : false,
						closeBtn : 0,
						area : '390px',
						skin : 'layui-layer-lan', //没有背景色
						shadeClose : true,
						content : $('#target')
					});
				}

			};

			$('#layerDemo .layui-btn').on(
					'click',
					function() {
						var othis = $(this), method = othis
								.data('method');
						active[method] ? active[method].call(
								this, othis) : '';
					});

			//监听导航点击
			element.on('nav(demo)', function(elem) {
				//console.log(elem)
				layer.msg(elem.text());
			});
		});
		</script>
</body>
</html>






<%-- <div style=" width: 70%; height:500px; overflow: auto;">
	<table border="1" style="width: 100%">
		<caption>用户操作日志</caption>
		<tr height="10px">
			<th>账号</th>
			<th>用户</th>
			<th>ip地址</th>
			<th colspan="3">方法</th>
			<th>日期</th>
			<th>响应时间</th>
			<th width="30px" style="width: 40px; overflow: auto;">结果</th>
		</tr>
		<c:choose>
		<c:when test="${! empty logEntities}">
		<c:forEach items="${logEntities}" var="s">
		<tr height="10px">
		<td>${s.userId}</td>
		<td>${s.module}</td>
		<td>${s.ip}</td>
		<td colspan="3">${s.method}</td>
		<td>${s.date}</td>
		<td>${s.reponseTime}</td>
		<td style="width: 40px; overflow: auto;">${s.result}</td>
		</tr>
		</c:forEach>
		</c:when>
		<c:otherwise>
		<tr>
		<td colspan="7">(空)</td>
		</tr>
		</c:otherwise>
		</c:choose>
   </table>

   </div> --%>