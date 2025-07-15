<%@page import="test.dao.BookDao"%>
<%@page import="test.dto.BookDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//폼 전송되는 수정할 책 정보를 추출해서
	int num=Integer.parseInt(request.getParameter("num"));
	String name=request.getParameter("name");
	String author=request.getParameter("author");
	String publisher=request.getParameter("publisher");	
	//BookDto 객체에 담고
	BookDto dto=new BookDto();
	dto.setNum(num);
	dto.setName(name);
	dto.setAuthor(author);
	dto.setPublisher(publisher);
	//DB 에 수정반영하고
	boolean isSuccess=new BookDao().update(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/book/update.jsp</title>
</head>
<body>
	<!-- html 응답하면서 javascript 를 로딩시키기 
	 아래의 script 안에 작성한 문자열은 클라이언트 웹브라우저가 javascript 로 평가해서
	 해석한다-->
	<script> 
		<%if(isSuccess){ %>
			alert("<%=name %> 책의 정보를 성공적으로 수정했습니다");
			//javascript 를 이용해서 페이지 이동(redirect 효과를 낼 수 있다)
			location.href="list.jsp";
		<%}else{ %>
			alert("수정 실패");
			//다시 수정 폼으로 이동시키기
			location.href="updateform.jsp?num=<%=num %>";
		<%} %>
	
	</script>	
</body>
</html>