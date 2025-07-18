<%@page import="test.dto.BoardDto"%>
<%@page import="java.util.List"%>
<%@page import="test.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//글 목록 얻어오기
	List<BoardDto> list = BoardDao.getInstance().selectAll();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/include/navbar.jsp">
		<jsp:param value="board" name="thisPage"/>
	</jsp:include>
	<div class="container pt-1"> <!-- pt : padding-top, pt-1~5 까지 있음 
											1~5 (4, 8, 16, 24, 48px)-->
		<a class="btn btn-outline-primary btn-sm" href="new-form.jsp">
			새 글 작성
			<i class="bi bi-pencil-square"></i>
		</a>
		<h1>게시글 목록입니다</h1>
		<table class="table table-bordered">
			<thead>
				<tr>
					<td>글번호</td>
					<td>작성자</td>
					<td>제목</td>
					<td>조회수</td>
					<td>작성일</td>					
				</tr>
			</thead>
			<tbody>
				<%for(BoardDto tmp : list){ %>
					<tr>
						<td><%=tmp.getNum() %></td>
						<td><%=tmp.getWriter() %></td>
						<td>
							<a href="view.jsp?num=<%=tmp.getNum() %>"><%=tmp.getTitle() %></a>
						</td>
						<td><%=tmp.getViewCount() %></td>
						<td><%=tmp.getCreatedAt() %></td>
					</tr>
				<%} %>
			</tbody>
		</table>
	</div>
</body>
</html>