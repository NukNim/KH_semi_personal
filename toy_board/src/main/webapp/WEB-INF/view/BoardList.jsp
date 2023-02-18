<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css" href="./resources/css/boardview.css?ver=1">
<link rel="stylesheet" type="text/css" href="./resources/css/boardList.css?ver=1">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 제목</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript" src="./resources/js/boardview.js"></script>

</head>
<body>
<%@include file="/WEB-INF/view/extra/header.jsp" %>
	<div>
	</div>
	<div>
		<table id="blist" class="" name="">
			<thead>
				<tr>
					<th class="no">번호</th>
					<th class="type">분류</th>					
					<th class="title">제목</th>
					<th class="author">글쓴이</th>
					<th class="createD">등록 시간</th>
					<th class="viewCnt">조회수</th>
				</tr>
			</thead>
			<tbody>
					<c:forEach var="anlist" items="${anlist}" >
						<tr class="allNoti">
							<td class="no">${anlist.id}</td>
							<td class="type">${anlist.categoryName}</td>
							<td class="title">
								<a href = "<%=request.getContextPath()%>/view?id=${anlist.id}">
									${anlist.title}
								</a>
							</td>
							<td class="author">${anlist.userId}</td>
							<td class="createD">
								<fmt:formatDate value="${anlist.createDate}" pattern="MM.dd / kk:mm" type="date"/>
							</td>
							<td class="viewCnt">${anlist.viewCnt}</td>
							

						</tr>
					</c:forEach>
					<c:forEach var="bnlist" items="${bnlist}" >
						<tr class="Noti">
							<td class="no">${bnlist.id}</td>
							<td class="type">${bnlist.categoryName}</td>
							<td class="title">
								<a href = "<%=request.getContextPath()%>/view?id=${bnlist.id}">
									${bnlist.title}
								</a>
							</td>
							<td class="author">${bnlist.userId}</td>
							<td class="createD">
								<fmt:formatDate value="${bnlist.createDate}" pattern="MM.dd / kk:mm" type="date"/>
							</td>
							<td class="viewCnt">${bnlist.viewCnt}</td>
						</tr>
					</c:forEach>
					<c:forEach var="blist" items="${blist}" >
						<tr>
							<td class="no">${blist.id}</td>
							<td class="type">${blist.categoryName}</td>
							<td class="title">
							<a href = "<%=request.getContextPath()%>/view?id=${blist.id}">
									${blist.title}
								</a>
							</td>
							<td class="author">${blist.userId}</td>
							<td class="createD">
								<fmt:formatDate value="${blist.createDate}" pattern="MM.dd / kk:mm" type="date"/>
							</td>
							<td class="viewCnt">${blist.viewCnt}</td>
						</tr>
					</c:forEach>
			</tbody>
		</table>
<hr>		
	<button class="btn bwrite" type="button" onclick="gotoWrite()">글작성하기</button>
		
		
		
	</div>
</body>
</html>