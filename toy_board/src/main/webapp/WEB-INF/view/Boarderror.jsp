<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="./resources/css/boardWrite.css?ver=1.1" rel="stylesheet" type="text/css" >
<link href="./resources/css/boardList.css?ver=1.1" rel="stylesheet" type="text/css" >
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>
<script type="text/javascript" src="./resources/js/boardview.js"></script>
</head>
<body>
<%@include file="/WEB-INF/view/extra/header.jsp" %>


<div>
	<span style="text-align: center;"><h1>${msg}</h1></span>
</div>

<button type="button" onclick="intoList()" class="btn intoList">목록으로</button>

</body>
</html>