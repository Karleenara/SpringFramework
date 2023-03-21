<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.kosa.myapp1.score.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<ScoreDto> list = (List<ScoreDto>)request.getAttribute("list");
	
%>
<%for(int i=0; i<list.size(); i++){
	ScoreDto dto = list.get(i);
%>
	<%=dto.getName() %> &nbsp;&nbsp;
	<%=dto.getEng() %> &nbsp;&nbsp;
	<%=dto.getKor() %> &nbsp;&nbsp;
	<%=dto.getMat() %> &nbsp;&nbsp;
	<br/>
<%} %> 

</body>
</html>