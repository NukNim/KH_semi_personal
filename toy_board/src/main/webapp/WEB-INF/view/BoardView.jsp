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
		<span class="createDate">
			<fmt:formatDate value="${bview.modifyDate != null ? bview.modifyDate :  bview.createDate}" pattern="yyyy.MM.dd / kk:mm" type="date"/>
		</span>
	</div>
	
	<div class="boardContent">
		<div class="board-inner">
			<span>${bview.content}</span>
		</div>
	</div>

	<div style="float: right;">
		<button type="button" onclick="intoList(${param.p})" class="btn intoList">목록으로</button>	
		<button type="button" onclick="goUpdate(${bview.id},${param.p} )" class="btn" name="updateCheck">수정</button>	
		<button type="button" onclick="goDelete(${bview.id})" class="btn" name="updateCheck">삭제</button>	
	</div>

	<div class="commentbox">
		<form id="step1" action="commentReg" method="post">
			<div class="commentInfo">
				<input type="hidden" id="boardId" class="boardId" name="boardId" value="${param.id }">
				<input type="hidden" id="pNum" class="pNum" name="pNum" value="${param.p }">
				<input type="hidden" id="stepType" class="stepType" name="stepType" value="1">
				<input type="text" id="commentId" class="commentId" name="commentId" placeholder="아이디">
				<input type="password" id="commentPw" class="commentPw" name="commentPw" placeholder="비밀번호">
			</div>
			<textarea id="comment" class="comment" name="comment"></textarea>
			<button type="button" id="regComment" class="btn regComment">등록</button>
		</form>
	</div>
	
	
	<div class="commentBody">
	<c:forEach var="clist" items="${clist}">
		<div class="commentList">
		<input type="text" class="commentListId" name="commentListId" value="${clist.userId}" disabled="disabled">
		<input type="password"  class="commentListPw" name="commentListPw" value="${clist.userPw}" disabled="disabled">
		<textarea class="Listcomment" disabled="disabled" >${clist.context} </textarea>
		</div>
	</c:forEach>
	</div>
	<div>
	</div>
	
	<script type="text/javascript">
 
	$("#regComment").on("click", cWrite);
	 
	function cWrite(){
		
		 if($(".commentId").val() == null || $(".commentId").val() ==""){
			alert("아이디를 입력해 주세요");
		}else if($(".commentPw").val() == null || $(".commentPw").val() ==""){
			alert("비밀번호를 입력해 주세요");
		}else if($(".comment") == null || $(".comment") ==""){
			alert("내용을 입력해 주세요");
		}else{	
				$("#step1").submit();
			}
		}

 </script>
	
	
</body>
</html>