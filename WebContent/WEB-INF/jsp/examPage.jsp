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
<title>${examination.examinationName}考试页面</title>
</head>
<script language="javascript" type="text/javascript"> 
  var interval = 1000; 
  function ShowCountDown(year,month,day,hour,minute,second,divname) 
	{ 
		  var now = new Date(); 
		  var endDate = new Date(year, month-1, day,hour,minute,second); 
		  var leftTime=endDate.getTime()-now.getTime(); 
		  var leftsecond = parseInt(leftTime/1000); 
		  var day1=Math.floor(leftsecond/(60*60*24)); 
		  var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
		  $('#hour').val(hour);
		  var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
		  $('#minute').val(minute);
		  var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60); 
		  $('#second').val(second);
		  var cc = document.getElementById(divname); 
		  cc.innerHTML = "剩余:"+hour+"小时"+minute+"分"+second+"秒"; 
		  timeEnd();
	 } 
		window.setInterval(function(){ShowCountDown(${year},${month},${day},${hour},${minute},${seconds},'time');}, interval); 

		function timeEnd() {
			  if($('#hour').val() == '0' && $('#minute').val() == '5' &&$('#second').val() == '0'){
				  layui.use('layer', function(){
	 	               var $ = layui.jquery, layer = layui.layer; 
	   			      layer.open({
	   			        type: 1
	   			        ,offset: 'auto'
	   			        ,id: 'layerDemo'+'auto'
	   			        ,title: '提示'
	   			        ,content: '<div style="padding: 20px 100px; color:#FF5722">'+ "考试剩余时间不多" +'</div>'
	   			        ,btn: '关闭'
	   			        ,btnAlign: 'c'
	   			        ,skin: 'demo-class'
	   			        ,shade: 0 
	   			        ,yes: function(){
	   			        	 layer.closeAll();
	   			        }
	   			      });
	 	            });
			  }else if($('#hour').val() == '0' && $('#minute').val() == '0' &&$('#second').val() == '10'){
				  layui.use('layer', function(){
	 	               var $ = layui.jquery, layer = layui.layer; 
	   			      layer.open({
	   			        type: 1
	   			        ,offset: 'auto'
	   			        ,id: 'layerDemo'+'auto'
	   			        ,title: '提示'
	   			        ,content: '<div style="padding: 20px 100px; color:#FF5722">'+ "考试结束,正在交卷.." +'</div>'
	   			        ,btn: '关闭'
	   			        ,btnAlign: 'c'
	   			        ,skin: 'demo-class'
	   			        ,shade: 0 
	   			        ,yes: function(){
	   			        	 layer.closeAll();
	   			        }
	   			      });
	 	            });
				  setTimeout('examEnd()',3000);
				    
			}
		}
		
		function examEnd() {
			 window.location.href = "<%=request.getContextPath()%>/index.jsp";
		}
		
		
		</script>
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
			<a class="navbar-brand" href="#">CMS考试系统</a> <a class="navbar-brand"
				href="#">剩余：<span id="time"></span></a>
		</div>
		<form class="navbar-form navbar-right"
			style="margin-right: 5%; float: left;">
			<a id="lllww" style="width: 100px; color: white; font-size: 1.7em;">${examination.examinationName}</a>
		</form>
	</div>
	</nav>

	<div>
	<input id="hour" type="text" style="display: none;"/>
	<input id="minute" type="text" style="display: none;"/>
	<input id="second" type="text" style="display: none;"/>
		<br /> <br /> <br /> <br />
	</div>



</body>
</html>