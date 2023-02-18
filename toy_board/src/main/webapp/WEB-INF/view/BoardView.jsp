<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript" src="./resources/js/boardview.js"></script>
<link rel="stylesheet" type="text/css" href="./resources/css/boardview.css">
<link rel="stylesheet" type="text/css" href="./resources/css/boardList.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세</title>

</head>
<body>
<%@include file="/WEB-INF/view/extra/header.jsp" %>

	
	<div class = "boardViewTitle">
		<h3>
			<span>[${bview.categoryName}]</span>
			<span>${bview.title}</span>
		</h3>
	</div>
	<div class="boardViewinfo">
		<span class="author">작성자 : ${bview.userId}</span> 
		<span class="createDate">${bview.createDate}</span>
	</div>
	
	<div class="boardContent">
		<div class="board-inner">
			<span>${bview.content}</span>
		</div>
	</div>
	
	
	<div>
		<button type="button" onclick="intoList()" class="intoList">목록으로</button>	
	</div>
	<script type="text/javascript">
	
	</script>

</body>
</html>