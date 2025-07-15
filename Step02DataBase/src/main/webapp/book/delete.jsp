<%@page import="test.dao.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num=Integer.parseInt(request.getParameter("num"));
	BookDao dao = new BookDao();
	dao.deleteByNum(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/book/delete.jsp</title>
</head>
<body>
	<script>
		alert("삭제 완료!");
		location.href="${pageContext.request.contextPath }/book/list.jsp";
	</script>
</body>
</html>