<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<link href="./resources/css/boardWrite.css?ver=1.1" rel="stylesheet" type="text/css" >
<link href="./resources/css/boardList.css?ver=1.1" rel="stylesheet" type="text/css" >
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript" src="./resources/js/boardview.js"></script>
</head>
<body>
<%@include file="/WEB-INF/view/extra/header.jsp" %>

	<form action="delete" method="post">
	<input type="hidden" class="id" name="id" value="<%=request.getParameter("id")%>">
	<div>
		<span class="sub">아이디</span><input type="text" id = "boardid" class="boardid" name="boardid" value="${bview.userId}">
		<span class="sub">비밀번호</span><input type="password" id = "boardpw" class="boardpw" name="boardpw">
	</div>
		<button type="button" class="btn deleteBtn" id="deleteBtn" name="deleteBtn">삭제</button>
	</form>
</div>

 <script type="text/javascript">
 
	$("#deleteBtn").on("click", bDelete);
	 
	function bDelete(){
		if($(".boardid").val() == null || $(".boardid").val() ==""){
			alert("아이디를 입력해 주세요");
		}else if($(".boardpw").val() == null || $(".boardpw").val() ==""){
			alert("비밀번호를 입력해 주세요");
		}else{
			$("form").submit();
		}
	}
	

 </script>
 

</body>
</html>