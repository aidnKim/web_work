<%@page import="test.dao.BookDao"%>
<%@page import="test.dto.BookDto"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//출력해줄 책 목록 불러오기
	List<BookDto> list=new BookDao().selectAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/book/list.jsp</title>
<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/include/navbar.jsp">
		<jsp:param value="book" name="thisPage"/>
	</jsp:include>	
	<div class="container">
	<div class="container">
		<h1>책 정보</h1>
		<a href="insertform.jsp">책 추가하기</a>
		<table class="table table-bordered">
			<thead class="table-dark">
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>저자</td>
					<td>출판사</td>
					<td>수정</td>
					<td>삭제</td>
				</tr>
			</thead>
			<tbody>
				<%for(BookDto tmp:list){ %>
					<tr>
						<td><%=tmp.getNum() %></td>
						<td><%=tmp.getName() %></td>
						<td><%=tmp.getAuthor() %></td>
						<td><%=tmp.getPublisher() %></td>
						<td>
							<a href="updateform.jsp?num=<%=tmp.getNum() %>">수정</a>
						</td>
						<td>
							<a href="delete.jsp?num=<%=tmp.getNum() %>">삭제</a>
						</td>
					</tr>
				<%} %>
			</tbody>
		</table>
	</div>
</body>
</html>