<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<link rel="stylesheet" type="text/css" href="./resources/css/boardview.css">
<link rel="stylesheet" type="text/css" href="./resources/css/boardList.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript" src="./resources/js/boardview.js"></script>
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
			<span class="commentUserId step${clist.commentStep}">아이디 : ${clist.userId}</span>
			<button type="button" class="sbtn showRecomment" style="display: none;">답글</button>
			<div class="recommentbox${clist.commentStep}">
				<form class="commentForm" action="" method="post">
					<input type="hidden" class="commId" name="commId" value="${clist.commentId}">
					<input type="hidden" class="pNum" name="pNum" value="${param.p}">
					<input type="hidden"  class="boardId" name="boardId" value="${param.id }">
					<input type="hidden"  class="stepType" name="stepType" value="2">
					<input type="text" class="commentListId" name="commentListId" value="${clist.userId}">
					<input type="password"  class="commentListPw" name="commentListPw">
					<textarea class="Listcomment" disabled>${clist.context} </textarea>
					<textarea class="recomment"></textarea>
					
				</form>
				<div class="buttonbox" >
					<button type="button" id="commentModi" class="btn commentChange" style="display: inline-block">수정</button>
					<button type="button" id="commentModi" class="btn commentModi" style="display: none">수정확인</button>
					<button type="button" id="commentDel" class="btn commentDel">삭제</button>
					<button type="button"  class="btn reCommentReg">답글등록</button>
				</div>
			</div>
		</c:forEach>
	</div>
	<div>
		<button type="button" class="btn totop">맨 위로</button>
	</div>
	
	<script type="text/javascript">
 
	$("#regComment").on("click", cWrite);
	$(".commentChange").on("click", cChange);
	$(".commentModi").on("click", cModify);
	$(".commentDel").on("click", cDelete);
	$(".showRecomment").on("click", function () {
		$(this).next().find(".commentForm").find(".recomment").css("display","inline-block");
		$(this).next().find(".buttonbox").find(".reCommentReg").css("display","inline-block");
	});
	$(".reCommentReg").on("click", recWrite);
	
	function cChange(){
		
		var formId = $(this).parent().prev(".commentForm");
		
		 if(formId.find(".commentListId").val() == null || formId.find(".commentListId").val() ==""){
			alert("등록하실 댓글에 아이디를 입력해 주세요");
		}else if(formId.find(".commentListPw").val() == null || formId.find(".commentListPw").val() ==""){
			alert("등록하실 댓글에 비밀번호를 입력해 주세요");
		}else{
			checkUserid(formId.find(".commentListId").val(), formId.find(".commentListPw").val());

		}

	}
	
	 
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
	
	function cModify(){
		
		var formId = $(this).parent().prev(".commentForm");
		
		 if(formId.find(".commentListId").val() == null || formId.find(".commentListId").val() ==""){
			alert("수정하실 댓글에 아이디를 입력해 주세요");
		}else if(formId.find(".commentListPw").val() == null || formId.find(".commentListPw").val() ==""){
			alert("수정하실 댓글에 비밀번호를 입력해 주세요");
		}else if(formId.find(".Listcomment").val() == null || formId.find(".Listcomment").val() ==""){
			alert("내용을 입력해 주세요");
		}else{
			CommentModify();	
			}
		}
	
	function cDelete(){
		
		var formId = $(this).parent().prev(".commentForm");
		
		 if(formId.find(".commentListId").val() == null || formId.find(".commentListId").val() ==""){
			alert("삭제하실 댓글에 아이디를 입력해 주세요");
		}else if(formId.find(".commentListPw").val() == null || formId.find(".commentListPw").val() ==""){
			alert("삭제하실 댓글에 비밀번호를 입력해 주세요");
		}else{
			CommentDelete();
			}
		}
	
	function recWrite(){
		
		var formId = $(this).parent().prev(".commentForm");
		
		 if(formId.find(".commentListId").val() == null || formId.find(".commentListId").val() ==""){
			alert(" 댓글에 답글을 달을 아이디를 입력해 주세요");
		}else if(formId.find(".commentListPw").val() == null || formId.find(".commentListPw").val() ==""){
			alert("댓글에 답글을 달을 비밀번호를 입력해 주세요");
		}else if(formId.find(".Listcomment").val() == null || formId.find(".Listcomment").val() ==""){
			alert("내용을 입력해 주세요");
		}else{
			RecommentReg();
			}
		}

	$(".totop").click(function(){
		  window.scrollTo({top : 0, behavior: 'auto'}); 
		});
	
	$(".recommentbox1").on("click", function(e){
		e.preventDefault();
			$(this).children(".buttonbox").css('display', 'block');
			$(this).children(".commentForm").children(".commentListId").css('display', 'block');
			$(this).children(".commentForm").children(".commentListPw").css('display', 'block');
			$(this).addClass("selectbox");
			$(this).prev().css('display', 'block');
			$('.recommentbox1').not($(this)).children(".buttonbox").css('display', 'none');
			$('.recommentbox1').not($(this)).children(".commentForm").children(".commentListId").css('display', 'none');
			$('.recommentbox1').not($(this)).children(".commentForm").children(".commentListPw").css('display', 'none');
			$('.recommentbox1').not($(this)).children(".commentForm").children(".recomment").css('display', 'none');
			$('.recommentbox1').not($(this)).removeClass("selectbox");
			$('.recommentbox1').not($(this)).prev().css('display', 'none');
	})

	
		function checkUserid(userid, userpw){
		
		var formId = $(".selectbox").find(".commentForm");
		
		$.ajax({
			url : "<%=request.getContextPath() %>/commid.lo",
			type : "post",
			async : false,
			data : {userid : userid, userpw : userpw, commid : formId.find(".commId").val()},
			success : function(result){
				if(result === "1"){
					formId.next().find(".commentChange").css("display", "none");
					formId.next().find(".commentModi").css("display", "inline-block");
					formId.find(".Listcomment").attr("disabled", false);	
				}else{
					alert(result);
				}
			},
			error : function(request, status, error){
				alert(request.statis)
				console.log(result + "error");
			}
		});
	}
	
	function CommentModify(){
		
		var formId = $(".selectbox").find(".commentForm");
		
		$.ajax({
			url : "<%=request.getContextPath() %>/commupdate.lo",
			type : "post",
			async : false,
			data : {userid : formId.find(".commentListId").val(), userpw : formId.find(".commentListPw").val()
					, commid : formId.find(".commId").val(), boardId : formId.find(".boardId").val()
					, pNum : formId.find(".pNum").val(), context : formId.find(".Listcomment").val()},
			success : function(result){
				if(result === "1"){
					location.reload();
				}else{
					alert(result);
				}
			},
			error : function(request, status, error){
				alert(request.statis)
				console.log(result + "error");
			}
		});
	}
	
	function CommentDelete(){
		
		var formId = $(".selectbox").find(".commentForm");
		
		$.ajax({
			url : "<%=request.getContextPath() %>/commdelete.lo",
			type : "post",
			async : false,
			data : {userid : formId.find(".commentListId").val(), userpw : formId.find(".commentListPw").val()
					, commid : formId.find(".commId").val()},
			success : function(result){
				if(result >= "1"){
					location.reload();
				}else{
					alert(result);
				}
			},
			error : function(request, status, error){
				alert(request.statis)
				console.log(result + "error");
			}
		});
	}
	
	function RecommentReg(){
		
		var formId = $(".selectbox").find(".commentForm");
		
		$.ajax({
			url : "<%=request.getContextPath() %>/recommreg.lo",
			type : "post",
			async : false,
			data : {userid : formId.find(".commentListId").val(), userpw : formId.find(".commentListPw").val()
					, commid : formId.find(".commId").val(), boardId : formId.find(".boardId").val()
					, context : formId.find(".recomment").val(), step : formId.find(".stepType").val()},
			success : function(result){
				if(result === "1"){
					location.reload();
				}else{
					alert(result);
				}
			},
			error : function(request, status, error){
				alert(request.statis)
				console.log(result + "error");
			}
		});
	}


 </script>
	
	
</body>
</html>