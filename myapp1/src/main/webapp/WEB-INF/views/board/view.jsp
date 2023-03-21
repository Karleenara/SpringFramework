<%@page import="com.kosa.myapp1.board.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "com.kosa.myapp1.board.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	BoardDto dto = (BoardDto)request.getAttribute("dto");
%>
<%= dto.getSeq() %><br/>
<%= dto.getTitle() %><br/>
<%= dto.getWriter() %><br/>
<%= dto.getContents() %><br/>
<%= dto.getWdate() %><br/>
<%= dto.getHit() %><br/>

</body>
</html>