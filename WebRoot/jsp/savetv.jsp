<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>欢迎您使用我们的网站后台管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="/fram/css/maincon.css">
<script type="text/javascript" src="/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="/js/progressBar.js"></script>
<link type="text/css" rel="stylesheet" href="/css/progressBar.css">
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0">


	<div class="mymain">&#8224增加教学视频</div>

	<div class="mymaincontent" id="center">


		<table width="100%" height="205" border="0" cellpadding="0"
			cellspacing="0">

			<tr>
				<td align="left" valign="top"
					style="padding: 0px; margin: 0px;; text-align: left"><iframe
						id='target_upload' name='target_upload' src=''
						style='display: none'></iframe>
					<form action="/upload" id="uploadForm"
						enctype="multipart/form-data" method="post" target="target_upload">
						视频文件<input type="file" name="upload">(必是.flv格式视频文件)<br />
						<br />
						<br />
						<br /> <input type="button" id="subButton" value="保存数据">
					</form>
					<div id="progress">
						<div id="title">
							<span id="text">上传进度</span>
							<div id="close">X</div>
						</div>
						<div id="progressBar">
							<div id="uploaded"></div>
						</div>
						<div id="info"></div>
					</div></td>
			</tr>
		</table>



	</div>










</body>
</html>
