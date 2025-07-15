<%@page import="test.dto.BookDto"%>
<%@page import="test.dao.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 폼 전송되는 책 정보를 추출
	String name=request.getParameter("name");
	String author=request.getParameter("author");
	String publisher=request.getParameter("publisher");
	//2. 책 정보를 dto 에 담고
	BookDto dto = new BookDto();
	dto.setName(name);
	dto.setAuthor(author);
	dto.setPublisher(publisher);
	//3. dao 객체를 이용해서 DB 에 저장	
	boolean isSuccess=new BookDao().insert(dto);
	//4. 응답		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/book/insert.jsp</title>
</head>
<body>
	<div class="container">
		<%if(isSuccess){ %>
			<p>
				<strong><%=name %></strong> 책 정보가 추가되었습니다.
				<a href="list.jsp">확인</a>
			</p>
		<%}else{ %>
			<p>
				책 정보 추가에 실패하였습니다.
				<a href="insertform.jsp">다시하기</a>
			</p>
		<%} %>
	</div>	
</body>
</html>