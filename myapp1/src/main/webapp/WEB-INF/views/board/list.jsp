<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.kosa.myapp1.board.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<BoardDto> list = (List<BoardDto>)request.getAttribute("list");
%>
<% for(int i=0; i < list.size(); i++){
		BoardDto dto = list.get(i);
%>
	<%=dto.getSeq() %> &nbsp;&nbsp;
	<%=dto.getTitle() %> &nbsp;&nbsp;
	<%=dto.getWriter() %> &nbsp;&nbsp;
	<%=dto.getWdate() %> &nbsp;&nbsp; 
	<%=dto.getHit() %> &nbsp;&nbsp;
	<br/>
<%} %>
</body>
</html>