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
<title>수정 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/36.0.0/classic/ckeditor.js"></script>
<script type="text/javascript" src="./resources/js/boardview.js"></script>
</head>
<body>
<%@include file="/WEB-INF/view/extra/header.jsp" %>

	<form action="update" method="post">
	<input type="hidden" class="id" name="id" value="<%=request.getParameter("id")%>">
	<span class="sub">제목</span><input type="text" id = "boardtitle" class="boardtitle" name="boardtitle" value="${bview.title}"><br>
	<div>
		<span class="sub">아이디</span><input type="text" id = "boardid" class="boardid" name="boardid" value="${bview.userId}">
		<span class="sub">비밀번호</span><input type="password" id = "boardpw" class="boardpw" name="boardpw">
		<span id="selectName" class="selectName">
			<label>카테고리 : </label>
			<select id = "cateSelect" class="cateSelect" name="cateSelect">
				<c:forEach var="catelist" items="${calist}">
					<c:choose>
						<c:when test="${catelist.categoryId eq bview.categoryId}">
							<option value="${catelist.categoryId}" selected>${catelist.categoryName}</option>
						</c:when>
						<c:otherwise>
							<option value="${catelist.categoryId}">${catelist.categoryName}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>			
			</select>
		</span>
	</div>
		<div class="bContext">
			<textarea id="editor" class="editor" name="bContext" placeholder="내용을 입력하세요">
			</textarea>
		</div>
		
		<button type="button" class="btn updateBtn" id="updateBtn" name="updateBtn">수정</button>
	</form>
</div>

<!-- CKeditor5 삽입 부분  -->
 <script>
 let editor;
         ClassicEditor
                 .create( document.querySelector('#editor'), {
                 	toolbar: {
                 		items: [
                 			'bold',
                 			'italic',
                 			'|',
                 			'outdent',
                 			'indent',
                 			'|',
                 			'blockQuote',
                 			'undo',
                 			'redo'
                 		]
                 	},
                 	language: 'ko'
                 })
                  .then( newEditor => {
  						 editor = newEditor;
  						 editor.setData('${bview.content}');
			  } )
                 .catch( error => {
                         console.error( error );
                 } );
         
 </script>
 <!-- CKeditor5 삽입 부분  -->
 <script type="text/javascript">
 
	$("#updateBtn").on("click", bUpdate);
	 
	function bUpdate(){
		
		var contents = editor.getData();
		
		if($(".boardtitle").val() == null || $(".boardtitle").val() ==""){
			alert("제목을 입력해 주세요");	
		}else if($(".boardid").val() == null || $(".boardid").val() ==""){
			alert("아이디를 입력해 주세요");
		}else if($(".boardpw").val() == null || $(".boardpw").val() ==""){
			alert("비밀번호를 입력해 주세요");
		}else if(editor.getData() == null || editor.getData() ==""){
			alert("내용을 입력해 주세요");
		}else{
			if($(".cateSelect").val() == "50" || $(".cateSelect").val() == "60" ){
				checkAid();
			}else{
				if("${bview.userId}" == $(".boardid").val()){
					$("form").submit();		
				}else if($(".boardid").val() == "admin"){
					checkAid();
				}else{
					alert("원 작성자가 아닙니다.");
				}
				
			}
		}
	}
	
	function checkAid(){
		$.ajax({
			url : "<%=request.getContextPath() %>/check.lo",
			type : "post",
			async : false,
			data : {userid : $(".boardid").val(), userpw : $(".boardpw").val()},
			success : function(result){
				if(result === "1"){
					$("form").submit();
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